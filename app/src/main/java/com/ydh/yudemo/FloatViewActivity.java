package com.ydh.yudemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FloatViewActivity extends AppCompatActivity {
    private TextView mTuoDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view);
        initView();
        addListener();
    }

    private void initView() {
        mTuoDong = (TextView) findViewById(R.id.tuodong);
    }

    private void addListener() {
       mTuoDong.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(FloatViewActivity.this,"拖动",Toast.LENGTH_SHORT).show();
           }
       });
    }
}
