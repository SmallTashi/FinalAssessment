package com.mredrock.tashi.finalexam.pagethree;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.Presenter;
import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;

import java.util.List;

public class PageThreePresenter extends Presenter implements PageThreeContract.callback {
    private PageRecyclerAdapter.OnItemClickListener clickListener;
    private PageThreeContract.getData getData  = new PageThreeModel(this);
    private RecyclerView threeRecycler;
    private Context threeContext;
    private List<String> contId;
    private HotNewsData hotNewsData;
    private String id;
    private PageRecyclerAdapter threeAdapter;
    public PageThreeContract.pageShow show;
    public PageThreePresenter(final PageThreeContract.pageShow show){
        this.show = show;
        clickListener = new PageRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String s) {
                show.intent2Player(s);
            }

            @Override
            public void askCamora() {

            }
        };
    }

    @Override
    public String get(DetailData b) {
        return b.getContent().getVideos().get(0).getUrl();
    }

    @Override
    public void isFinish(){
        show.isFinish();
    }

    @Override
    public void onSuccess(HotNewsData c) {
        this.hotNewsData = c;
        LinearLayoutManager manager = new LinearLayoutManager(threeContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        threeRecycler.setLayoutManager(manager);
        this.threeAdapter = new PageRecyclerAdapter(hotNewsData.getDataList(), PageRecyclerAdapter.PAGE_THREE);
        threeRecycler.setAdapter(threeAdapter);
        threeAdapter.setOnItemClickListener(clickListener);
    }
    public void refreshThree() {
        getData.refreshData();
    }

    public void LoadMoreThree() {
        getData.LoadMore();
    }

    @Override
    public void LoadMore(HotNewsData data) {
        threeAdapter.loadMoreThree(this);
    }

    public void setPageThree(RecyclerView recyclerView, Context context) {
        this.threeRecycler = recyclerView;
        this.threeContext = context;
        if (this.hotNewsData != null) {
            LinearLayoutManager manager = new LinearLayoutManager(threeContext);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            threeRecycler.setLayoutManager(manager);
            threeRecycler.setAdapter(threeAdapter);
        } else {
            getData.initData();
        }
    }


    @Override
    public void Refresh(HotNewsData data) {
        threeAdapter.refreshThree(this,data);
    }

    @Override
    public void noMoreData() {
        show.addToast("没有更多数据了",false);
    }

    @Override
    public void url(String url) {

    }

}
