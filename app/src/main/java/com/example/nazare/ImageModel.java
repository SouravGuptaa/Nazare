package com.example.nazare;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ImageModel implements Parcelable {

    private String title;
    public String data;
    private String size;

    public ImageModel(){

    }

    protected ImageModel(Parcel in) {
        title = in.readString();
        data = in.readString();
        size = in.readString();
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(data);
        parcel.writeString(size);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }



}
