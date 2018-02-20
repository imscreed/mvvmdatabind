package com.imscreed.imgurclient.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imscreed.imgurclient.model.ImgurPost;

/**
 * Created by Ibrahim on 2018-02-20.
 */

public class DetailViewModel {
    public ImgurPost imgurPost;

    public DetailViewModel(ImgurPost imgurPost) {
        this.imgurPost = imgurPost;
    }

    public String getId(){
        return imgurPost.id;
    }

    public String getTitle(){
        return imgurPost.title;
    }

    public String getImageForDetail(){
        return imgurPost.imageList != null && imgurPost.imageList.size() != 0 ? imgurPost.imageList.get(0).link : imgurPost.link;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
