package com.imscreed.imgurclient.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.imscreed.imgurclient.di.components.DaggerNetworkComponent;
import com.imscreed.imgurclient.di.components.NetworkComponent;
import com.imscreed.imgurclient.model.ImgurData;
import com.imscreed.imgurclient.model.ImgurPost;
import com.imscreed.imgurclient.services.ImgurService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ibrahim on 2018-02-19.
 */

public class PostViewModel {
    private Context context;
    /**
     * Provides data to the views
     * Handles everything related to network calls and notify the views
     * */

    public ObservableInt progressBar;
    private ImgurService imgurService;
    private List<ImgurPost> posts = new ArrayList<>();
    private String TAG = "PostViewModel";
    private RemoteDataListener mRemoteDataListener;

    public List<ImgurPost> getPosts() {
        return posts;
    }

    public PostViewModel(@NonNull Context context) {
        this.context = context;
        progressBar = new ObservableInt(View.GONE);
        NetworkComponent networkComponent = DaggerNetworkComponent.builder().build();
        imgurService = networkComponent.provideImgurService();
    }

    public void fetchPostsFromRemote(int page){
        progressBar.set(View.VISIBLE);
        Call<ImgurData> call = imgurService.getData(page);
        call.enqueue(new Callback<ImgurData>() {
            @Override
            public void onResponse(Call<ImgurData> call, Response<ImgurData> response) {
                Log.d(TAG, "onResponse: "+response.body());
                if(response.isSuccessful()){
                    posts.addAll(response.body().posts);
                    Log.d(TAG, "onResponse: "+posts.get(0).title);
                    mRemoteDataListener.onSuccess(posts);
                }
                progressBar.set(View.GONE);
            }

            @Override
            public void onFailure(Call<ImgurData> call, Throwable t) {
                t.printStackTrace();
                progressBar.set(View.GONE);
                mRemoteDataListener.onFailure();
            }
        });
    }

    public void setRemoteDataListener(RemoteDataListener remoteDataListener) {
        this.mRemoteDataListener = remoteDataListener;
    }

    public interface RemoteDataListener {
        public void onSuccess(List<ImgurPost> imgurPosts);
        public void onFailure();
    }
}
