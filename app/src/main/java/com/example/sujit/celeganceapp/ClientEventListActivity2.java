package com.example.sujit.celeganceapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.sujit.celeganceapp.ContestantData.Participant;

import java.util.ArrayList;

public class ClientEventListActivity2 extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ListView eventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_event_list);

        setupComponents();
        setupToolbar();
        setupListView();
    }

    private void setupComponents(){
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolBarMain);
        eventsListView = (ListView) findViewById(R.id.eventList);

    }

    private void setupToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Intra Events");

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
            Intent intent = new Intent(ClientEventListActivity2.this,Participant.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    private void setupListView()
    {
        String [] titleList = getResources().getStringArray(R.array.intraEvents);
        String [] category = getResources().getStringArray(R.array.category2);



        LAdapter lAdapter = new LAdapter(ClientEventListActivity2.this,titleList,category);
        eventsListView.setAdapter(lAdapter);

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //  Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i)+"is selected", Toast.LENGTH_SHORT).show();
                //String  event_name =(String) adapterView.getItemAtPosition(i);
                Toast.makeText(getBaseContext(),"selected = "+i,Toast.LENGTH_SHORT).show();
                goto_event_page(i);

            }
        });

    }
    private void goto_event_page(int pos) {
        Intent intent = new Intent(this,Show_Event_details.class);
        intent.putExtra("EventArr",new int[]{pos,2});
        startActivity(intent);
    }

    //Adapter class for the listview
    public class LAdapter extends BaseAdapter
    {
        private String [] titleList;
        private String [] categoryList;
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView titleTextView;
        private TextView categoryTextView;
        private ImageView eventImageView;




        public LAdapter(Context mContext, String [] titleList,String [] categoryList) {
            this.mContext = mContext;
            this.titleList = titleList;
            this.categoryList = categoryList;
            layoutInflater = LayoutInflater.from(this.mContext);
        }

        @Override
        public int getCount() {
            return titleList.length;
        }

        @Override
        public Object getItem(int i) {
            return titleList[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view = layoutInflater.inflate(R.layout.event_single_list_item,null);
            }
            titleTextView = (TextView)view.findViewById(R.id.eventNamesTxtview);
            // descTextView = (TextView)view.findViewById(R.id.eventDescriptionTxtview);
            eventImageView = (ImageView)view.findViewById(R.id.imgEvents);
            categoryTextView = (TextView)view.findViewById(R.id.category);

            titleTextView.setText(titleList[i]);
            categoryTextView.setText(categoryList[i]);

            if(titleList[i].equalsIgnoreCase("Dhating Naach"))
            {
                eventImageView.setImageResource(R.drawable.dhating_naach);
            }
            else if(titleList[i].equalsIgnoreCase("Treasure Hunt"))
            {
                eventImageView.setImageResource(R.drawable.treasure_hunt);
            }
            else if(titleList[i].equalsIgnoreCase("Fast N Furious"))
            {
                eventImageView.setImageResource(R.drawable.fnf);
            }
            else if(titleList[i].equalsIgnoreCase("Beg Borrow Steal"))
            {
                eventImageView.setImageResource(R.drawable.bbs);
            }
            else if(titleList[i].equalsIgnoreCase("Xumberance"))
            {
                eventImageView.setImageResource(R.drawable.xumberance);
            }
            else if(titleList[i].equalsIgnoreCase("Rangbazzi"))
            {
                eventImageView.setImageResource(R.drawable.rangbazz);
            }
            else if(titleList[i].equalsIgnoreCase("Dosti Not Out"))
            {
                eventImageView.setImageResource(R.drawable.dosti);
            }
            else if(titleList[i].equalsIgnoreCase("Dangal"))
            {
                eventImageView.setImageResource(R.drawable.dangal);
            }
            else if(titleList[i].equalsIgnoreCase("Filmy Deewane"))
            {
                eventImageView.setImageResource(R.drawable.filmy);
            }
            else if(titleList[i].equalsIgnoreCase("Game of Thrones"))
            {
                eventImageView.setImageResource(R.drawable.got);
            }
            else if(titleList[i].equalsIgnoreCase("Youth Minister"))
            {
                eventImageView.setImageResource(R.drawable.ym);
            }
            else if(titleList[i].equalsIgnoreCase("Lucky Star"))
            {
                eventImageView.setImageResource(R.drawable.lucky);
            }
            else if(titleList[i].equalsIgnoreCase("Eat and Earn"))
            {
                eventImageView.setImageResource(R.drawable.eat);
            }
            else if(titleList[i].equalsIgnoreCase("Sherlock Holmes"))
            {
                eventImageView.setImageResource(R.drawable.sherlock);
            }
            else
            {
                eventImageView.setImageResource(R.drawable.sumo);
            }


            return view;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent2 =new Intent(this,MainActivity.class);
        startActivity(intent2);
        finish();
    }
}
