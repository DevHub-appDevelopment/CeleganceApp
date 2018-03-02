package com.example.sujit.celeganceapp.ContestantData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.sujit.celeganceapp.R;

import java.util.ArrayList;
import java.util.List;


public class disQualify extends Fragment {


    RecyclerView recyclerView;

    ContestantAdapter adapter;
    List<ContestantData> dataList;
    List<ContestantData> selection_list = new ArrayList<ContestantData>();
    Participant participant;
    int Type =0;

    public disQualify() {
        // Required empty public constructor
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
        ContestantData data = new ContestantData("XXXXXX","XXXXX","XXXXXX","XXXXXX");
        recyclerView = rootView.findViewById(R.id.recyclerView);
        dataList = new ArrayList<ContestantData>();
        for(int i = 0;i<=10;i++)
            dataList.add(data);

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


}
