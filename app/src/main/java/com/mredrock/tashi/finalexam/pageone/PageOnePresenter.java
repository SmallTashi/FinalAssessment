package com.mredrock.tashi.finalexam.pageone;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mredrock.tashi.finalexam.Presenter;
import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.adapter.PagerAdapter;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;

import java.util.ArrayList;
import java.util.List;

public class PageOnePresenter implements PageOneContract.callback {
    private PageOneContract.pageOneShow show;
    private OtherData otherData;
    private static PageList pageList;
    private RecyclerView recycler;
    private ViewPager pager;
    private TabLayout tabLayout;
    private PagerAdapter pagerAdapter;
    private FragmentManager manager;
    private PageRecyclerAdapter.OnItemClickListener clickListener;
    private PageRecyclerAdapter adapter;
    private Context context;
    public PageOneContract.getData get = new PageOneModel(this);
    public PageOnePresenter(PageOneContract.pageOneShow s) {
        this.show = s;
    }

    public void refresh(){
        get.refreshData(otherData.getNextUrl());
    }

    public void load(String s){
        get.loadMoreData(s);
    }

    public void setPage(ViewPager pager, TabLayout top, FragmentManager manager) {
        this.pager = pager;
        this.tabLayout = top;
        this.manager = manager;
        if (pageList != null) {
            pager.setAdapter(pagerAdapter);
            adapter.setOnItemClickListener(clickListener);
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setupWithViewPager(pager);
        } else {
            get.getPageList();
        }
    }

    @Override
    public void onSuccess(PageList p) {
        pageList = p;
        this.pagerAdapter=PagerAdapter.getInstance(manager, p.getCategoryList());
        pager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void LoadMore(OtherData data) {
        this.adapter.loadMoreOne(this,data);
    }

    @Override
    public void Refresh(OtherData data) {
        this.adapter.refreshOne(this,data);
         }

    @Override
    public void onSuccess(OtherData a) {
        otherData = a;
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(manager);
        adapter = new PageRecyclerAdapter(a.getContList(), PageRecyclerAdapter.PAGE_ONE);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(clickListener);
    }

    @Override
    public void onFailed(String s) {
        show.addToast(s,false);
    }

    @Override
    public void noMoreData() {
        show.addToast("没有更多数据了",false);
    }

    @Override
    public String url(String s) {
        return s;
    }

    @Override
    public void isFinish() {
        show.isFinish();
    }

    @Override
    public void setAdapter(RecyclerView recyclerView, Context context, String category,PageRecyclerAdapter.OnItemClickListener listener) {
        this.recycler = recyclerView;
        this.context = context;
        this.clickListener = listener;
        if (otherData != null) {
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(manager);
            recycler.setAdapter(adapter);
        } else {
            get.initData(category);
        }
    }
}
