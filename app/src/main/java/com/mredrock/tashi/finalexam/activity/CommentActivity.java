package com.mredrock.tashi.finalexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.Presenter;
import com.mredrock.tashi.finalexam.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;

public class CommentActivity extends BaseActivity implements Contract.show{
    private Presenter presenter = new Presenter(this);
    private String contId;
    @BindView(R.id.JZVideoPlayerStandard)
    cn.jzvd.JZVideoPlayerStandard player;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
       contId = intent.getStringExtra("flag");
        presenter.setComment(this,recyclerView,contId);
    }

    @Override
    public void onBackPressed() {
        if(JZVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void setList(List<?> data) {

    }

    @Override
    public void intent2Player(Object o) {

    }

    @Override
    public void isFinishLoadMore(boolean isFinish) {

    }

    @Override
    public void isRefresh(boolean isRefresh) {

    }

    @Override
    public void addToast(String s, boolean isShort) {

    }
}
