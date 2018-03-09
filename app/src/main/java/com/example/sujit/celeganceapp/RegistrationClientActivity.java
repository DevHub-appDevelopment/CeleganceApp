package com.example.sujit.celeganceapp;

import com.example.sujit.celeganceapp.packages.Register_Pojo_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sujit.celeganceapp.packages.Register_Pojo_class;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationClientActivity extends AppCompatActivity {


    private ProgressBar mProgressBar;
    private TextView loadingPleaseWaitTextView;
    private EditText TextName;
    private EditText TextReg;
    private EditText TextPhone;
    private EditText TextBranch;
    private Button submit;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private String event_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_client);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        TextName = findViewById(R.id.textName);
        TextReg = findViewById(R.id.textRegId);
        TextPhone =findViewById(R.id.textPhone);
        TextBranch = findViewById(R.id.textBranch);
        submit = findViewById(R.id.btn_register);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storeIntoFireBase();
                initWidgets();
            }
        });
    }


    private void storeIntoFireBase() {
        Toast.makeText(this,"on click",Toast.LENGTH_SHORT).show();

        String name = TextName.getText().toString();
        String branch = TextBranch.getText().toString();
        String reg_id = TextReg.getText().toString();
        String  phone_string = TextPhone.getText().toString();
        long phone_number = Integer.parseInt(phone_string);

        Intent intent= getIntent();
        event_name = intent.getStringExtra("event_name");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Participants");

        Register_Pojo_class participant_details = new Register_Pojo_class(name,branch,reg_id,phone_number,event_name);
        String participant_name = TextName.toString();
        reference.child(participant_name).setValue(participant_details);


    }

    private void initWidgets()
    {
     mProgressBar.setVisibility(View.VISIBLE);
        loadingPleaseWaitTextView  = (TextView)findViewById(R.id.loadingPleaseWait);
        mProgressBar.setVisibility(View.GONE);
        loadingPleaseWaitTextView.setVisibility(View.GONE);
    }
}

