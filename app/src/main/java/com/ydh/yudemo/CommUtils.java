
package com.ydh.yudemo;

import android.content.Context;

/**
 * Created by Android on 2018/4/3.
 */

public class CommUtils {
    private static CommUtils commUtils;
    private Context mContext;
    private CommUtils(Context mContext){
        this.mContext=mContext;
    }
    public static CommUtils getInstance(Context mContext){
        synchronized (CommUtils.class){
            if (commUtils==null){
                commUtils= new CommUtils(mContext);
            }
        }
        return commUtils;
    }
}
