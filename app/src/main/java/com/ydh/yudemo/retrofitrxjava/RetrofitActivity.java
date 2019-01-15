package com.ydh.yudemo.retrofitrxjava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;
import com.ydh.yudemo.retrofitrxjava.bean.User;
import com.ydh.yudemo.retrofitrxjava.dagger.DaggerActivity;
import com.ydh.yudemo.retrofitrxjava.netsubscribe.MovieSubscribe;
import com.ydh.yudemo.utils.FileUtils;
import com.ydh.yudemo.utils.LogUtils;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseActivity {


    @Override
    public int getInflateId() {
        return R.layout.activity_retrofit;
    }

    @Override
    public void initView() {

    }

    @Override
    public void addListener() {

    }

    @Override
    public void addData() {

    }

    public void button(View view) {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("sort", "0");
        params.put("type", "glean");
        params.put("limit", "5");
        RetrofitHelper.getService().getUser(params).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User body = response.body();
                LogUtils.error(body.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void download(View view) {
        final TestService testService = RetrofitHelper.getService();
        testService.downloadFile("https://www.ixungen.cn/download/xungen.apk").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                //建立一个文件
                final File file = FileUtils.createFile(RetrofitActivity.this);
                final ProgressDialog progressDialog = new ProgressDialog(RetrofitActivity.this);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("努力下载中......");
                progressDialog.show();
                progressDialog.setMax(100);
                //下载文件放在子线程
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //保存到本地
                        FileUtils.writeFile2Disk(response, file, new HttpCallBack() {
                            @Override
                            public void onLoading(final long current, final long total) { /** * 更新进度条 */
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        LogUtils.error("current: " + current + " total：" + total);
                                        int cur = (int) (current * 100 / total);
                                        progressDialog.setProgress(cur);
                                        if (cur == 100) {
                                            Toast.makeText(mContext, "下载成功", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                            //安装apk
                                            Intent intent = new Intent();
                                            //执行动作
                                            intent.setAction(Intent.ACTION_VIEW);
                                            Uri mUri;
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                                //通过FileProvider创建一个content类型的Uri
                                                mUri = FileProvider.getUriForFile(mContext, "com.ruihuo.ixungen.FileProvider", file);
                                                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                                            } else {
                                                mUri = Uri.fromFile(file);
                                                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                                            }
                                            //执行的数据类型
                                            intent.setDataAndType(mUri, "application/vnd.android.package-archive");
                                            mContext.startActivity(intent);
                                            System.exit(0);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }.start();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void RetrofitRxjava(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://api.dev.ixungen.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        HashMap<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("sort", "0");
        params.put("type", "glean");
        params.put("limit", "5");
        apiService.getUser(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<User>() {
                    @Override
                    public void onNext(User user) {
                        Log.e("结果", user.toString());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void testRx(View view) {

    }

    public void dagger(View view) {
        startActivity(new Intent(this, DaggerActivity.class));
    }

    /**
     * Retrofit Rxjava配合使用
     */
    public void RetrofitRxjava() {

        HashMap<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("sort", "0");
        params.put("type", "glean");
        params.put("limit", "5");
        MovieSubscribe.getData(params, new DisposableObserver<User>() {
            @Override
            public void onNext(@NonNull User user) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
