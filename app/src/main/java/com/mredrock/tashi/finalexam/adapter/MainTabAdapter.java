package com.mredrock.tashi.finalexam.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainTabAdapter implements TabLayout.OnTabSelectedListener {
    private Context context;
    private List<String> name = new ArrayList<>();

    private List<MainFragment> fragments = new ArrayList<>();
    private MainFragment currentFragment = new MainFragment();

    private List<Drawable> images = new ArrayList<>();
    private FragmentManager manager;
    private MainTabAdapter.onSelect listener;

    public MainTabAdapter(MainTabAdapter.onSelect select, Context context, FragmentManager manager, TabLayout tabLayout) {
        this.context = context;
        this.listener = select;
        listener.isSelected(false);
        tabLayout.addOnTabSelectedListener(this);
        for (int i = 0; i < 3; i++) {
            fragments.add(MainFragment.getInstance(i));
        }
        this.manager = manager;
        name.add("首页");
        name.add("报料");
        name.add("直播");
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(0)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(1)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(2)));
    }

    private View getTabView(int pos) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_bottom_view, null);
        ImageView image = v.findViewById(R.id.tab_image);
        TextView text = v.findViewById(R.id.tab_text);
        images.add(image.getResources().getDrawable(R.drawable.tab_top_sel_unsel));
        images.add(image.getResources().getDrawable(R.drawable.tab_paike_sel_unsel));
        images.add(image.getResources().getDrawable(R.drawable.tab_live_sel_unsel));
        text.setText(name.get(pos));
        image.setSelected(false);
        text.setSelected(false);
        image.setBackground(images.get(pos));
        return v;
    }

    private FragmentTransaction switchFragment(MainFragment targetFragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.hide(currentFragment).add(R.id.fragment, targetFragment);
        } else {
            transaction.hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int pos = tab.getPosition();
        Log.d("tab", String.valueOf(pos));
        tab.getCustomView().findViewById(R.id.tab_image).setSelected(true);
        tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);
        if(pos==1){
            listener.isSelected(true);
        }else {
            listener.isSelected(false);
        }
        switchFragment(fragments.get(pos)).commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getCustomView().findViewById(R.id.tab_image).setSelected(false);
        tab.getCustomView().findViewById(R.id.tab_text).setSelected(false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    public interface onSelect {
        void isSelected(boolean isSelected);
    }

}