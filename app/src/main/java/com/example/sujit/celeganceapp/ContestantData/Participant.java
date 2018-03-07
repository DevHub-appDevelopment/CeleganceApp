package com.example.sujit.celeganceapp.ContestantData;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sujit.celeganceapp.AddMembers;
import com.example.sujit.celeganceapp.AdminAuth;
import com.example.sujit.celeganceapp.AdminPanel;
import com.example.sujit.celeganceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Participant extends AppCompatActivity implements SearchView.OnQueryTextListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    int counter = 0;
    TextView counter_text;
    Qualify qualify;
    disQualify disQualify;
    boolean action_mode =false;
    int type;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    String phoneNumber ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        counter_text = findViewById(R.id.Counter_text);
        counter_text.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        database = FirebaseDatabase.getInstance();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //mAuth = FirebaseAuth.getInstance();
        //currentUser = mAuth.getCurrentUser();
        //phoneNumber = currentUser.getPhoneNumber();

        /*if(currentUser==null)
        {
            Intent intent = new Intent(Participant.this, AdminAuth.class);
            startActivity(intent);
            finish();
        }
        else {
            setupViewPager(viewPager);
        }*/
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        disQualify = new disQualify();
        qualify = new Qualify();
        adapter.addFragment(qualify, "Qualified");
        adapter.addFragment(disQualify, "DisQuslified");

        viewPager.setAdapter(adapter);

    }

    public void prepareSelection(View view, int adapterPosition, int type) {
        switch (type)
        {
            case 1: qualify.prepareSelection(view,adapterPosition);
            break;
            case 0:disQualify.prepareSelection(view, adapterPosition);
        }


    }


    public void onLongClick(int type) {
        toolbar.getMenu().clear();
        this.type =type;


        counter_text.setVisibility(View.VISIBLE);

        action_mode =true;

            qualify.adapter.notifyDataSetChanged();

            disQualify.adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<ContestantData> newList = new ArrayList<>();
        List<ContestantData> dis = new ArrayList<>();

            for (ContestantData data : qualify.dataList) {
                String name = data.getName().toLowerCase();
                String branch = data.getBranch().toLowerCase();
                String reg =data.getReg().toLowerCase();
                if (name.contains(newText)||branch.contains(newText)||reg.contains(newText))
                    newList.add(data);
            }
            qualify.adapter.setFilter(newList);



            for (ContestantData data : disQualify.dataList) {
                String name = data.getName().toLowerCase();
                String branch = data.getBranch().toLowerCase();
                String reg =data.getReg().toLowerCase();
                if (name.contains(newText)||branch.contains(newText)||reg.contains(newText))
                    dis.add(data);
            }
            disQualify.adapter.setFilter(dis);



        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        MenuItem menuItem = menu.findItem(R.id.serach);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    public  void UpdateCounter(int i)
    { switch (i)
    {
        case 1: counter +=1;
        break;
        case 0:counter-=1;
    }
        if(counter == 0)
        {
            counter_text.setText("0 item selected");
        }
        else
            counter_text.setText(counter+" item selected");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                DeactivateActionMode();
                break;
            case R.id.addMembers:
               updateCandidateInfo();
                break;
            case R.id.action_settings:
                mAuth.signOut();
                Intent intent = new Intent(Participant.this,AdminAuth.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sendMessage :
                openDialouge();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(action_mode)
        {
            DeactivateActionMode();
        }
        else {

            super.onBackPressed();
        }
    }

    private void DeactivateActionMode() {
        qualify.selection_list.clear();
        counter = 0;
        counter_text.setText("0 item selected");
        counter_text.setVisibility(View.GONE);
        action_mode =false;

            qualify.adapter.notifyDataSetChanged();

            disQualify.adapter.notifyDataSetChanged();
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
    public void updateCandidateInfo(){
        String phoneNum = mAuth.getCurrentUser().getPhoneNumber();
        final DatabaseReference databaseReference = database.getReference("Admins");
        Query query = databaseReference.orderByChild("phone").equalTo(phoneNum);
        //Log.e("Event in updateinfo",eventTest);
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // dataSnapshot.child("event").getValue().toString();
                String event="";
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()) {
                    DataSnapshot admin = iterator.next();

                    event =admin.child("event").getValue().toString();
                    break;

                }
                Intent intent = new Intent(Participant.this,AddMembers.class);
                intent.putExtra("event",event);
                startActivity(intent);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void openDialouge(){
        final DatabaseReference databaseReference = database.getReference("Admins");
        Query query = databaseReference.orderByChild("phone").equalTo(phoneNumber);
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // dataSnapshot.child("event").getValue().toString();
                String event="";
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()) {
                    DataSnapshot admin = iterator.next();

                    event =admin.child("event").getValue().toString();
                    break;

                }
                SendMessageDialouge sendMessageDialouge = new SendMessageDialouge();
                Bundle bundle = new Bundle();
                bundle.putString("Event",event);
                sendMessageDialouge.setArguments(bundle);
                sendMessageDialouge.show(getSupportFragmentManager(),"Send message");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

