package com.mredrock.tashi.finalexam.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mredrock.tashi.finalexam.data.PageList;
import com.mredrock.tashi.finalexam.fragment.PageFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private static List<PageList.CategoryListBean> name ;

    private static List<PageFragment> fragments = new ArrayList<>();


    public static PagerAdapter getInstance(FragmentManager fragmentManager,List<PageList.CategoryListBean> n){
        name = n;
        return  new PagerAdapter(fragmentManager);

    }

    private PagerAdapter(FragmentManager fm) {
        super(fm);
        for (int i = 0; i < name.size(); i++) {
            fragments.add(PageFragment.getInstance(name.get(i).getCategoryId()));
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
