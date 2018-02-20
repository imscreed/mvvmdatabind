package com.imscreed.imgurclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibrahim on 2018-02-19.
 */

public class ImgurImageData implements Parcelable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("description")
    @Expose
    public String description;
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
        dest.writeString(this.description);
        dest.writeString(this.link);
    }

    public ImgurImageData() {
    }

    protected ImgurImageData(Parcel in) {
        this.id = in.readString();
        this.description = in.readString();
        this.link = in.readString();
    }

    public static final Parcelable.Creator<ImgurImageData> CREATOR = new Parcelable.Creator<ImgurImageData>() {
        @Override
        public ImgurImageData createFromParcel(Parcel source) {
            return new ImgurImageData(source);
        }

        @Override
        public ImgurImageData[] newArray(int size) {
            return new ImgurImageData[size];
        }
    };
}
