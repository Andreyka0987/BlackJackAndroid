package com.example.blackjackapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class EntryActivity extends AppCompatActivity {

    Button btn_start;
    ImageView info_icon;
    VideoView videoView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entry);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btn_start = findViewById(R.id.btn_start);
        info_icon = findViewById(R.id.infoIcon_btn);
        videoView = findViewById(R.id.mainVideo);


        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.charlie_kirky;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!videoView.isPlaying()){
                    videoView.start();
                }
            }
        },1500,500);






        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EntryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        info_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://en.wikipedia.org/wiki/Blackjack");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });


    }
}