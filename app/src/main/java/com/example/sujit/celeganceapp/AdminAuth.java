package com.example.sujit.celeganceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2/27/2018.
 */

public class AdminAuth extends AppCompatActivity{
    private EditText phoneNum;
    private Button sendCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    TextView notAdmin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_auth);
        phoneNum = (EditText)findViewById(R.id.phoneNumber);
        sendCode = (Button)findViewById(R.id.sendVerfication);
        notAdmin  = (TextView)findViewById(R.id.notAdmin);



        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber ="+91"+phoneNum.getText().toString();
                DatabaseReference reference = database.getReference("Admins");
                Query query = reference.orderByChild("phone").equalTo(phoneNumber);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            Log.e("Number","Exists");
                            String phoneNumber ="+91"+phoneNum.getText().toString();
                            phoneNum.setEnabled(false);
                            sendCode.setEnabled(false);
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    phoneNumber,
                                    60,
                                    TimeUnit.SECONDS,
                                    AdminAuth.this,
                                    mCallbacks);

                        }
                        else {
                            Log.e("Number","Doesn't");

                            notAdmin.setText("You are not an admin");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Log.w("", "onVerifica tionFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                } else if (e instanceof FirebaseTooManyRequestsException) {

                }


            }


        };


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(AdminAuth.this,AdminPanel.class);
                            startActivity(intent);
                            finish();
                            // ...
                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }


}
