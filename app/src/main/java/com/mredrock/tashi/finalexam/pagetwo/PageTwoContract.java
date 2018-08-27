package com.mredrock.tashi.finalexam.pagetwo;

import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.data.OtherData;

public interface PageTwoContract extends Contract {
    interface pageShow{
        void intent2Player(String s);
        boolean isFinish();
        void askCamora();
        void addToast(String s, boolean isShort);
    }
    interface callback{
        void onSuccess(OtherData d);
        void isFinish();
        void onFailed(String s);
        void LoadMore(OtherData data);
        void Refresh(OtherData data);
        void noMoreData();
    }
    interface getData{
        void initData(String s);
        void Refresh(String s);
        void LoadMore(String s);

    }
}
