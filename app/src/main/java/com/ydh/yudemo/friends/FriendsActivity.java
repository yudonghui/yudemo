package com.ydh.yudemo.friends;

import android.content.Intent;
import android.view.View;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

public class FriendsActivity extends BaseActivity {

    @Override
    public int getInflateId() {
        return R.layout.activity_friends;
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

    public void image(View view){
        startActivity(new Intent(mContext,DynamicImageActivity.class));
    }
    public void comment(View view){
        startActivity(new Intent(mContext,CommentsActivity.class));
    }
}
