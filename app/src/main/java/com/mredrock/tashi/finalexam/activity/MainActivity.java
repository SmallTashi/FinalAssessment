package com.mredrock.tashi.finalexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.adapter.MainTabAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainTabAdapter.onSelect {
    @BindView(R.id.testRecorder)
    Button testRecorder;
    private TabLayout bottom;
    private ImageButton paike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottom = findViewById(R.id.tab_bottom);
        paike = findViewById(R.id.paike_button);
        paike.setVisibility(View.GONE);
        testRecorder.setVisibility(View.VISIBLE);
        testRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestRecorderActivity.class);
                startActivity(intent);
            }
        });
        new MainTabAdapter(this, this, getSupportFragmentManager(), bottom);
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
