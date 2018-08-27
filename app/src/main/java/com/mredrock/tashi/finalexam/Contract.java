package com.mredrock.tashi.finalexam;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;

import java.util.List;

public interface Contract {
    interface show {

        void intent2Player(String o);

        void addToast(String s, boolean isShort);
    }

    interface load {


        void getDetailData(String s);
        }

    interface action {
        void onSuccess(DetailData b);

        void onFailed(String s);
    }

}
