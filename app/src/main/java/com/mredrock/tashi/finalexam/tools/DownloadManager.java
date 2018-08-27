package com.mredrock.tashi.finalexam.tools;

public class DownloadManager {
    private String path;
    private String url;
    private long contentLen;
    private volatile long completedLen;

    public DownloadManager(String path,String url){
        this.path = path;
        this.url = url;
    }
    


}
