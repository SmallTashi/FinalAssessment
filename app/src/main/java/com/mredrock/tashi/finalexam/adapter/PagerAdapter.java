package com.mredrock.tashi.finalexam.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mredrock.tashi.finalexam.fragment.PageFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private static List<String> name = new ArrayList<>();

    private static List<PageFragment> fragments = new ArrayList<>();

    private static PagerAdapter instance ;

    public static PagerAdapter getInstance(FragmentManager fragmentManager){
        if(instance==null){
            instance = new PagerAdapter(fragmentManager);
        }
        return instance;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        name.add("万象");
        name.add("精选");
        name.add("生活");
        name.add("新知");
        name.add("社会");
        name.add("科技");
        name.add("娱乐");
        name.add("美食");
        name.add("音乐");
        name.add("世界");
        for (int i = 0; i < name.size(); i++) {
            fragments.add(new PageFragment());
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name.get(position);
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
