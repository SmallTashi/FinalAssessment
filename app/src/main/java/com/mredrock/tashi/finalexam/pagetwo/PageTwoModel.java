package com.mredrock.tashi.finalexam.pagetwo;

import com.mredrock.tashi.finalexam.adapter.PageRecyclerAdapter;
import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.tools.API;
import com.mredrock.tashi.finalexam.tools.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageTwoModel implements PageTwoContract.getData {
    private PageTwoContract.callback callback;
    private int index = 0;
    private Map<String,String> map = new HashMap<>();
    private API api = RetrofitManager.getAPi();
    PageTwoModel(PageTwoContract.callback s) {
        this.callback = s;
    }



    @Override
    public void initData(String s) {
        map.put("hotPageidx", String.valueOf(index));
        map.put("categoryId", s);
        Call<OtherData> get = api.getCategoryTag(map);
        get.enqueue(new Callback<OtherData>() {
            @Override
            public void onResponse(Call<OtherData> call, Response<OtherData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OtherData> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
        index++;
    }

    @Override
    public void Refresh(String s) {
        index++;
        if(index>=3){
            callback.noMoreData();
        }else {
            map.put("hotPageidx", String.valueOf(index));
            Call<OtherData> get = api.getCategoryTag(map);
            get.enqueue(new Callback<OtherData>() {
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

    @Override
    public void LoadMore(String s) {
        String url = s.substring(36, s.length());
        Call<OtherData> data = api.getNext(url);
        data.enqueue(new Callback<OtherData>() {
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


}
