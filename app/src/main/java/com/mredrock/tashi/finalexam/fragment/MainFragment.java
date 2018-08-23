package com.mredrock.tashi.finalexam.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.adapter.PagerAdapter;

public class MainFragment extends Fragment {

    private static int mFlag;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private static MainFragment instance = null;
    private static class getInstance{
        static MainFragment instance = new MainFragment();
    }
    public static MainFragment get(int flag){
        mFlag = flag;
        if(instance==null){
            instance = getInstance.instance;
            return instance;
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (mFlag){
            case FIRST:
                return inflater.inflate(R.layout.fragment_first,container,false);
            case SECOND:
                return inflater.inflate(R.layout.fragment_second,container,false);
            case THIRD:
                return inflater.inflate(R.layout.fragment_third,container,false);
        }
        return inflater.inflate(R.layout.fragment_first,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (mFlag){
            case FIRST:
               initFirst(view);
                break;
            case SECOND:
                break;
            case THIRD:
                break;
        }
    }
    private void initFirst(View view){
        TabLayout top = view.findViewById(R.id.fragment_main_tab);
        ViewPager pager = view.findViewById(R.id.fragment_viewpager);
        for (int i = 0; i < 10; i++) {
            top.addTab(top.newTab());
        }
        pager.setAdapter(PagerAdapter.getInstance(getChildFragmentManager()));
        top.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(top));
        top.setupWithViewPager(pager);
    }

}
