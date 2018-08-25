package com.mredrock.tashi.finalexam.media;

public interface RecordStreamListener {
   void currentStatus(int status);

   String setName();

   void  addToast(String s,boolean v);
}
