package com.ydh.yudemo.retrofitrxjava.netsubscribe;


import com.ydh.yudemo.retrofitrxjava.bean.User;
import com.ydh.yudemo.retrofitrxjava.netutils.HttpMethods;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by 眼神 on 2018/3/27.
 * 建议：把功能模块来分别存放不同的请求方法，比如登录注册类LoginSubscribe、电影类MovieSubscribe
 */

public class MovieSubscribe {
    /**
     * 获取数据
     */
    public static void getData(Map<String, String> map, DisposableObserver<User> subscriber) {
        Observable<User> observable = HttpMethods.getInstance().getHttpApi().getDataForMap(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
}
