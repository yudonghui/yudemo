package com.ydh.module_first.service;

import com.ydh.module_first.bean.TreeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("glean/getClanTreeTwo")
    Flowable<TreeBean> getTree(@Query("familyId") String familyId);
}
