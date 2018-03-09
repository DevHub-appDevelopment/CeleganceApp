package com.example.sujit.celeganceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Book> lstBook;
   android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Celegance");
        //This is just a test



        //Category grid starts here
        lstBook = new ArrayList<>();
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));
        lstBook.add(new Book("The vegetarian","Category","Description book",R.drawable.cccc));


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        com.sunbees.recyclerview.RecyclerViewAdapter myAdapter= new com.sunbees.recyclerview.RecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_admin)
        {
            Intent intent = new Intent(MainActivity.this,AdminPanel.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
