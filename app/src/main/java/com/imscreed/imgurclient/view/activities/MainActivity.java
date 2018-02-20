package com.imscreed.imgurclient.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.imscreed.imgurclient.R;
import com.imscreed.imgurclient.databinding.ActivityMainBinding;
import com.imscreed.imgurclient.model.ImgurPost;
import com.imscreed.imgurclient.view.adapters.ImgurPostAdapter;
import com.imscreed.imgurclient.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityBinding;
    private PostViewModel postViewModel;
    private List<ImgurPost> posts = new ArrayList<>();
    private String TAG = "MainActivity";
    private int page = 0;

    private void initDataBinding(){
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        postViewModel = new PostViewModel(this);
        activityBinding.setPostViewModel(postViewModel);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        postViewModel.setRemoteDataListener(mRemoteDataListener);
        setUpPosts(activityBinding.recyclerView);
        activityBinding.getPostViewModel().fetchPostsFromRemote(page);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUpPosts(RecyclerView recyclerView){
        ImgurPostAdapter adapter = new ImgurPostAdapter(posts);
        recyclerView.setAdapter(adapter);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int totalItems = recyclerView.getLayoutManager().getItemCount()/2;
                Log.d(TAG, "onScrollStateChanged: TOTAL: "+totalItems);
                Log.d(TAG, "onScrollStateChanged: VISIBLE: "+gridLayoutManager.findLastVisibleItemPosition());
                if(totalItems <= gridLayoutManager.findLastVisibleItemPosition() + 5) {
                    page++;
                    activityBinding.getPostViewModel().fetchPostsFromRemote(page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private PostViewModel.RemoteDataListener mRemoteDataListener = new PostViewModel.RemoteDataListener() {
        @Override
        public void onSuccess(List<ImgurPost> imgurPosts) {
            ImgurPostAdapter adapter = (ImgurPostAdapter) activityBinding.recyclerView.getAdapter();
            adapter.setPosts(postViewModel.getPosts());
        }

        @Override
        public void onFailure() {
            Toast.makeText(MainActivity.this, "Failed to get data from server", Toast.LENGTH_SHORT).show();
        }
    };
//
//    @Override
//    public void update(Observable observable, Object o) {
//        if(observable instanceof PostViewModel){
//            ImgurPostAdapter adapter = (ImgurPostAdapter) activityBinding.recyclerView.getAdapter();
//            PostViewModel postViewModel = (PostViewModel) observable;
//            adapter.setPosts(postViewModel.getPosts());
//        }
//    }
}
