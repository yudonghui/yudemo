package com.ydh.yudemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.ydh.yudemo.widget.GifView;

/**
 * Created by Android on 2018/4/21.
 */

public class LoadingDialog extends AlertDialog{
    private TextView loadingTextView;
    private GifView mGifView;
    private Context mContext;

    public LoadingDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    //一个自定义view和一个textview组成。加载提示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_refresh);
        loadingTextView = (TextView) findViewById(R.id.loading_text);
        mGifView = (GifView) findViewById(R.id.loading_gifview);
        mGifView.setMovieResource(R.raw.loading);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = DisplayUtil.dip2px(getContext(),100);
        lp.height = DisplayUtil.dip2px(getContext(),100);
        getWindow().setAttributes(lp);

    }

    public void setLoadingText(String text) {
        loadingTextView.setText(text);
    }

    public void setLoadingText(int progress) {
        if (loadingTextView != null) {
            loadingTextView.setText("努力加载中:" + progress + "%");
        }
    }

    @Override
    public void dismiss() {
        if (isShowing()) {
            super.dismiss();
        }
    }
}
