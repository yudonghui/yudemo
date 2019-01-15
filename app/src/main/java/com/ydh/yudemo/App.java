package com.ydh.yudemo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.litepal.LitePalApplication;
import org.xutils.x;



/**
 * Created by Android on 2018/3/21.
 */

public class App extends LitePalApplication {
    private static App instance;
    public static boolean ISLOG=true;
    public static String rid="1000796";
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        x.Ext.init(this);
       // addData();
    }

    private void addData() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.red_txt);
                return null;
            }
        });
    }
    public static App getInstance() {
        return instance;
    }
}
