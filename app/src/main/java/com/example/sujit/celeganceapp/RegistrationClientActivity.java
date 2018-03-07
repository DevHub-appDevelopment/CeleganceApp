package com.example.sujit.celeganceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegistrationClientActivity extends AppCompatActivity {


    private ProgressBar mProgressBar;
    private TextView loadingPleaseWaitTextView;
    private EditText emailEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_client);


    }

    private void initWidgets()
    {
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        loadingPleaseWaitTextView  = (TextView)findViewById(R.id.loadingPleaseWait);
        mProgressBar.setVisibility(View.GONE);
        loadingPleaseWaitTextView.setVisibility(View.GONE);
    }
}

