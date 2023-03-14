package com.ydh.module_first;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.ydh.module_first.bean.TreeBean;
import com.ydh.module_first.selfview.TreeView;
import com.ydh.module_first.service.ApiService;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TreeView mTreeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addData();
    }

    private void initView() {
        mTreeView = findViewById(R.id.treeView);
    }

    private void addData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit build = builder.baseUrl("https://api.ixungen.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Flowable<TreeBean> tree = apiService.getTree("11");
        tree.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<TreeBean>() {
                    @Override
                    public void onNext(TreeBean treeBean) {
                        if (treeBean == null || treeBean.getData() == null) {
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
