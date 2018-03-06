package com.example.sujit.celeganceapp.ContestantData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class disQualify extends Fragment {


    RecyclerView recyclerView;

    ContestantAdapter adapter;
    List<ContestantData> dataList;
    List<ContestantData> selection_list = new ArrayList<ContestantData>();
    Participant participant;
    int Type =0;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    String eventTest;
    Iterator<DataSnapshot> dataSnapshotIterator;
    ContestantData contestantData;
    public disQualify() {
        // Required empty public constructor
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        showCandidateInfo();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_qualify, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        dataList = new ArrayList<ContestantData>();


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
        query.addValueEventListener(new ValueEventListener() {
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
                    databaseReference1.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.e("Inside","Data Change");
                            Iterator<DataSnapshot> dataSnapshotIterator1 = dataSnapshot.getChildren().iterator();
                            while (dataSnapshotIterator1.hasNext())
                            {
                                DataSnapshot candidates = dataSnapshotIterator1.next();
                                if(candidates.child("qualify").getValue().toString().equals("0")) {
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



}
