package com.mredrock.tashi.finalexam.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.tashi.finalexam.R;

import java.net.MalformedURLException;
import java.net.URL;

import cn.jzvd.JZVideoPlayer;

public class PageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int flag;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live, parent, false);
        return new ViewHolderPool.PageOneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderPool.PageOneViewHolder item = (ViewHolderPool.PageOneViewHolder)holder;
        item.videoPlayerStandard.setUp("http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv",JZVideoPlayer.SCREEN_WINDOW_NORMAL,"测试视频");
        item.title.setText("测试测试测试测试测试测试测试测试测试测试");
        item.detail.setText("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
