package com.ydh.yudemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.yudemo.widget.CountDownView;


public class CountDownActivity extends AppCompatActivity {
    private TextView mStart;
    private CountDownView mCountDownView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        initView();
        addListener();
    }

    private void initView() {
        mStart = (TextView) findViewById(R.id.start);
        mCountDownView = (CountDownView) findViewById(R.id.countDownView);
    }

    private void addListener() {
        mStart.setOnClickListener(StartListener);
        mCountDownView.setCountDownTimerListener(CountDownListener);
        mCountDownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CountDownActivity.this,"跳过",Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener StartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCountDownView.start();
        }
    };
    CountDownView.CountDownTimerListener CountDownListener = new CountDownView.CountDownTimerListener() {
        @Override
        public void onStartCount() {
            Toast.makeText(CountDownActivity.this, "开始了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinishCount() {
            Toast.makeText(CountDownActivity.this, "结束了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownView.stop();
    }
}
