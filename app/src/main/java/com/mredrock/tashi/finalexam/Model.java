package com.mredrock.tashi.finalexam;


import android.util.Log;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;
import com.mredrock.tashi.finalexam.tools.API;
import com.mredrock.tashi.finalexam.tools.RetrofitManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.load {
    private Contract.action callback;
    private API api;
    private Map<String, String> map = new HashMap<>();
    public List<OtherData.HotListBean> hotListBeans;

    Model(Contract.action s) {
        this.callback = s;
        this.api = RetrofitManager.getAPi();
    }


    @Override
    public void getNext(String s, final Contract.action action) {
        String url = s.substring(36, s.length());
        Call<OtherData> data = api.getNext(url);
        data.enqueue(new Callback<OtherData>() {
            @Override
            public void onResponse(Call<OtherData> call, Response<OtherData> response) {
                Log.d("Model", "===========Success get Next=============");
                action.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OtherData> call, Throwable t) {
                Log.d("Model", "===========Failed get Next=============");
                action.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getHotList() {
        Call<OtherData> bean = api.getHotList(map);
        bean.enqueue(new Callback<OtherData>() {
            @Override
            public void onResponse(Call<OtherData> call, Response<OtherData> response) {
                if (hotListBeans != null) {
                    hotListBeans.addAll(response.body().getHotList());
                } else {
                    hotListBeans = response.body().getHotList();
                }
                callback.onSuccess(hotListBeans);
            }

            @Override
            public void onFailure(Call<OtherData> call, Throwable t) {

            }
        });

    }

    @Override
    public void getOtherData(String s, String index) {
        map.put("hotPageidx", index);
        map.put("categoryId", s);
        Call<OtherData> bean = api.getCategoryTag(map);
        bean.enqueue(new Callback<OtherData>() {
            @Override
            public void onResponse(Call<OtherData> call, Response<OtherData> response) {
                Log.d("Model", "===========Success get OtherData=============");
                if (hotListBeans == null) {
                    hotListBeans = new ArrayList<>();
                }
                hotListBeans.addAll(response.body().getHotList());
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OtherData> call, Throwable t) {
                Log.d("Model", "===========Failed get OtherData=============");
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getDetailData(String s) {
        Call<DetailData> bean = api.getDetail(s);
        bean.enqueue(new Callback<DetailData>() {
            @Override
            public void onResponse(Call<DetailData> call, Response<DetailData> response) {
                Log.d("Model", "===========Success get DetailData=============");
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<DetailData> call, Throwable t) {
                Log.d("Model", "===========Failed get DetailData=============");
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void getHotNewData() {
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
}
