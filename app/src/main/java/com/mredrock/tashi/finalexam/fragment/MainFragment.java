package com.mredrock.tashi.finalexam.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.activity.CommentActivity;
import com.mredrock.tashi.finalexam.activity.MainActivity;
import com.mredrock.tashi.finalexam.pageone.PageOneContract;
import com.mredrock.tashi.finalexam.pageone.PageOnePresenter;
import com.mredrock.tashi.finalexam.pagethree.PageThreeContract;
import com.mredrock.tashi.finalexam.pagethree.PageThreePresenter;
import com.mredrock.tashi.finalexam.pagetwo.PageTwoContract;
import com.mredrock.tashi.finalexam.pagetwo.PageTwoPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    private int mFlag = -1;

    public static MainFragment getInstance(int msg) {
        Bundle bundle = new Bundle();
        bundle.putInt("flag", msg);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
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

        Log.d("MainFragment", "===============onAttach===============");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MainTabAdapter", "===============" + String.valueOf(mFlag) + "===============");
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.mFlag = bundle.getInt("flag");
        }
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
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
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
        PageOnePresenter one= new PageOnePresenter(new PageOneContract.pageOneShow() {
            @Override
            public void intent2Player(String contId) {
                Intent intent = new Intent(getContext(),CommentActivity.class);
                intent.putExtra("flag",contId);
                startActivity(intent);
            }

            @Override
            public boolean isFinish() {
                return true;
            }

            @Override
            public void addToast(String s, boolean isShort) {
                if (isShort) {
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }

        });
        TabLayout top = view.findViewById(R.id.fragment_main_tab);
        ViewPager pager = view.findViewById(R.id.fragment_viewpager);
        for (int i = 0; i < 10; i++) {
            top.addTab(top.newTab());
        }
        one.setPage(pager, top, getChildFragmentManager());
    }

    private void initSecond(View view) {
        final PageTwoPresenter two = new PageTwoPresenter(new PageTwoContract.pageShow() {
            @Override
            public void intent2Player(String s) {
                Intent intent = new Intent(getContext(),CommentActivity.class);
                intent.putExtra("flag",s);
                startActivity(intent);
            }
            @Override
            public boolean isFinish() {
                return true;
            }

            @Override
            public void askCamora() {
                Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
                //好使
                intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,10485760L);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
                startActivityForResult(intent, MainActivity.VIDEO_CAPTURE);
            }


            @Override
            public void addToast(String s, boolean isShort) {
                if (isShort) {
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        }) ;
        final RecyclerView recyclerView = view.findViewById(R.id.page_two_recycler);
        two.setPageTwo(recyclerView, getContext());
        SmartRefreshLayout refreshLayout = view.findViewById(R.id.page_two_refresh);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                two.LoadMoreTwo();
                refreshLayout.finishLoadMore(200,two.show.isFinish(),false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                two.refresh();
                refreshLayout.finishRefresh(200, true);
            }
        });
    }

    private void initThird(View view) {
        final PageThreePresenter three = new PageThreePresenter(new PageThreeContract.pageShow() {
            @Override
            public void intent2Player(String o) {
                Intent intent = new Intent(getContext(),CommentActivity.class);
                intent.putExtra("flag",o);
                startActivity(intent);
            }

            @Override
            public boolean isFinish() {
                return true;
            }

            @Override
            public void addToast(String s, boolean isShort) {
                if (isShort) {
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.page_three_recycler);
        three.setPageThree(recyclerView, getContext());
        SmartRefreshLayout refreshLayout = view.findViewById(R.id.page_three_refresh);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                three.LoadMoreThree();
                    refreshLayout.finishLoadMore(200, true,false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                three.refreshThree();
                refreshLayout.finishRefresh(200,three.show.isFinish());
            }
        });
    }

}
