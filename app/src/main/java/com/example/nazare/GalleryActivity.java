package com.example.nazare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private TextView tvvideo;
    private TextView tvmusic;
    private TextView tvimage;
    private RecyclerView recyclerView;
    private ArrayList<MusicModel> musicList;
    private ArrayList<VideoModel> videoList;
    private ArrayList<ImageModel> imageList;
    private GalleryAdapter galleryAdapter;
    private static final int REQUEST_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        init();

    }

    private void init() {
        initElements();
        initListener();
        initAction();
    }

    private void initElements() {
        tvmusic = findViewById(R.id.musictext);
        tvvideo = findViewById(R.id.videotext);
        tvimage = findViewById(R.id.imagetext);
        recyclerView = findViewById(R.id.recylerView);
        musicList = new ArrayList<>();
        videoList = new ArrayList<>();
        imageList = new ArrayList<>();

    }

    private void initListener() {

        tvmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryAdapter = new GalleryAdapter(musicList,"music", GalleryActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(GalleryActivity.this));
                recyclerView.setAdapter(galleryAdapter);
            }
        });
        tvvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryAdapter = new GalleryAdapter(videoList,"video", GalleryActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(GalleryActivity.this));
                recyclerView.setAdapter(galleryAdapter);
            }
        });
        tvimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryAdapter = new GalleryAdapter(imageList,"image", GalleryActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(GalleryActivity.this));
                recyclerView.setAdapter(galleryAdapter);
            }
        });
    }

    private void initAction() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        } else {
            loadAudio();
            loadVideo();
            loadImage();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadAudio();
                loadVideo();
                loadImage();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadAudio() {

        Uri uriAudio = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST};

        Cursor cursor = getContentResolver().query(uriAudio, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                MusicModel musicModel = new MusicModel();
                musicModel.setData(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                musicModel.setSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                musicModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                musicModel.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                musicList.add(musicModel);

            }
            cursor.close();

            galleryAdapter = new GalleryAdapter(musicList,"music", GalleryActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(GalleryActivity.this));
            recyclerView.setAdapter(galleryAdapter);

        }

    }

    public void loadVideo() {

        Uri uriVideo = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.Media.DATA, MediaStore.Video.Media.SIZE, MediaStore.Video.Media.TITLE, MediaStore.Video.Media.ARTIST};

        Cursor cursor = getContentResolver().query(uriVideo, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                VideoModel videoModel = new VideoModel();
                videoModel.setData(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                videoModel.setSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
                videoModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)));
                videoModel.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)));
                videoList.add(videoModel);

            }
            cursor.close();
        }

    }

    public void loadImage() {

        Uri uriImage = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE, MediaStore.Images.Media.TITLE};

        Cursor cursor = getContentResolver().query(uriImage, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                ImageModel imageModel = new ImageModel();
                imageModel.setData(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                imageModel.setSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
                imageModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)));
                imageList.add(imageModel);

            }
            cursor.close();
        }
    }



}