package com.ydh.yudemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.ydh.yudemo.frame.database.db.DaoMaster;
import com.ydh.yudemo.frame.database.db.DaoSession;

import org.litepal.LitePalApplication;
import org.xutils.x;


/**
 * Created by Android on 2018/3/21.
 */

public class App extends LitePalApplication {
    private static App instance;
    public static boolean ISLOG = true;
    public static String rid = "1000796";
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        x.Ext.init(this);
        // addData();
        //初始化数据库
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "zbc_test.db");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取 DaoSession
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static App getInstance() {
        return instance;
    }
}
