package com.imscreed.imgurclient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibrahim on 2018-02-19.
 */

public class ImgurData {
    @SerializedName("data")
    @Expose
    public List<ImgurPost> posts;
}
