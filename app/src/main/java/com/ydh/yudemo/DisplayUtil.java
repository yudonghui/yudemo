package com.ydh.yudemo;

import android.content.Context;

import java.lang.reflect.Field;

public class DisplayUtil {
    public static int dip2px(Context mContext,double dipValue) {
        float m = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * m + 0.5f);
    }
    public static int getDisplayHeight(Context mContext) {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDisplayWidth(Context mContext) {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取通知栏高度
     *
     * @return 通知栏高度
     */
    public static int getStatusBarHeight(Context mContext) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}
