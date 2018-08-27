package com.mredrock.tashi.finalexam;


import android.util.Log;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.tools.API;
import com.mredrock.tashi.finalexam.tools.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.load {
    private Contract.action callback;
    private API api;

    Model(Contract.action s) {
        this.callback = s;
        this.api = RetrofitManager.getAPi();
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


}
