package com.ydh.yudemo.retrofitrxjava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by UI on 2018/9/27.
 */

public class RetrofitHelper {
    private static RetrofitHelper retrofitHelper;
    private TestService mService;
    private ApiService mApiService;

    private RetrofitHelper(int type) {
        if (type==1){
            Retrofit build = new Retrofit.Builder().baseUrl("http://api.dev.ixungen.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService = build.create(TestService.class);
        }else {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(10000, TimeUnit.SECONDS);
            //连接 超时时间
            builder.writeTimeout(10000, TimeUnit.SECONDS);
            //写操作 超时时间
            builder.readTimeout(10000, TimeUnit.SECONDS);
            //读操作 超时时间
            builder.retryOnConnectionFailure(true);//错误重连
            Retrofit build = new Retrofit.Builder().baseUrl("http://api.dev.ixungen.cn/")
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mApiService = build.create(ApiService.class);
        }

    }

    private static RetrofitHelper getInstance() {
        if (retrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper == null) {
                    retrofitHelper = new RetrofitHelper(1);
                }
            }
        }
        return retrofitHelper;
    }
    private static RetrofitHelper getInstanceRx() {
        if (retrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper == null) {
                    retrofitHelper = new RetrofitHelper(2);
                }
            }
        }
        return retrofitHelper;
    }
    public static TestService getService() {
        return getInstance().mService;
    }
    public static ApiService getServiceRx() {
        return getInstanceRx().mApiService;
    }
}
