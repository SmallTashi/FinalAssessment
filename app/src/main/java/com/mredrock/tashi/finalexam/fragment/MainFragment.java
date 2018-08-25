package com.mredrock.tashi.finalexam.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.Presenter;
import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.activity.CommentActivity;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements Contract.show {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private int mFlag=-1;
    private List<OtherData.HotListBean> pageTwoData;
    private List<HotNewsData.ContListBean> pageThreeData;
    private Presenter presenter = new Presenter(this);

    @Override
    public void setList(List<?> data) {
        switch (mFlag){
            case FIRST:
                break;
            case SECOND:
                pageTwoData = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    OtherData.HotListBean bean = (OtherData.HotListBean)data.get(i);
                    pageTwoData.add(bean);
                }
                break;
            case THIRD:
                for (int i = 0; i < data.size(); i++) {
                    HotNewsData.DataListBean a = (HotNewsData.DataListBean)data.get(i);
                    pageThreeData=a.getContList();
                }
                break;
        }
    }

    @Override
    public void intent2Player(Object o) {
        Intent intent = new Intent(getContext(), CommentActivity.class);
        if(o instanceof HotNewsData.ContListBean){
            HotNewsData.ContListBean data = (HotNewsData.ContListBean)o;

            intent.putExtra("flag",data.getContId());
            startActivity(intent);
        }if(o instanceof OtherData.HotListBean){
            OtherData.HotListBean b = (OtherData.HotListBean)o;
            intent.putExtra("flag",b.getContId());
            startActivity(intent);
        }
    }

    @Override
    public void isFinishLoadMore(boolean isFinish) {

    }

    @Override
    public void isRefresh(boolean isRefresh) {

    }

    @Override
    public void addToast(String s, boolean isShort) {

    }


    public static MainFragment getInstance(int msg) {
        Bundle bundle = new Bundle();
        bundle.putInt("flag",msg);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.mFlag = bundle.getInt("flag");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainFragment", "===============onDestroy===============");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MainFragment", "===============onDestroyView===============");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MainFragment", "===============onDetach===============");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MainFragment", "===============onStart===============");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MainFragment", "===============onStop===============");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MainFragment", "===============onPause===============");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initData();
        Log.d("MainFragment", "===============onAttach===============");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MainTabAdapter", "==============="+String.valueOf(mFlag)+"===============");
        initData();
        switch (mFlag) {
            case FIRST:
                return inflater.inflate(R.layout.fragment_page_one, container, false);
            case SECOND:
                return inflater.inflate(R.layout.fragment_page_two, container, false);
            case THIRD:
                return inflater.inflate(R.layout.fragment_page_three, container, false);
        }
        return inflater.inflate(R.layout.fragment_page_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (mFlag) {
            case FIRST:
                initFirst(view);
                break;
            case SECOND:
                initSecond(view);
                break;
            case THIRD:
                initThird(view);
                break;
        }
    }

    private void initFirst(View view) {
        TabLayout top = view.findViewById(R.id.fragment_main_tab);
        ViewPager pager = view.findViewById(R.id.fragment_viewpager);
        for (int i = 0; i < 10; i++) {
            top.addTab(top.newTab());
        }

        presenter.setPageOne(pager, top, getChildFragmentManager());
    }

    private void initSecond(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.page_two_recycler);
        presenter.setPageTwo(recyclerView, getContext());
    }

    private void initThird(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.page_three_recycler);
        presenter.setPageThree(recyclerView, getContext());
    }

}
