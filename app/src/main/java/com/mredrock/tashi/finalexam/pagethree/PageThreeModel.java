package com.mredrock.tashi.finalexam.pagethree;

import android.util.Log;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.tools.API;
import com.mredrock.tashi.finalexam.tools.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageThreeModel implements PageThreeContract.getData {
    private PageThreeContract.callback callback;
    private API api = RetrofitManager.getAPi();

    PageThreeModel(PageThreeContract.callback s) {
        this.callback = s;
    }

    @Override
    public void LoadMore() {

        Call<HotNewsData> bean = api.getHotNews();
        bean.enqueue(new Callback<HotNewsData>() {
            @Override
            public void onResponse(Call<HotNewsData> call, Response<HotNewsData> response) {
                Log.d("Model", "===========Success get HotNewData=============");
                callback.LoadMore(response.body());
            }

            @Override
            public void onFailure(Call<HotNewsData> call, Throwable t) {
                Log.d("Model", "===========Failed get HotNewData=============");
                callback.onFailed(t.toString());

            }
        });

    }

    @Override
    public void initData() {
        Call<HotNewsData> bean = api.getHotNews();
        bean.enqueue(new Callback<HotNewsData>() {
            @Override
            public void onResponse(Call<HotNewsData> call, Response<HotNewsData> response) {
                Log.d("Model", "===========Success get HotNewData=============");
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<HotNewsData> call, Throwable t) {
                Log.d("Model", "===========Failed get HotNewData=============");
                callback.onFailed(t.toString());

            }
        });
    }

    @Override
    public void refreshData() {
        Call<HotNewsData> bean = api.getHotNews();
        bean.enqueue(new Callback<HotNewsData>() {
            @Override
            public void onResponse(Call<HotNewsData> call, Response<HotNewsData> response) {
                Log.d("Model", "===========Success get HotNewData=============");
                callback.Refresh(response.body());
            }

            @Override
            public void onFailure(Call<HotNewsData> call, Throwable t) {
                Log.d("Model", "===========Failed get HotNewData=============");
                callback.onFailed(t.toString());

            }
        });
    }

    @Override
    public void getDetailData(String s) {
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
}
