package com.example.sujit.celeganceapp.ContestantData;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.sujit.celeganceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class disQualify extends Fragment implements View.OnClickListener{


    RecyclerView recyclerView;

    ContestantAdapter adapter;
    List<ContestantData> dataList = new ArrayList<>();;
    List<ContestantData> selection_list = new ArrayList<ContestantData>();
    Participant participant;
    int Type =0;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    String eventTest;
    Iterator<DataSnapshot> dataSnapshotIterator;
    ContestantData contestantData;
    ProgressDialog dialog;
    public disQualify() {

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        refresh();


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dis_qualify, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        dataList = new ArrayList<ContestantData>();
        Button qualify = rootView.findViewById(R.id.qualify);
        Button disQualify = rootView.findViewById(R.id.diqualify);
        disQualify.setOnClickListener(this);
        qualify.setOnClickListener(this);


        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContestantAdapter(getContext(),dataList,Type);
        recyclerView.setAdapter(adapter);
        participant = (Participant)getContext();


        return rootView;
    }
    public void prepareSelection(View view, int position) {
        if (((CheckBox) view).isChecked()) {
            selection_list.add(dataList.get(position));

            participant.UpdateCounter(1);

        } else {
            selection_list.remove(dataList.get(position));

            participant.UpdateCounter(0);
        }

    }


    public void showCandidateInfo(){
//        String phoneNum = mAuth.getCurrentUser().getPhoneNumber();
        final DatabaseReference databaseReference = database.getReference("Admins");
        Query query = databaseReference.orderByChild("phone").equalTo("+917008916802");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshotIterator = dataSnapshot.getChildren().iterator();
                while(dataSnapshotIterator.hasNext())
                {
                    DataSnapshot admin = dataSnapshotIterator.next();

                    //Log.e("Event",admin.child("event").getValue().toString());
                    eventTest = admin.child("event").getValue().toString();
                    final DatabaseReference databaseReference1 = database.getReference("Events").child(admin.child("event").getValue().toString());
                    //  Query query1 = databaseReference1.orderByChild(admin.child("event").getValue().toString());
                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.e("Inside","Data Change");
                            Iterator<DataSnapshot> dataSnapshotIterator1 = dataSnapshot.getChildren().iterator();
                            while (dataSnapshotIterator1.hasNext())
                            {
                                DataSnapshot candidates = dataSnapshotIterator1.next();
                                if(candidates.child("qualify").getValue().toString().equals("0")&& search(candidates.child("regId").getValue().toString())) {
                                    contestantData = new ContestantData(candidates.child("name").getValue().toString(), candidates.child("regId").getValue().toString(), candidates.child("branch").getValue().toString(), candidates.child("phone").getValue().toString(), candidates.child("qualify").getValue().toString());
                                    dataList.add(contestantData);
                                   adapter.notifyDataSetChanged();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean search(String reg)
    {
        for(ContestantData data : dataList)
        {
            if(data.getReg().equals(reg))
            {
                return false;
            }


        }
        return true;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.qualify: {
                dataList.clear();
                Iterator<ContestantData> contestantDataIterator = selection_list.listIterator();
                while (contestantDataIterator.hasNext()) {


                    DatabaseReference reference = database.getReference("Events").child(eventTest);
                    Query query = reference.orderByChild("regId").equalTo(contestantDataIterator.next().getReg());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                dataSnapshot1.getRef().child("qualify").setValue("1");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

                selection_list.clear();

                break;


            }
        }
        }
    public void refresh()
    {
        final DatabaseReference databaseReference = database.getReference("Admins");
        Query query = databaseReference.orderByChild("phone").equalTo("+917008916802");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshotIterator = dataSnapshot.getChildren().iterator();
                while (dataSnapshotIterator.hasNext()) {
                    DataSnapshot admin = dataSnapshotIterator.next();


                    eventTest = admin.child("event").getValue().toString();
                    final DatabaseReference databaseReference1 = database.getReference("Events").child(admin.child("event").getValue().toString());
                    databaseReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            showCandidateInfo();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}






