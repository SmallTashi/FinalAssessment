package com.mredrock.tashi.finalexam.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.Presenter;
import com.mredrock.tashi.finalexam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class CommentActivity extends BaseActivity implements Contract.show {

    @BindView(R.id.JZVideoPlayerStandard)
    JZVideoPlayerStandard player;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.emp)
    ImageView emp;
    private Presenter presenter = new Presenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comment);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        String c = intent.getStringExtra("flag");
        presenter.setComment(this, recyclerView, c, player,emp);
    }


    @Override
    public void intent2Player(String s) {
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
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
    public void addToast(String s, boolean isShort) {
        if (isShort) {
            Toast.makeText(CommentActivity.this, s, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(CommentActivity.this, s, Toast.LENGTH_LONG).show();
    }
}

