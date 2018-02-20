package com.imscreed.imgurclient.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imscreed.imgurclient.model.ImgurPost;
import com.imscreed.imgurclient.view.activities.DetailActivity;

/**
 * Created by Ibrahim on 2018-02-19.
 */

public class ItemPostViewModel extends BaseObservable{
    public ImgurPost imgurPost;
    private Context context;

    public ItemPostViewModel(ImgurPost imgurPost, Context context) {
        this.imgurPost = imgurPost;
        this.context = context;
    }

    public void setImgurPost(ImgurPost imgurPost) {
        this.imgurPost = imgurPost;
        notifyChange();
    }

    public String getId(){
        return imgurPost.id;
    }

    public String getTitle(){
        return imgurPost.title;
    }

    //We are going to fetch the first image's link from the list of images
    public String getThumbnail(){
        return imgurPost.imageList != null ? imgurPost.imageList.get(0).link : imgurPost.link;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public void onItemClick(View view){
        context.startActivity(DetailActivity.launch(view.getContext(), imgurPost));
    }

}
