package com.example.sujit.celeganceapp.ContestantData;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.sujit.celeganceapp.Manifest;
import com.example.sujit.celeganceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

/**
 * Created by user on 3/7/2018.
 */

public class SendMessageDialouge extends AppCompatDialogFragment{

    final int SEND_SMS_REQUEST = 5;
    EditText message;
    FirebaseDatabase database;
    String event;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alerTdialouge = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        database = FirebaseDatabase.getInstance();
        final View view =  layoutInflater.inflate(R.layout.send_message,null);


        alerTdialouge.setView(view)

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        message = view.findViewById(R.id.enterMessage);
                     sendSms();
                    }
                });

        return alerTdialouge.create();
    }
    public  void sendSms(){
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.SEND_SMS},
                   SEND_SMS_REQUEST);

        }
        else {

           sendSmsToCandidates();

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS_REQUEST: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                  sendSmsToCandidates();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }
    public  void sendSmsToCandidates(){
        Bundle bundle = getArguments();
        event = bundle.getString("Event");
        final DatabaseReference databaseReference1 = database.getReference("Events").child(event);
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Inside","Data Change");
                Iterator<DataSnapshot> dataSnapshotIterator1 = dataSnapshot.getChildren().iterator();
                while (dataSnapshotIterator1.hasNext())
                {
                    DataSnapshot candidates = dataSnapshotIterator1.next();
                    if(candidates.child("qualify").getValue().toString().equals("1")) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(candidates.child("phone").getValue().toString(),null,
                                message.getText().toString(),null,null);

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
