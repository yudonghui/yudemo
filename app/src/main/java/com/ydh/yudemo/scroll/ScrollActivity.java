package com.ydh.yudemo.scroll;

import android.util.Log;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;

public class ScrollActivity extends BaseActivity {
    private ForumScrollView mForumScrollView;

    @Override
    public int getInflateId() {
        return R.layout.activity_scroll;
    }

    @Override
    public void initView() {
        mForumScrollView = (ForumScrollView) findViewById(R.id.forumScrollView);
    }

    @Override
    public void addListener() {
        mForumScrollView.setOnScrollistener(new ForumScrollView.OnScrollistener() {
            @Override
            public void onScroll(int startY, int endY) {
                //状态栏的高度
                int statusBarHeight = DisplayUtil.getStatusBarHeight(mContext);
                Log.e("滑动参数","startY: "+startY+" endY: "+endY+" statusBarHeight: "+statusBarHeight);
            }
        });
    }

    @Override
    public void addData() {

    }
}
