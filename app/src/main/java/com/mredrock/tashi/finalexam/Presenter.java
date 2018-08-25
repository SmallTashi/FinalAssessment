package com.mredrock.tashi.finalexam;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.adapter.PagerAdapter;
import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;

import java.util.List;

public class Presenter implements Contract.action {

    private OtherData otherData;
    private PageList pageList;
    private Contract.load get = new Model(this);
    private Contract.show show;

    private RecyclerView oneRecycler;
    private Context oneContext;
    private ViewPager onePager;
    private TabLayout oneTab;
    private FragmentManager oneManager;
    private HotNewsData hotNewsData;
    private DetailData detailData;
    private PageRecyclerAdapter oneAdapter;

    private RecyclerView twoRecycler;
    private Context twoContext;
    private List<OtherData.HotListBean> hotList;
    private PageRecyclerAdapter twoAdapter;

    private RecyclerView commentRecycler;
    private Context commentContext;
    private PageRecyclerAdapter commentAdapter;

    private RecyclerView threeRecycler;
    private Context threeContext;
    private PageRecyclerAdapter threeAdapter;


    public Presenter(Contract.show s) {
        this.show = s;
    }

    public void setPageTwo(RecyclerView pageTwo, Context context) {
        this.twoRecycler = pageTwo;
        this.twoContext = context;
        if (this.hotList != null) {
            LinearLayoutManager manager = new LinearLayoutManager(twoContext);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            twoRecycler.setLayoutManager(manager);
            twoRecycler.setAdapter(twoAdapter);
        } else {
            get.getHotList();
        }
    }


    public void setPageOne(ViewPager pager, TabLayout top, FragmentManager manager) {
        this.onePager = pager;
        this.oneTab = top;
        this.oneManager = manager;

        if (this.pageList != null) {
            onePager.setAdapter(PagerAdapter.getInstance(oneManager, pageList.getCategoryList()));
            oneTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(onePager));
            onePager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(oneTab));
            oneTab.setupWithViewPager(onePager);
        } else {
            get.getPageList();
        }
    }

    public void setMainAdapter(Context context, RecyclerView recycler, String categoryId, String index) {
        this.oneRecycler = recycler;
        this.oneContext = context;
        if (this.otherData != null) {
            LinearLayoutManager manager = new GridLayoutManager(oneContext, 2);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            oneRecycler.setLayoutManager(manager);
        } else {
            get.getOtherData(categoryId, index);
        }

    }

    public void refreshOne(String cateGory, String index) {
        get.getOtherData(cateGory, index);
    }

    public void LoadMoreOne(String cateGory, String index) {
        get.getOtherData(cateGory, index);
    }

    @Override
    public void loadMore(boolean inLoad) {
        show.isFinishLoadMore(inLoad);
    }

    @Override
    public void refreshItem(boolean isFinish) {
        show.isRefresh(isFinish);
    }

    @Override
    public void onSuccess(OtherData a) {
        if (this.otherData == null) {
            this.otherData = a;
            LinearLayoutManager manager = new GridLayoutManager(oneContext, 2);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            oneRecycler.setLayoutManager(manager);
            oneAdapter = new PageRecyclerAdapter(a.getContList(), PageRecyclerAdapter.PAGE_ONE);
            oneRecycler.setAdapter(oneAdapter);
            show.setList(a.getContList());
        } else {
            oneAdapter.refreshList(PageRecyclerAdapter.PAGE_ONE, a.getContList(), this);
        }

    }

    @Override
    public void onSuccess(List<OtherData.HotListBean> d) {
        if (hotList == null) {
            this.hotList = d;
            LinearLayoutManager manager = new LinearLayoutManager(twoContext);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            twoRecycler.setLayoutManager(manager);
            twoAdapter = new PageRecyclerAdapter(hotList, PageRecyclerAdapter.PAGE_TWO);
            twoRecycler.setAdapter(twoAdapter);
            show.setList(hotList);
        } else {
            twoAdapter.refreshList(PageRecyclerAdapter.PAGE_TWO, d, this);
        }

    }


    @Override
    public void onSuccess(DetailData b) {
        if (this.detailData == null) {
            this.detailData = b;
            LinearLayoutManager manager = new LinearLayoutManager(commentContext);
            manager.setOrientation(LinearLayout.VERTICAL);
            commentRecycler.setLayoutManager(manager);
            this.commentAdapter = new PageRecyclerAdapter(detailData.getPostInfo().getChildList(), PageRecyclerAdapter.COMMENT);
            commentRecycler.setAdapter(commentAdapter);
        } else {
            commentAdapter.refreshList(PageRecyclerAdapter.COMMENT, b.getPostInfo().getChildList(), this);
        }

    }

    @Override
    public void onSuccess(HotNewsData c) {
        if (this.hotNewsData == null) {
            this.hotNewsData = c;
            LinearLayoutManager manager = new LinearLayoutManager(threeContext);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            threeRecycler.setLayoutManager(manager);
            this.threeAdapter = new PageRecyclerAdapter(hotNewsData.getDataList(), PageRecyclerAdapter.PAGE_THREE);
            threeRecycler.setAdapter(threeAdapter);
            show.setList(c.getDataList());
        } else {
            threeAdapter.refreshList(PageRecyclerAdapter.PAGE_THREE, c.getDataList(), this);
        }

    }

    @Override
    public void onSuccess(PageList p) {
        this.pageList = p;
        onePager.setAdapter(PagerAdapter.getInstance(oneManager, p.getCategoryList()));
        oneTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(onePager));
        onePager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(oneTab));
        oneTab.setupWithViewPager(onePager);
        show.setList(p.getCategoryList());
    }

    @Override
    public void onFailed(String s) {
        show.addToast(s, false);
    }

    public void setComment(Context commentActivity, RecyclerView recyclerView, String contId) {
        this.commentContext = commentActivity;
        this.commentRecycler = recyclerView;
        if (this.detailData != null) {
            LinearLayoutManager manager = new LinearLayoutManager(commentContext);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            commentRecycler.setLayoutManager(manager);
            commentRecycler.setAdapter(commentAdapter);
        } else {
            get.getDetailData(contId);
        }
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
            get.getHotNewData();
        }
    }
}