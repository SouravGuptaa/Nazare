package com.example.nazare;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.IOException;

public class GalleryViewActivity extends AppCompatActivity {
    private VideoView videoView;
    private String data;
    private String type;
    private ImageView imageView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_view_activity);
        init();

    }
    private void init() {
        initElements();
        getIntentValues();
        if (type.equals("video")){
            bindData(data);
        } else if (type.equals("music")) {
            playMusic(data);
        }else {
            showImage(data);
        }

    }

    private void getIntentValues() {
        data = getIntent().getStringExtra("data");
        type = getIntent().getStringExtra("type");


    }

    private void initElements() {
        videoView = findViewById(R.id.videoView);
        imageView = findViewById( R.id.imageview);

    }
    public void bindData(String data) {
        videoView.setVisibility(View.VISIBLE);
        videoView.setVideoPath(data);
        videoView.start();
    }

    public void showImage(String data) {
        // Load the image using an image loading library like Glide or Picasso
        imageView.setImageURI(Uri.parse(data));
        imageView.setVisibility(View.VISIBLE);

    }

    private void playMusic(String data) {

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(data));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}