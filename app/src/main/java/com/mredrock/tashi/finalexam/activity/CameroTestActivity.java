package com.mredrock.tashi.finalexam.activity;

import android.content.Intent;
import android.media.MediaRecorder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.mredrock.tashi.finalexam.R;

public class CameroTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camero_test);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,1048760L);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);

            }
        });
        SurfaceView surfaceView = findViewById(R.id.surfaceView);
    }


}
