package com.ydh.yudemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class TestActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext = this;
        initView();
        addListener();
        addData();
    }

    private void addData() {

    }
    private LinearLayout mRootView;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            float x1 = mRootView.getX();
            float y1 = mRootView.getY();
            Log.e("位置: ", "  getX: " + x1 + " getY: " + y1);

            LinearLayout childAt = (LinearLayout) mRootView.getChildAt(0);
            float x = childAt.getX();
            float y = childAt.getY();
            Log.e("位置: " + 1, "  getX: " + x + " getY: " + y);

            LinearLayout childAt2 = (LinearLayout) childAt.getChildAt(0);
            float x2 = childAt2.getX();
            float y2 = childAt2.getY();
            Log.e("位置: " + 2, "  getX: " + x2 + " getY: " + y2);

            View childAt3 =  childAt2.getChildAt(0);
            float x3 = childAt3.getX();
            float y3 = childAt3.getY();
            Log.e("位置: " + 4, "  getX: " + x3 + " getY: " + y3);
        }
    };
    private void measure(){

    }
    private void initView() {
         mRootView = (LinearLayout) findViewById(R.id.root_view);
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               handler.sendEmptyMessage(1);
           }
       },2000);
    }

    private void addListener() {

    }

}
