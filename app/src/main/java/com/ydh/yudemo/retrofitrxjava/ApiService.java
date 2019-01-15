package com.ydh.yudemo.retrofitrxjava;

import com.ydh.yudemo.retrofitrxjava.bean.User;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by UI on 2018/9/29.
 */

public interface ApiService {
    @GET("v1/cms/getlist")
    Flowable<User> getUser(@QueryMap Map<String,String> params);
}
