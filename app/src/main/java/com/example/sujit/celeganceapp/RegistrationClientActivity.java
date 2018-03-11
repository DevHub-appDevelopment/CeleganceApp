package com.example.sujit.celeganceapp;

import com.example.sujit.celeganceapp.packages.Register_Pojo_class;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationClientActivity extends AppCompatActivity {


    private ProgressBar mProgressBar;
    private EditText TextName;
    private EditText TextReg;
    private EditText TextPhone;
    private EditText TextBranch;
    private Button submit;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private String event_name;
    Bundle extras;
    String i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_client);

        database = FirebaseDatabase.getInstance();
        TextName = findViewById(R.id.textName);
        TextReg = findViewById(R.id.textRegId);
        TextPhone =findViewById(R.id.textPhone);
        TextBranch = findViewById(R.id.textBranch);
        submit = findViewById(R.id.btn_register);
        extras = getIntent().getExtras();
        event_name= extras.getString("EventName");
        Log.e("Event Name",event_name);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextName.getText().toString().equals("")||TextPhone.getText().toString().equals("")||
                        TextReg.getText().toString().equals("")||
                        TextBranch.getText().toString().equals("")){
                    Toast.makeText(RegistrationClientActivity.this,"Must fill up all the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    registerNewMember(TextName.getText().toString(),TextPhone.getText().toString(),"1"
                            ,TextReg.getText().toString(),TextBranch.getText().toString());}

            }
        });
    }



    public void registerNewMember(String name,String phone,String qualify,String regId,String branch){
        final Members members = new Members(name,phone,qualify,regId,branch);
        final DatabaseReference databaseReference = database.getReference("Events").child(event_name);
        //final String[] i = new String[]{"Never been pressed"};
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                i = Long.toString(dataSnapshot.getChildrenCount()+1);
                databaseReference.child(i).setValue(members);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Toast.makeText(this,"Member Added",Toast.LENGTH_LONG).show();


    }


}

