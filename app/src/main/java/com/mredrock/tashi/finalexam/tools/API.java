package com.mredrock.tashi.finalexam.tools;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.TopPageData;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    String BASE_LI = "http://app.pearvideo.com/clt/jsp/v2/";
    @GET("getCategorys.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<ResponseBody> getTopPageList(@Header("X-Serial-Num:") String s);

    @GET("home.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<TopPageData> getHotNews(@Query("lastLikeIds") String s);

    @FormUrlEncoded
    @POST("getCategoryConts.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<ResponseBody> getCategory(@FieldMap Map<String, String> map);



    @GET("content.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<DetailData> getDetail(@Query("contId")String s);

}
