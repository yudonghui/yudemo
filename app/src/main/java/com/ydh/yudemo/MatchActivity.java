package com.ydh.yudemo;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.ydh.yudemo.widget.YuTextView;

public class MatchActivity extends AppCompatActivity {
    private Context mContext;
    YuTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        mContext = this;
        mTextView = (YuTextView) findViewById(R.id.textView);
        ScrollTranslationView mTestView = (ScrollTranslationView) findViewById(R.id.scrollTranslationView);
        mTestView.setHardwareAccelerated(mTestView, false);
        mTextView.setHardwareAccelerated(mTextView, false);
        addData();
    }

    private void addData() {
    }

    private void initView() {
    }

}
