package com.ydh.yudemo.retrofitrxjava;

import com.ydh.yudemo.retrofitrxjava.bean.User;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by UI on 2018/9/27.
 */

public interface TestService {
    @GET("v1/cms/getlist")
    Call<User> getUser(@QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/")
    Call<User.DataBean> getDataBean(@FieldMap Map<String, String> params);

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
