package com.mredrock.tashi.finalexam;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;

import java.util.List;

public interface Contract {
    interface show {
        void setList(List<?> data);

        void intent2Player(Object o);

        void isFinishLoadMore(boolean isFinish);

        void isRefresh(boolean isRefresh);

        void addToast(String s, boolean isShort);
    }

    interface load {
        void getNext(String s, Contract.action action);

        void getHotList();

        void getOtherData(String s, String index);

        void getDetailData(String s);

        void getHotNewData();

        void getPageList();
    }

    interface action {
        void loadMore(boolean inLoad);

        void refreshItem(boolean isFinish);

        void onSuccess(OtherData a);

        void onSuccess(List<OtherData.HotListBean> d);

        void onSuccess(DetailData b);

        void onSuccess(HotNewsData c);

        void onSuccess(PageList p);

        void onFailed(String s);
    }

}
