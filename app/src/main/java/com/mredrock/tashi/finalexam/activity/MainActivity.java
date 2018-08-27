package com.mredrock.tashi.finalexam.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.adapter.MainTabAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;

public class MainActivity extends BaseActivity implements MainTabAdapter.onSelect {
    public static final int VIDEO_CAPTURE = 0;
    @BindView(R.id.testRecorder)
    Button testRecorder;
    private ImageButton paike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TabLayout bottom = findViewById(R.id.tab_bottom);
        paike = findViewById(R.id.paike_button);
        paike.setVisibility(View.GONE);
        testRecorder.setVisibility(View.GONE);
        testRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestRecorderActivity.class);
                startActivity(intent);
            }
        });

        new MainTabAdapter(this, this, getSupportFragmentManager(), bottom);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==VIDEO_CAPTURE){
            Uri videoUri=data.getData();
        }
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
    public void isSelected(boolean isSelected) {
        if (isSelected) {
            paike.setVisibility(View.VISIBLE);
        } else {
            paike.setVisibility(View.INVISIBLE);
        }
    }
}
