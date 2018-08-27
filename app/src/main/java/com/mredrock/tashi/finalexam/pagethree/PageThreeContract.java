package com.mredrock.tashi.finalexam.pagethree;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;

public interface PageThreeContract{
    interface pageShow {
        void intent2Player(String s);
        boolean isFinish();
        void addToast(String s, boolean isShort);
    }
    interface getData{
        void initData();
        //刷新
        void refreshData();

        void LoadMore();

        void getDetailData(String s);
    }

    interface callback{
        String get(DetailData b);
        void isFinish();
        void LoadMore(HotNewsData data);
        void Refresh(HotNewsData data);
        void onSuccess(HotNewsData a);
        void onFailed(String s);
        void noMoreData();

        void url(String url);
    }

}
