package com.ydh.yudemo.selfview;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.ydh.yudemo.R;
import com.ydh.yudemo.selfview.finger.FingerActivity;

public class SelfViewActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_view);
        findViewById(R.id.finger).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finger:
                startActivity(FingerActivity.class);
                break;
        }
    }

    private void startActivity(Class<? extends AppCompatActivity> classs) {
        Intent intent = new Intent(this, classs);
        startActivity(intent);
    }
}
