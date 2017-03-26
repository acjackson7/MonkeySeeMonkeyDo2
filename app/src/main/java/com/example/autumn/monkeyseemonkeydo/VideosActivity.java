package com.example.autumn.monkeyseemonkeydo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class VideosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        String data = getIntent().getExtras().getString("sign");
        String fname = "";
        String url = "";
        int i = 0;
        while(i < data.length()){
            if(!(data.charAt(i) == ' ')){
                fname += data.charAt(i);
                i++;
            }
            else{
                fname += Character.toString(data.charAt(i+1)).toUpperCase();
                i+=2;
            }
        }

        url = "http://67.205.152.67/" + fname + ".mp4";
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(data);



        VideoView signVideo = (VideoView) findViewById(R.id.videoView5);
        //MediaController mc = new MediaController(this);
        //mc.setAnchorView(signVideo);
        Uri uri = Uri.parse(url);
        //signVideo.setMediaController(mc);
        signVideo.setVideoURI(uri);
        signVideo.start();


        final Button returnButton = (Button) findViewById (R.id.translateAgain);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeScreen = new Intent(v.getContext(),MainActivity.class);
                startActivity(homeScreen);
            }
        });
    }
}
