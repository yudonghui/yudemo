package com.ydh.yudemo.circle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.ydh.yudemo.R;

public class CircleViewActivity extends AppCompatActivity {
    private CirclerView mCircleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);
        mCircleView= (CirclerView) findViewById(R.id.circleView);
    }
    public void startClick(View view){
        mCircleView.start();
    }
}
