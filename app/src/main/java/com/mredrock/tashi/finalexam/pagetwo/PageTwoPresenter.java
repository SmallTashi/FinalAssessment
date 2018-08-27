package com.mredrock.tashi.finalexam.pagetwo;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mredrock.tashi.finalexam.Presenter;
import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class PageTwoPresenter extends Presenter implements PageTwoContract.callback {
    private RecyclerView twoRecycler;
    private Context twoContext;
    private OtherData otherData;

    private String index;
    public PageTwoContract.getData getData = new PageTwoModel(this);
    private List<OtherData.ContListBean> hotList = new ArrayList<>();
    private PageRecyclerAdapter twoAdapter;
    private PageRecyclerAdapter.OnItemClickListener clickListener;
    public PageTwoContract.pageShow show;
    public PageTwoPresenter(PageTwoContract.pageShow s) {

        this.show = s;
        clickListener = new PageRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String s) {
                show.intent2Player(s);
            }

            @Override
            public void askCamora() {
                show.askCamora();
            }

        };
    }
    public void refresh() {
        getData.Refresh("世界");
    }

    @Override
    public void LoadMore(OtherData data) {
     twoAdapter.loadMoreTwo(this,data);
    }
    public void LoadMoreTwo() {
        if(otherData!=null){
            getData.LoadMore(otherData.getNextUrl());
        }else {
            show.addToast("没有更多数据了",false);
        }
    }
    @Override
    public void Refresh(OtherData data) {
        this.otherData = data;
        twoAdapter.refreshTwo(this,data);
    }

    public void setPageTwo(RecyclerView pageTwo, Context context) {
        this.twoRecycler = pageTwo;
        this.twoContext = context;
        if (this.hotList.size()!=0) {
            LinearLayoutManager manager = new LinearLayoutManager(twoContext);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            twoRecycler.setLayoutManager(manager);
            twoRecycler.setAdapter(twoAdapter);
            twoAdapter.setOnItemClickListener(clickListener);
        } else {
            getData.initData("10");
        }
    }

    @Override
    public void onSuccess(OtherData d) {
        this.otherData = d;
        this.hotList = d.getContList();
        if(otherData!=null){
            hotList.addAll(otherData.getContList());
        }
        LinearLayoutManager manager = new LinearLayoutManager(twoContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        twoRecycler.setLayoutManager(manager);
        twoAdapter = new PageRecyclerAdapter(hotList, PageRecyclerAdapter.PAGE_TWO);
        twoRecycler.setAdapter(twoAdapter);
        twoAdapter.setOnItemClickListener(clickListener);
    }

    @Override
    public void isFinish() {
        show.isFinish();
    }

    @Override
    public void noMoreData() {
        show.addToast("没有更多数据了",false);
    }



}
