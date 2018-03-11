package com.example.sujit.celeganceapp;

import android.app.ActionBar;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.location.SettingInjectorService;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Show_Event_details extends AppCompatActivity {
    TextView event_description;
    TextView event_name;
    Switch notify_switch;
    String [] events;
    int event_number;
    String passingValue;
    Bundle extras;
    int[] eventArr;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    String [] description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show__event_details);
        extras=getIntent().getExtras();
        eventArr = extras.getIntArray("EventArr");

        Log.e("Inside","Show Event");
        event_number = eventArr[0];
        Log.e("Event number",Integer.toString(event_number));
        Log.e("Event cat",Integer.toString(eventArr[1]));
        //Toast.makeText(this,event_number+" received",Toast.LENGTH_LONG).show();

        event_name = findViewById(R.id.EventName);
        event_description = (TextView)findViewById(R.id.description);
        if(eventArr[1]==2){

            description  = getResources().getStringArray(R.array.Description2);

            events = getResources().getStringArray(R.array.intraEvents);
        }
        else{

            description = getResources().getStringArray(R.array.Description);

            events = getResources().getStringArray(R.array.interEvents);
        }


        event_name.setText(events[event_number]);
        passingValue = events[event_number];
        event_description.setText(Html.fromHtml(description[event_number]));
        event_description.setMovementMethod(new ScrollingMovementMethod());






    }
    public void register(View view)
    {

        Intent intent2 = new Intent(this, RegistrationClientActivity.class);
        intent2.putExtra("EventName",passingValue.toLowerCase());
        Toast.makeText(this,passingValue,Toast.LENGTH_LONG).show();
        startActivity(intent2);

    }
}
