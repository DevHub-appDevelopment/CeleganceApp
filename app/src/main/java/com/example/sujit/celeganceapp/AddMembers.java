package com.example.sujit.celeganceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by user on 3/3/2018.
 */

public class AddMembers extends AppCompatActivity {

    private Button add;
    private EditText name;
    private  EditText college;
    private EditText phone;
    private EditText regId;
    private  Bundle extras;
    private  String event;
    private FirebaseDatabase firebaseDatabase;
    String i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmembers);
        setUpComponents();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewMember(name.getText().toString(),phone.getText().toString(),"1",regId.getText().toString());
            }
        });



    }
    public void  setUpComponents(){
        name =(EditText)findViewById(R.id.enterName);
        college = (EditText)findViewById(R.id.enterClg);
        phone = (EditText)findViewById(R.id.enterPhone);
        regId = (EditText)findViewById(R.id.enterId);
        add = (Button)findViewById(R.id.addMembers);
        firebaseDatabase = FirebaseDatabase.getInstance();
        extras = getIntent().getExtras();
        if(extras!=null)
        {
            event = extras.getString("event");
            Log.e("event in the fucking",event);
        }

    }
    public void registerNewMember(String name,String phone,String qualify,String regId){
        final Members members = new Members(name,phone,qualify,regId);
        final DatabaseReference databaseReference = firebaseDatabase.getReference("Events").child(event);
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
        



    }
}
