package com.example.sujit.celeganceapp;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2/27/2018.
 */

public class AdminPanel extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    String eventTest;
    Iterator<DataSnapshot> dataSnapshotIterator;
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        setUpToolBar();


    }
    public  void setUpToolBar(){
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.adminToobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin");
        //
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_settings)
        {
            mAuth.signOut();
            Intent intent = new Intent(AdminPanel.this,AdminAuth.class);
            startActivity(intent);
            finish();

        }
        if(id==R.id.addMembers)
        {
          updateCandidateInfo();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(AdminPanel.this, AdminAuth.class);
            startActivity(intent);
            finish();
        } else {
            showCandidateInfo();
        }
    }
//        logOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.signOut();
//                Intent intent = new Intent(AdminPanel.this,AdminAuth.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }

    public void showCandidateInfo(){
        String phoneNum = mAuth.getCurrentUser().getPhoneNumber();
        final DatabaseReference databaseReference = database.getReference("Admins");
        Query query = databaseReference.orderByChild("phone").equalTo(phoneNum);
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
                                Log.e("Name",candidates.child("name").getValue().toString());
                                Log.e("regId",candidates.child("regId").getValue().toString());
                                Log.e("phone",candidates.child("phone").getValue().toString());
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
                Intent intent = new Intent(AdminPanel.this,AddMembers.class);
                intent.putExtra("event",event);
                startActivity(intent);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
