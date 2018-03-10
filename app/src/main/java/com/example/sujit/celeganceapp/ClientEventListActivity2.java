package com.example.sujit.celeganceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        getSupportActionBar().setTitle("Inter Events");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_admin)
        {
            Intent intent = new Intent(ClientEventListActivity2.this,AdminPanel.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    private void setupListView()
    {
        String [] titleList = getResources().getStringArray(R.array.Events);
        String [] description = getResources().getStringArray(R.array.Description);



        LAdapter lAdapter = new LAdapter(ClientEventListActivity2.this,titleList,description);
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
                Toast.makeText(this,"value passed "+pos,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Show_Event_details.class);
                intent.putExtra("event_number",pos);
                startActivity(intent);
            }

    //Adapter class for the listview
    public class LAdapter extends BaseAdapter
    {
        private String [] titleList;
        private String [] descList;
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView titleTextView;
        private TextView descTextView;
        private ImageView eventImageView;




        public LAdapter(Context mContext, String [] titleList,String [] descList) {
            this.mContext = mContext;
            this.titleList = titleList;
            this.descList = descList;
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
            descTextView = (TextView)view.findViewById(R.id.eventDescriptionTxtview);
            eventImageView = (ImageView)view.findViewById(R.id.imgEvents);

            titleTextView.setText(titleList[i]);
          //  descTextView.setText(descList[i]);

            if(titleList[i].equalsIgnoreCase("Filmy Deewane"))
            {
                eventImageView.setImageResource(R.drawable.background);
            }
            else if(titleList[i].equalsIgnoreCase("Event 2"))
            {
                eventImageView.setImageResource(R.drawable.background);
            }
            else if(titleList[i].equalsIgnoreCase("Event 3"))
            {
                eventImageView.setImageResource(R.drawable.background);
            }
            else if(titleList[i].equalsIgnoreCase("Event 4"))
            {
                eventImageView.setImageResource(R.drawable.background);
            }
            else if(titleList[i].equalsIgnoreCase("Event 5"))
            {
                eventImageView.setImageResource(R.drawable.addimage);
            }
            else if(titleList[i].equalsIgnoreCase("Event 6"))
            {
                eventImageView.setImageResource(R.drawable.addimage);
            }
            else if(titleList[i].equalsIgnoreCase("Event 7"))
            {
                eventImageView.setImageResource(R.drawable.addimage);
            }
            else if(titleList[i].equalsIgnoreCase("Event 8"))
            {
                eventImageView.setImageResource(R.drawable.addimage);
            }
            else if(titleList[i].equalsIgnoreCase("Event 9"))
            {
                eventImageView.setImageResource(R.drawable.addimage);
            }
            else
            {
                eventImageView.setImageResource(R.drawable.addimage);
            }


            return view;
        }

    }


}
