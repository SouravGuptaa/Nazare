package com.example.nazare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.IOException;
import java.util.ArrayList;

public class GalleryViewActivity extends AppCompatActivity {

    private VideoView videoView;
    private String data;
    private String type;
    private ImageView imageView;
    private ImageButton btnPrevious;
    private ImageButton btnPause;
    private ImageButton btnNext;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private ArrayList<MusicModel> musicList;
    private ArrayList<VideoModel> videoList;
    private ArrayList<ImageModel> imageList;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_view_activity);
        init();

    }

    private void init() {
        initElements();
        initListener();

        getIntentValues();
        if (type.equals("video")) {
            bindData(data);
        } else if (type.equals("music")) {
            playMusic(data);
        } else {
            showImage(data);
        }

    }

    private void getIntentValues() {
        data = getIntent().getStringExtra("data");
        type = getIntent().getStringExtra("type");
        if (type.equals("music")) {
            musicList = getIntent().getParcelableArrayListExtra("list");
        } else if (type.equals("video")) {
            videoList = getIntent().getParcelableArrayListExtra("list");
        } else {
            imageList = getIntent().getParcelableArrayListExtra("list");
        }
        position = getIntent().getIntExtra("position", 0);

    }

    private void initElements() {
        videoView = findViewById(R.id.videoView);
        imageView = findViewById(R.id.imageview);
        btnPrevious = findViewById(R.id.previous);
        btnPause = findViewById(R.id.pause);
        btnNext = findViewById(R.id.next);
        seekBar = findViewById(R.id.seekbar);
        mediaPlayer = new MediaPlayer();

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

    private void initListener() {
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type.equals("music")) {

                    if (position == 0) {
                        position = musicList.size() - 1;
                    } else {
                        position--;
                    }
                    MusicModel musicModel = musicList.get(position);
                    mediaPlayer.reset();
                    playMusic(musicModel.getData());

                } else if (type.equals("video")) {

                    if (position == 0) {
                        position = videoList.size() - 1;
                    } else {
                        position--;
                    }
                    VideoModel videoModel = videoList.get(position);
                    bindData(videoModel.getData());

                } else if (type.equals("image")) {
                    if (position == 0) {
                        position = imageList.size() - 1;
                    } else {
                        position--;
                    }
                    ImageModel imageModel = imageList.get(position);
                    showImage(imageModel.getData());

                } else {

                }
            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type.equals("music")) {
                    if (position == musicList.size() - 1) {
                        position = 0;
                    } else {
                        position++;
                    }
                    MusicModel musicModel = musicList.get(position);
                    mediaPlayer.reset();
                    playMusic(musicModel.getData());

                } else if (type.equals("video")) {
                    if (position == videoList.size() - 1) {
                        position = 0;
                    } else {
                        position++;
                    }
                    VideoModel videoModel = videoList.get(position);
                    bindData(videoModel.getData());

                } else if (type.equals("image")) {
                    if (position == imageList.size() - 1) {
                        position = 0;
                    } else {
                        position++;
                    }
                    ImageModel imageModel = imageList.get(position);
                    showImage(imageModel.getData());

                }
            }

        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {

                if (type.equals("music")) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        btnPause.setImageDrawable(getResources().getDrawable(R.drawable.baseline_play_arrow_24));
                    } else {
                        mediaPlayer.start();
                        btnPause.setImageDrawable(getResources().getDrawable(R.drawable.baseline_pause_24));
                    }
                } else {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        btnPause.setImageDrawable(getResources().getDrawable(R.drawable.baseline_play_arrow_24));
                    } else {
                        videoView.start();
                        btnPause.setImageDrawable(getResources().getDrawable(R.drawable.baseline_pause_24));
                    }

                }

            }
        });

        seekBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

}