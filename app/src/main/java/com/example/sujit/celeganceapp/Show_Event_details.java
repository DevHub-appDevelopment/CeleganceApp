package com.example.sujit.celeganceapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.location.SettingInjectorService;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
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
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__event_details);
        Intent intent= getIntent();
        event_number = intent.getIntExtra("event_number",0);
        //Toast.makeText(this,event_number+" received",Toast.LENGTH_LONG).show();

        event_name = findViewById(R.id.EventName);
        event_description = (TextView)findViewById(R.id.description);

        String [] description = getResources().getStringArray(R.array.Description);
         events = getResources().getStringArray(R.array.Events);

        event_name.setText(events[event_number]);
        passingValue = events[event_number];
        event_description.setText(description[event_number]);
        event_description.setMovementMethod(new ScrollingMovementMethod());

        notify_switch =findViewById(R.id.notify);

        //to store the data of notification sending time
        //we have to use either sqlLite or firebase

            Toast.makeText(this,"you got a notification",Toast.LENGTH_LONG).show();
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.common_google_signin_btn_icon_light)
                            .setContentTitle("Event Reminder")
                            .setVibrate(new long[]{500,500})
                            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                            .setContentText(events[event_number] + " event is going to start now");
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(event_number, builder.build());

    }
    public void register(View view)
    {
        Snackbar snack = Snackbar.make(view ," method is invoked",Snackbar.LENGTH_SHORT);
        snack.show();
//        mAuth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
       // if(currentUser == null) {
//            Intent intent = new Intent(this, RegistrationClientActivity.class);
//            intent.putExtra("EventName",events[event_number]);
//            startActivity(intent);
//        }
//        else
//        {
//            Snackbar snackbar = Snackbar.make(view ," Registration Complete",Snackbar.LENGTH_LONG);
//            snackbar.show();
//        }

        //testing
        Intent intent2 = new Intent(this, RegistrationClientActivity.class);
           intent2.putExtra("EventName",passingValue);
           startActivity(intent2);
    }
}
