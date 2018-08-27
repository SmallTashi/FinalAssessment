package com.mredrock.tashi.finalexam;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class DownloadService extends Service {
    private Binder binder = new DownloadBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    class DownloadBinder extends Binder{
        public void startDCache(){}

        public void cancle(){}

        public int getFileSize(){
            return 0;
        }
    }
}
