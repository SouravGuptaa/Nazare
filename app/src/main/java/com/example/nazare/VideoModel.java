package com.example.nazare;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class VideoModel implements Parcelable {
    private String data;
    private String title;
    private String artist;
    private String size;

    public VideoModel() {

    }

    protected VideoModel(Parcel in) {
        data = in.readString();
        title = in.readString();
        artist = in.readString();
        size = in.readString();
    }

    public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel in) {
            return new VideoModel(in);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(data);
        parcel.writeString(title);
        parcel.writeString(artist);
        parcel.writeString(size);

    }


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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}
