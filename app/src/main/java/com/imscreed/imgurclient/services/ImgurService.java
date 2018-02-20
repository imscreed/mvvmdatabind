package com.imscreed.imgurclient.services;

import com.imscreed.imgurclient.model.ImgurData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Ibrahim on 2018-02-17.
 */

public interface ImgurService {
    @GET("gallery/user/viral/{page}")
    @Headers("Authorization: Client-ID e0d9382b96f900b")
    Call<ImgurData> getData(@Path("page") int page);
}
