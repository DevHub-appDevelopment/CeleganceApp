package com.example.sujit.celeganceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
public class GridEventListActivity extends AppCompatActivity {
    // ArrayList for person names
    ArrayList<String> personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7", "Person 8", "Person 9", "Person 10", "Person 11", "Person 12", "Person 13", "Person 14"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.currency, R.drawable.celegance, R.drawable.currency, R.drawable.celegance, R.drawable.currency, R.drawable.addimage, R.drawable.celegance, R.drawable.currency, R.drawable.celegance, R.drawable.celegance, R.drawable.currency, R.drawable.celegance, R.drawable.currency, R.drawable.addimage));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_event_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager); //
        GridAdapter gridAdapter = new GridAdapter(this, personNames, personImages);
        recyclerView.setAdapter(gridAdapter); // set the Adapter to RecyclerView
    }
}
