package com.imscreed.imgurclient.view.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.imscreed.imgurclient.R;
import com.imscreed.imgurclient.databinding.ActivityDetailBinding;
import com.imscreed.imgurclient.model.ImgurPost;
import com.imscreed.imgurclient.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private static String IMGUR_POST_KEY = "IMGUR_POST_KEY";
    private ActivityDetailBinding activityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        getExtrasFromIntent();
    }
    private void getExtrasFromIntent() {
        ImgurPost imgurPost = (ImgurPost) getIntent().getParcelableExtra(IMGUR_POST_KEY);
        DetailViewModel detailViewModel = new DetailViewModel(imgurPost);
        activityDetailBinding.setDetailViewModel(detailViewModel);
    }

    public static Intent launch(Context context, ImgurPost imgurPost) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(IMGUR_POST_KEY, imgurPost);
        return intent;
    }
}
