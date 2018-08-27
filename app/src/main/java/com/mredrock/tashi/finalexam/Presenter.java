package com.mredrock.tashi.finalexam;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.data.DetailData;

import cn.jzvd.JZVideoPlayerStandard;

import static cn.jzvd.JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN;

public class Presenter implements Contract.action {
    private Contract.load get = new Model(this);
    private Contract.show show;
    private RecyclerView commentRecycler;
    private Context commentContext;
    private JZVideoPlayerStandard player;
    private ImageView imageView;

    protected Presenter() {
    }

    public Presenter(Contract.show s) {
        this.show = s;
    }

    @Override
    public void onSuccess(DetailData b) {

        LinearLayoutManager manager = new LinearLayoutManager(commentContext);
        manager.setOrientation(LinearLayout.VERTICAL);
        commentRecycler.setLayoutManager(manager);
        if(b.getPostInfo().getChildList().size()==0){
            commentRecycler.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);

        }else {
            commentRecycler.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);

            PageRecyclerAdapter commentAdapter = new PageRecyclerAdapter(b.getPostInfo().getChildList(), PageRecyclerAdapter.COMMENT);
            commentRecycler.setAdapter(commentAdapter);

        }
        player.setUp(b.getContent().getVideos().get(0).getUrl(), SCREEN_WINDOW_FULLSCREEN, b.getContent().getName());
        Glide.with(commentContext)
                .load(b.getContent().getPic())
                .centerCrop()
                .placeholder(commentContext.getResources().getColor(R.color.white))
                .into(player.thumbImageView);

    }

    @Override
    public void onFailed(String s) {
        show.addToast(s, false);
    }

    //加载评论设置adapter
    public void setComment(Context commentActivity, RecyclerView recyclerView, String contId, JZVideoPlayerStandard playerStandard, ImageView imageView) {
        this.commentContext = commentActivity;
        this.commentRecycler = recyclerView;
        this.imageView = imageView;
        this.player = playerStandard;
        get.getDetailData(contId);
    }


}