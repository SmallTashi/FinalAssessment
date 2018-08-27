package com.mredrock.tashi.finalexam.pageone;

import android.util.Log;

import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;
import com.mredrock.tashi.finalexam.tools.API;
import com.mredrock.tashi.finalexam.tools.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageOneModel implements PageOneContract.getData{
    private PageOneContract.callback callback;
    private API api;
    private Map<String, String> map = new HashMap<>();
    private int index =1;
    PageOneModel(PageOneContract.callback s) {
        this.callback = s;
        this.api = RetrofitManager.getAPi();
    }

    @Override
    public void initData(String ca) {
        index++;
        map.put("hotPageidx", String.valueOf(index));
        map.put("categoryId", ca);
        Call<OtherData> bean = api.getCategoryTag(map);
        bean.enqueue(new Callback<OtherData>() {
            @Override
            public void onResponse(Call<OtherData> call, Response<OtherData> response) {
                    callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OtherData> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getLink(String s) {
        Call<DetailData> call = api.getURL(s);
        call.enqueue(new Callback<DetailData>() {
            @Override
            public void onResponse(Call<DetailData> call, Response<DetailData> response) {
                callback.url(response.body().getContent().getVideos().get(0).getUrl());
            }

            @Override
            public void onFailure(Call<DetailData> call, Throwable t) {

            }
        });
    }

    @Override
    public void getPageList() {
        String a = String.valueOf(System.currentTimeMillis() / 1000);
        Call<PageList> bean = api.getTopPageList(a);
        bean.enqueue(new Callback<PageList>() {
            @Override
            public void onResponse(Call<PageList> call, Response<PageList> response) {
                Log.d("Model", "===========Success get pageList=============");
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PageList> call, Throwable t) {
                Log.d("Model", "===========Failed get pageList=============");
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void loadMoreData(String s) {
        index++;
        map.put("hotPageidx", String.valueOf(index));
        Call<OtherData> bean = api.getCategoryTag(map);
        bean.enqueue(new Callback<OtherData>() {
            @Override
            public void onResponse(Call<OtherData> call, Response<OtherData> response) {
                callback.LoadMore(response.body());
            }

            @Override
            public void onFailure(Call<OtherData> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void refreshData(String s) {
        String url = s.substring(36, s.length());
        Call<OtherData> data = api.getNext(url);
        data.enqueue(new Callback<OtherData>() {
            @Override
            public void onResponse(Call<OtherData> call, Response<OtherData> response) {
                callback.Refresh(response.body());
            }

            @Override
            public void onFailure(Call<OtherData> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
