package com.example.nazare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {
    private VideoView videoView;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        init();

    }
    private void init() {
        initElements();
        getIntentValues();
        bindData();

    }

    private void getIntentValues() {
        data = getIntent().getStringExtra("data");
    }

    private void initElements() {
        videoView = findViewById(R.id.videoView);

    }

    public  void bindData() {
        videoView.setVideoPath(data);
        videoView.start();

    }



}