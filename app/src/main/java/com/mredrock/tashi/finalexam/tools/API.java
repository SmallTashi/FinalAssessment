package com.mredrock.tashi.finalexam.tools;

import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.data.PageList;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

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
    Call<PageList> getTopPageList(@Header("X-Serial-Num:") String s);      //列表获取

    @GET("home.jsp?lastLikeIds=1063871%2C1063985%2C1064069%2C1064123%2C1064078%2C1064186%2C1062372%2C1064164%2C1064081%2C1064176%2C1064070%2C1064019")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<HotNewsData> getHotNews();          //头条

    @GET
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<OtherData> getNext(@Url String s);


    @FormUrlEncoded
    @POST("getCategoryConts.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<OtherData> getCategoryTag(@FieldMap Map<String, String> map);      //分类下内容

    @FormUrlEncoded
    @POST("getCategoryConts.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<OtherData> getHotList(@FieldMap Map<String, String> map);      //分类下内容

    @GET("content.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<DetailData> getDetail(@Query("contId")String s);        //从头条 json 中 contId 字段获取到的值

    @GET("content.jsp")
    @Headers({"X-Channel-Code:official","X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0","X-Platform-Version:5.0",
            "X-User-ID: "})
    Call<DetailData> getURL(@Query("contId")String s);

}
