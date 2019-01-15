package com.ydh.yudemo.retrofitrxjava.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ydh.yudemo.R;
import com.ydh.yudemo.utils.LogUtils;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {
    @Inject
    DaggerDemo daggerDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        DaggerUserCompoent.create().inject(this);
        LogUtils.error(daggerDemo.getName());
    }
}
