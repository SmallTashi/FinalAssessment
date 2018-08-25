package com.mredrock.tashi.finalexam.media;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.mredrock.tashi.finalexam.tools.WaveHeader;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TSAudioRecorder implements AudioRecord.OnRecordPositionUpdateListener {
    private int currentSize = 0;

    private int len = 0;

    private DataOutputStream out = null;

    private static File Folder;

    private static TSAudioRecorder recorder;
    //取麦克风的声音
    private final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC;
    //音频采样率，必须为16000或8000
    private final static int AUDIO_SAMPLE_RATE = 16000;
    //采样位数
    private static final int AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    //音道数
    private final static int AUDIO_CHANNEL = AudioFormat.CHANNEL_IN_MONO;

    private AudioRecord audioRecord;

    private int bufferSizeInBytes;

    private ExecutorService executorService;

    private int status = Status.STATUS_NO_READY;

    private File filename;

    private String Path;

    private AudioRecord.OnRecordPositionUpdateListener positionUpdateListener;

    private List<File> filesName;

    private TSAudioRecorder() {
        filesName = new ArrayList<>();

        executorService = Executors.newCachedThreadPool();
    }


    @Override
    public void onMarkerReached(AudioRecord recorder) {

    }

    @Override
    public void onPeriodicNotification(AudioRecord recorder) {

    }

    private static class getInstance {
        static TSAudioRecorder instance = new TSAudioRecorder();
    }

    public static TSAudioRecorder getInstance(File folder) {
        Folder = folder;
        if (recorder == null) {
            recorder = getInstance.instance;
        }
        return recorder;
    }

    private void createAudio(String fileName) {
        bufferSizeInBytes = AudioRecord.getMinBufferSize(AUDIO_SAMPLE_RATE,AUDIO_INPUT,AUDIO_ENCODING);
        audioRecord = new AudioRecord(AUDIO_INPUT,AUDIO_SAMPLE_RATE,  AUDIO_CHANNEL, AUDIO_ENCODING, bufferSizeInBytes);
        this.Path = fileName;
        status = Status.STATUS_READY;
    }
    public void startRecord() {
        createAudio("TenthTime");
        String path = Folder.getPath();
        filename = new File(path + File.separator + Path+".pcm");
        try {
            if (filename.createNewFile()) {
                filesName.add(filename);
                this.out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        audioRecord.startRecording();
        Log.d("AudioRecorder", "===startRecord===" + audioRecord.getState());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                keepData(currentSize);
            }
        });
    }

    public void pauseRecord() {
        Log.d("AudioRecorder", "===pauseRecord===");
        if (status != Status.STATUS_START) {
            throw new IllegalStateException("没有在录音");
        } else {
            audioRecord.stop();
            status = Status.STATUS_PAUSE;
            currentSize = (int) filename.length();
            audioRecord.stop();
        }
    }

    public void recover() {
        if (currentSize == 0) {
        } else {
            Log.d("AudioRecorder", "===recoverRecord===" + audioRecord.getState());
            keepData(len);
        }
    }

    public void stopRecord() {
        if (status == Status.STATUS_NO_READY || status == Status.STATUS_READY) {

        } else {
            Log.d("AudioRecorder", "===stopRecord===" + audioRecord.getState());
            audioRecord.stop();
            release();
            //释放资源
            audioRecord.release();
            audioRecord = null;
            status = Status.STATUS_STOP;
            out = null;
        }
    }

    public void cancel() {
        filesName.remove(filename);
        Path = null;
        if (audioRecord != null) {
            audioRecord.release();
            audioRecord = null;
        }
        status = Status.STATUS_NO_READY;
    }

    private void release() {
        if (filename!=null) {
            WaveHeader header = new WaveHeader(AUDIO_SAMPLE_RATE,AUDIO_CHANNEL,AUDIO_ENCODING);
            Log.d("AudioRecorder","===beginChange===");
            header.pcmToWav(filename.getPath(),filename.getPath()+"change.wav");
        }
    }

    private void keepData(int size) {

        byte[] data = new byte[bufferSizeInBytes];
        int readSize;
        status = Status.STATUS_START;

        try {
            while (status == Status.STATUS_START) {
                readSize = audioRecord.read(data, size, bufferSizeInBytes);
                if (AudioRecord.ERROR_INVALID_OPERATION != readSize && out != null) {
                    out.write(data);
                    len += readSize;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("AudioRecorder",String.valueOf(len));
        try {
            if(out!=null){
                out.close();
                Log.d("AudioRecorder", "===writerClosed===");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
