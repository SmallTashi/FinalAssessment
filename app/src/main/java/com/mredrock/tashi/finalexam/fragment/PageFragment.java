package com.mredrock.tashi.finalexam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.activity.CommentActivity;
import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.pageone.PageOneContract;
import com.mredrock.tashi.finalexam.pageone.PageOnePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


//加载各分类详细Item

public class PageFragment extends Fragment implements PageOneContract.pageOneShow,PageRecyclerAdapter.OnItemClickListener{
    private PageOnePresenter p = new PageOnePresenter(this);

    @BindView(R.id.page_recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    /**
     *
     */
    private String categoryId;


    public static PageFragment getInstance(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("categoryId", msg);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.categoryId = bundle.getString("categoryId");
        }
        p.setAdapter(recycler,getContext(),categoryId,this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SmartRefreshLayout refreshLayout = view.findViewById(R.id.page_one_refresh);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                p.load(categoryId);
                refreshLayout.finishLoadMore(200);
//                    refreshLayout.finishLoadMore(p.isFinishLoadMore());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                p.refresh();
                refreshLayout.finishRefresh(200);
            }
        });

    }


    @Override
    public void intent2Player(String o) {
        Intent intent = new Intent(getContext(), CommentActivity.class);
        intent.putExtra("flag", o);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public void addToast(String s, boolean v) {
        if (v) {
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(String s) {
        Intent intent = new Intent(getContext(), CommentActivity.class);
        intent.putExtra("flag", s);
        startActivity(intent);
    }

    @Override
    public void askCamora() {

    }

}
