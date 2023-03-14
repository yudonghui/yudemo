package com.ydh.yudemo.interview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ydh.yudemo.R;

import java.util.HashMap;

/**
 * Created by ydh on 2020/9/14
 * 面试相关测试
 */
public class InterviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        HashMap<String, String> map = new HashMap<>();
        map.put("","");
    }
}
