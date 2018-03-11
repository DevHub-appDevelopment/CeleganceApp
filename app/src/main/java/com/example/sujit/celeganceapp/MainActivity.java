package com.example.sujit.celeganceapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button inter,intra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        VideoView simpleVideoView = (VideoView)findViewById(R.id.videoView);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Pasajero.otf");
        inter = (Button)findViewById(R.id.interEvents);
        intra = (Button)findViewById(R.id.intraEvents);
        inter.setTypeface(tf);
        intra.setTypeface(tf);

        simpleVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.setLooping(true);
            }
        });

        simpleVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.stars));
        simpleVideoView.start();

    }
    public void intraEvents(View view)
    {
        Intent intent1 =new Intent(this,ClientEventListActivity.class);
        startActivity(intent1);
        finish();

    }
    public void interEvents(View view)
    {
        Intent intent2 =new Intent(this,ClientEventListActivity2.class);
        startActivity(intent2);
        finish();

    }
}
