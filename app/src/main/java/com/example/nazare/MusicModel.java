package com.example.nazare;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicModel implements Parcelable {
    private String data;
    private String title;
    private String artist;
    private String album;
    private String track;
    private String size;

    public MusicModel() {
        // Default constructor
    }

    protected MusicModel(Parcel in) {
        data = in.readString();
        title = in.readString();
        artist = in.readString();
        album = in.readString();
        track = in.readString();
        size = in.readString();
    }

    public static final Creator<MusicModel> CREATOR = new Creator<MusicModel>() {
        @Override
        public MusicModel createFromParcel(Parcel in) {
            return new MusicModel(in);
        }

        @Override
        public MusicModel[] newArray(int size) {
            return new MusicModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeString(track);
        dest.writeString(size);
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}


