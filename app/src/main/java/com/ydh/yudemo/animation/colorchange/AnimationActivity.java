package com.ydh.yudemo.animation.colorchange;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

public class AnimationActivity extends BaseActivity {
    private View mLeftNavigationView, mRightNavigationView;
    private View[] mChildViews;

    @Override
    public int getInflateId() {
        return R.layout.activity_animation;
    }

    @Override
    public void initView() {
        mLeftNavigationView = findViewById(R.id.navigation_left);
        mRightNavigationView = findViewById(R.id.navigation_right);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.root_view);
        mChildViews = new View[viewGroup.getChildCount()];
        for (int i = 0; i < mChildViews.length; i++) {
            mChildViews[i] = viewGroup.getChildAt(i);
        }
    }
    public void onClick(View view) {
        RippleAnimation.create(view).setDuration(5000).start();
        int color;
        switch (view.getId()) {
            case R.id.red:
                color = Color.RED;
                break;
            case R.id.green:
                color = Color.GREEN;
                break;
            case R.id.blue:
                color = Color.BLUE;
                break;
            case R.id.yellow:
                color = Color.YELLOW;
                break;
            case R.id.black:
                color = Color.DKGRAY;
                break;
            case R.id.cyan:
                color = Color.CYAN;
                break;
            default:
                color = Color.TRANSPARENT;
                break;
        }
        updateColor(color);
    }

    private void updateColor(int color) {
        mLeftNavigationView.setBackgroundColor(color);
        mRightNavigationView.setBackgroundColor(color);
        for (View view : mChildViews) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(color);
            } else {
                view.setBackgroundColor(color);
            }
        }
    }

    private void initStatusBar() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
    @Override
    public void addListener() {

    }

    @Override
    public void addData() {

    }
}
