package com.mredrock.tashi.finalexam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.Presenter;
import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.activity.CommentActivity;
import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


//加载各分类详细Item

public class PageFragment extends Fragment implements Contract.show {
    private Presenter p = new Presenter(this);
    @BindView(R.id.page_recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private boolean isRefresh = false;
    private int in = 1;
    private int i = 1;
    private List<OtherData.ContListBean> list;
    private String categoryId;


    public static PageFragment getInstance(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("categoryId", msg);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.categoryId = bundle.getString("categoryId");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RefreshLayout refreshLayout =view.findViewById(R.id.swipeRefresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                p.refreshOne(categoryId,String.valueOf(in));
                refreshLayout.finishRefresh(2000);
                if(isRefresh){
                    refreshLayout.finishRefresh(true);
                }
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                i++;
                p.LoadMoreOne(categoryId,String.valueOf(in+i));
                refreshLayout.finishLoadMore(2000);
                if(isRefresh){
                    refreshLayout.finishLoadMore(true);
                }
            }
        });
        p.setMainAdapter(getContext(), recycler, categoryId, String.valueOf(in));
    }

    @Override
    public void setList(List<?> data) {
        //TODO 保存数据
    }

    @Override
    public void intent2Player(Object o) {
        if(o instanceof OtherData.ContListBean){
            OtherData.ContListBean c = (OtherData.ContListBean)o;
            Intent intent = new Intent(getContext(), CommentActivity.class);
            intent.putExtra("flag",c.getContId());
        }

    }

    @Override
    public void isFinishLoadMore(boolean isFinish) {

    }

    @Override
    public void isRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    @Override
    public void addToast(String s, boolean v) {
        if (v) {
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }
}
