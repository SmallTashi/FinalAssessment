package com.mredrock.tashi.finalexam.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.media.RecordStreamListener;
import com.mredrock.tashi.finalexam.media.TSAudioRecorder;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestRecorderActivity extends AppCompatActivity implements RecordStreamListener {

    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.pause)
    Button pause;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.reLord)
    Button reLord;
    private int flag;
    public boolean isSuccess = false;
    private TSAudioRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recorder);
        ButterKnife.bind(this);
        String s = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(s + File.separator + "TSRecordTest");
        if (!file.exists()) {
            if (file.mkdir()) {
                addToast("CreateFolder", false);
            } else {

            }
        } else {
            file.delete();
        }

        recorder = TSAudioRecorder.getInstance(file);
    }


    @Override
    public void currentStatus(int status) {
        this.flag = status;
    }

    @Override
    public void addToast(String s, boolean v) {
        if (v) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.start, R.id.stop, R.id.pause})
    public void onViewClicked(View view) {
        if (recorder == null) {
            addToast("recorder is null", true);
        } else {
            switch (view.getId()) {
                case R.id.start:
                    recorder.startRecord();
                    break;
                case R.id.stop:
                    recorder.stopRecord();
                    break;
                case R.id.pause:
                    recorder.pauseRecord();
                    break;
                case R.id.cancel:
                    recorder.cancel();
                    break;
                case R.id.reLord:

                    break;
            }
        }
    }
}
