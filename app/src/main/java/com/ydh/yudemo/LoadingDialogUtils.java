package com.ydh.yudemo;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Android on 2018/4/21.
 */

public class LoadingDialogUtils {
    public LoadingDialog mLoadingDialog;

    public LoadingDialogUtils(Context mContext) {
        if (mContext == null) return;
        mLoadingDialog = new LoadingDialog(mContext);
        mLoadingDialog.show();
        WindowManager.LayoutParams lp = mLoadingDialog.getWindow().getAttributes();
        lp.alpha = 0.8f;
        mLoadingDialog.getWindow().setAttributes(lp);
    }

    public void setDimiss() {
        if (mLoadingDialog == null) return;
        if (mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }

    //设置加载文字
    public void setLoadingText(String loadingText) {
        if (mLoadingDialog == null) return;
        mLoadingDialog.setLoadingText(loadingText);
    }

    //带有进程的下载提示
    public void setLoadingProgress(int progress) {
        if (mLoadingDialog == null) return;
        mLoadingDialog.setLoadingText(progress);
    }
}
