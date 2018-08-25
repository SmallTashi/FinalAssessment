package com.mredrock.tashi.finalexam.tools;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public static API getAPi(){
        return  new Retrofit.Builder()
                .baseUrl(API.BASE_LI)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(API.class);
    }
}
