package com.imscreed.imgurclient.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.imscreed.imgurclient.R;
import com.imscreed.imgurclient.databinding.PostItemLayoutBinding;
import com.imscreed.imgurclient.model.ImgurPost;
import com.imscreed.imgurclient.viewmodel.ItemPostViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ibrahim on 2018-02-17.
 */

public class ImgurPostAdapter extends RecyclerView.Adapter<ImgurPostAdapter.CustomViewHolder> {

    private List<ImgurPost> posts;
    private String TAG = "CustomAdapter";

    public ImgurPostAdapter(List<ImgurPost> posts) {
        this.posts = Collections.emptyList();
    }

    public void setPosts(List<ImgurPost> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PostItemLayoutBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.post_item_layout, parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final ImgurPost redditPost = posts.get(position);
        holder.bind(redditPost);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private final PostItemLayoutBinding binding;

        public CustomViewHolder(PostItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(ImgurPost post){
            binding.setItemViewModel(new ItemPostViewModel(post, itemView.getContext()));
            Log.d(TAG, "bind: "+binding.getItemViewModel().getTitle());
        }
    }
}
