package com.example.nazare;

import android.widget.ImageView;

public class VideoModel {
    public String data;
    private String title;
    private String size;

    private String image;
    private ImageView imageView;



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

}

