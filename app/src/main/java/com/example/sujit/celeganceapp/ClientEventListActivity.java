package com.example.sujit.celeganceapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.sujit.celeganceapp.ContestantData.Participant;

public class ClientEventListActivity extends AppCompatActivity {

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
        getSupportActionBar().setTitle("Inter Events");

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
            Intent intent = new Intent(ClientEventListActivity.this,Participant.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    private void setupListView()
    {
        String [] titleList = getResources().getStringArray(R.array.interEvents);
        String [] category = getResources().getStringArray(R.array.category);



        LAdapter lAdapter = new LAdapter(ClientEventListActivity.this,titleList,category);
        eventsListView.setAdapter(lAdapter);

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                goto_event_page(i);

            }
        });

    }
            private void goto_event_page(int pos) {
                Intent intent = new Intent(this,Show_Event_details.class);
                intent.putExtra("EventArr",new int[]{pos,1});
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

            if(titleList[i].equalsIgnoreCase("Dancing Feet"))
            {
                eventImageView.setImageResource(R.drawable.dancing_feet);
            }
            else if(titleList[i].equalsIgnoreCase("War of DJs"))
            {
                eventImageView.setImageResource(R.drawable.war_dj);
            }
            else if(titleList[i].equalsIgnoreCase("Battle of Bands"))
            {
                eventImageView.setImageResource(R.drawable.battleofbandsfinal);
            }
            else if(titleList[i].equalsIgnoreCase("sargam"))
            {
                eventImageView.setImageResource(R.drawable.sargam);
            }
            else if(titleList[i].equalsIgnoreCase("Muscle Maniac"))
            {
                eventImageView.setImageResource(R.drawable.muscle);
            }
            else if(titleList[i].equalsIgnoreCase("Nukkad"))
            {
                eventImageView.setImageResource(R.drawable.nukkad);
            }
            else if(titleList[i].equalsIgnoreCase("Dramaturgy"))
            {
                eventImageView.setImageResource(R.drawable.dramaturgy);
            }
            else if(titleList[i].equalsIgnoreCase("Art-Attack"))
            {
                eventImageView.setImageResource(R.drawable.artattackfinal);
            }
            else if(titleList[i].equalsIgnoreCase("Mobile Legends"))
            {
                eventImageView.setImageResource(R.drawable.mobile_legend);
            }
            else if(titleList[i].equalsIgnoreCase("Game Raiderz"))
            {
                eventImageView.setImageResource(R.drawable.game_raid);
            }
            else if(titleList[i].equalsIgnoreCase("Gladiator"))
            {
                eventImageView.setImageResource(R.drawable.gladiator);
            }
            else if(titleList[i].equalsIgnoreCase("Gully Cricket"))
            {
                eventImageView.setImageResource(R.drawable.gully_cricket);
            }
            else if(titleList[i].equalsIgnoreCase("Open Mic"))
            {
                eventImageView.setImageResource(R.drawable.open_mic);
            }
            else if(titleList[i].equalsIgnoreCase("Basket Ball"))
            {
                eventImageView.setImageResource(R.drawable.basket_ball);
            }
            else if(titleList[i].equalsIgnoreCase("Splitsvilla"))
            {
                eventImageView.setImageResource(R.drawable.splitsvilla);
            }
            else if(titleList[i].equalsIgnoreCase("Roadies"))
            {
                eventImageView.setImageResource(R.drawable.roadies);
            }
            else
            {
                eventImageView.setImageResource(R.drawable.deadpool);
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
