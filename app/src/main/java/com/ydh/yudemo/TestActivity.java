package com.ydh.yudemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext=this;
        initView();
        addListener();
        addData();
    }

    private void addData() {

    }

    private void initView() {

    }

    private void addListener() {

    }

}
