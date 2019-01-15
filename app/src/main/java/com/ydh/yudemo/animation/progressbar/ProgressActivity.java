package com.ydh.yudemo.animation.progressbar;

import android.graphics.Paint;
import android.widget.TextView;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

public class ProgressActivity extends BaseActivity {
    private com.ydh.yudemo.animation.progressbar.CircleBarView mCircleView;
    private TextView mCircleViewTextView;
    private com.ydh.yudemo.animation.progressbar.WaveProgressView mProgressView;


    @Override
    public int getInflateId() {
        return R.layout.activity_progress;
    }

    @Override
    public void initView() {
        mCircleView = (com.ydh.yudemo.animation.progressbar.CircleBarView) findViewById(R.id.circleView);
        mCircleViewTextView = (TextView) findViewById(R.id.circleViewTextView);
        mProgressView = (com.ydh.yudemo.animation.progressbar.WaveProgressView) findViewById(R.id.progressView);
    }

    @Override
    public void addListener() {

    }

    @Override
    public void addData() {
        mCircleView.setTextView(mCircleViewTextView);
        mCircleView.setMaxNum(1000);
        mCircleView.setProgressNum(500, 3000);
        mCircleView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float updateNum, float maxNum) {
                return (int) (updateNum * 100 / maxNum) + "%";
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float updateNum, float maxNum) {

            }
        });


        mProgressView.setMaxNum(1000);
        mProgressView.setProgressNum(500, 3000);
    }
}
