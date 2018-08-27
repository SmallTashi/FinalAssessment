package com.mredrock.tashi.finalexam.pageone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;

public interface PageOneContract{
    interface callback{
        void onSuccess(PageList p);
        void LoadMore(OtherData data);
        void Refresh(OtherData data);
        void onSuccess(OtherData a);
        void onFailed(String s);
        void noMoreData();
        String url(String s);
        void isFinish();
        void setAdapter(RecyclerView recyclerView, Context context, String category, PageRecyclerAdapter.OnItemClickListener listener);

    }
    interface pageOneShow{
        void intent2Player(String contId);

        boolean isFinish();

        void addToast(String s, boolean isShort);
    }
    interface getData{
        void initData(String ca);

        void getLink(String s);

        void getPageList();
        //加载
        void loadMoreData(String s);
        //刷新
        void refreshData(String s);

    }



}
