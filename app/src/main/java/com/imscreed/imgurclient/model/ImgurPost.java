package com.imscreed.imgurclient.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim on 2018-02-19.
 */

public class ImgurPost implements Parcelable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("images")
    @Expose
    public List<ImgurImageData> imageList;
    @SerializedName("link")
    @Expose
    public String link;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeList(this.imageList);
        dest.writeString(this.link);
    }

    public ImgurPost() {
    }

    protected ImgurPost(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.imageList = new ArrayList<ImgurImageData>();
        in.readList(this.imageList, ImgurImageData.class.getClassLoader());
        this.link = in.readString();
    }

    public static final Parcelable.Creator<ImgurPost> CREATOR = new Parcelable.Creator<ImgurPost>() {
        @Override
        public ImgurPost createFromParcel(Parcel source) {
            return new ImgurPost(source);
        }

        @Override
        public ImgurPost[] newArray(int size) {
            return new ImgurPost[size];
        }
    };
}
