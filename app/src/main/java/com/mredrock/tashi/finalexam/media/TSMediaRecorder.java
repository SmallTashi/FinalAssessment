package com.mredrock.tashi.finalexam.media;

import android.media.MediaRecorder;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class TSMediaRecorder {
    private MediaRecorder mediaRecorder = null;
    private String fileName;
    private String audioSavaDir ;
    private String filePath;

    public void startRecord(){
        if(mediaRecorder==null){
            mediaRecorder = new MediaRecorder();
        }
        try {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            fileName = DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA))+".m4a";


        }catch (IllegalStateException e){}
    }

}
