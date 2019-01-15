package com.ydh.yudemo.animation;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;
import com.ydh.yudemo.animation.colorchange.AnimationActivity;
import com.ydh.yudemo.animation.praise.PraiseActivity;
import com.ydh.yudemo.animation.progressbar.ProgressActivity;
import com.ydh.yudemo.animation.testproperty.PropertyAnimationActivity;
import com.ydh.yudemo.animation.testproperty.ShaderActivity;

public class AnimationsActivity extends BaseActivity implements View.OnClickListener {
    private TextView mColorChange;
    private TextView mPraise;
    private TextView mProgress;
    private TextView mProperty;
    private TextView mShader;

    @Override
    public int getInflateId() {
        return R.layout.activity_animations;
    }

    @Override
    public void initView() {
        mColorChange = (TextView) findViewById(R.id.colorChange);
        mPraise = (TextView) findViewById(R.id.praise);
        mProgress= (TextView) findViewById(R.id.progress);
        mProperty = (TextView) findViewById(R.id.property);
        mShader= (TextView)findViewById(R.id.shader);
    }

    @Override
    public void addListener() {
        mColorChange.setOnClickListener(this);
        mPraise.setOnClickListener(this);
        mProgress.setOnClickListener(this);
        mProperty.setOnClickListener(this);
        mShader.setOnClickListener(this);
    }

    @Override
    public void addData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.colorChange:
                startActivity(new Intent(mContext, AnimationActivity.class));
                break;
            case R.id.praise:
                startActivity(new Intent(mContext, PraiseActivity.class));
                break;
            case R.id.progress:
                startActivity(new Intent(mContext, ProgressActivity.class));
                break;
            case R.id.property:
                startActivity(new Intent(mContext, PropertyAnimationActivity.class));
                break;
            case R.id.shader:
                startActivity(new Intent(mContext, ShaderActivity.class));
                break;
        }
    }
}
