package com.ydh.yudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ydh.yudemo.MoneyEditext.MoneyEditextActivity;
import com.ydh.yudemo.banner.BannerActivity;
import com.ydh.yudemo.circle.CircleViewActivity;
import com.ydh.yudemo.draggridview.DragActivity;
import com.ydh.yudemo.expandtextview.ExpandTextViewActivity;
import com.ydh.yudemo.friends.FriendsActivity;
import com.ydh.yudemo.gallery.GalleryActivity;
import com.ydh.yudemo.keyboard.KeyboardActivity;
import com.ydh.yudemo.permission.PermissionActivity;
import com.ydh.yudemo.permissiontest.PermissionTestActivity;
import com.ydh.yudemo.reptile.ReptileActivity;
import com.ydh.yudemo.selfview.SelfViewActivity;
import com.ydh.yudemo.smartrefreshlayout.SmartRefreshLayoutActivity;
import com.ydh.yudemo.sticky.StickyActivity;
import com.ydh.yudemo.weelview.WeelViewActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mSelfView;
    private TextView mCountDown;
    private TextView mFloatView;
    private TextView mKeyboard;
    private TextView mCircleView;
    private TextView mJc;
    private TextView mDragGridView;
    private TextView mSmartRefreshLayout;
    private TextView mBanner;
    private TextView mWeelView;
    private TextView mTest;
    private TextView mGallery;
    private TextView mSticky;
    private TextView mMatchView;
    private TextView mExpandTextView;
    private TextView mReptile;
    private TextView mPermission;
    private TextView mPermissionYu;
    private TextView mMoneyEditext;
    private TextView mCommentReply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addListener();
    }

    private void initView() {
        mSelfView= (TextView) findViewById(R.id.selfView);
        mCountDown = (TextView) findViewById(R.id.countdown);
        mFloatView = (TextView) findViewById(R.id.floatView);
        mKeyboard = (TextView) findViewById(R.id.keyboard);
        mCircleView = (TextView) findViewById(R.id.circleView);
        mJc = (TextView) findViewById(R.id.jc);
        mDragGridView = (TextView) findViewById(R.id.gragGridView);
        mSmartRefreshLayout = (TextView) findViewById(R.id.smartRefreshLayout);
        mBanner = (TextView) findViewById(R.id.banner);
        mWeelView = (TextView) findViewById(R.id.weelView);
        mGallery = (TextView) findViewById(R.id.gallery);
        mSticky = (TextView) findViewById(R.id.sticky);
        mMatchView = (TextView) findViewById(R.id.matchView);
        mPermission = (TextView) findViewById(R.id.permission);
        mExpandTextView = (TextView) findViewById(R.id.expandTextView);
        mReptile = (TextView) findViewById(R.id.reptile);
        mPermissionYu = (TextView) findViewById(R.id.permissionyu);
        mMoneyEditext = (TextView) findViewById(R.id.money_editext);
        mCommentReply = (TextView) findViewById(R.id.comment_reply);
        mTest = (TextView) findViewById(R.id.test);
    }

    private void addListener() {
        mSelfView.setOnClickListener(SelfViewListener);//简单的自定义控件
        mCountDown.setOnClickListener(CountDownListener);//引导页倒计时
        mFloatView.setOnClickListener(FloatViewListener);//可拖动的控件布局
        mKeyboard.setOnClickListener(KeyboardListener);//自定义键盘
        mCircleView.setOnClickListener(CircleViewListener);//圆形图
        mJc.setOnClickListener(JcListener);//竞彩概率计算
        mDragGridView.setOnClickListener(DragListener);
        mSmartRefreshLayout.setOnClickListener(SmartListener);//SmartRefreshLayout
        mBanner.setOnClickListener(BannerListener);
        mWeelView.setOnClickListener(WeelViewListener);
        mGallery.setOnClickListener(GalleryListener);//画廊效果
        mSticky.setOnClickListener(StickyListener);//recyclerView吸附效果
        mMatchView.setOnClickListener(MatchListener);//赛事比赛自定义控件
        mPermission.setOnClickListener(PermissionListener);
        mExpandTextView.setOnClickListener(ExpandTextViewListener);
        mReptile.setOnClickListener(ReptileListener);
        mPermissionYu.setOnClickListener(PermissionYuListener);
        mMoneyEditext.setOnClickListener(MoneyEditextListener);//EditText 仿微信充值
        mCommentReply.setOnClickListener(CommentReplyListener);
        mTest.setOnClickListener(TestListener);//测试用的

    }
   View.OnClickListener SelfViewListener =new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           startActivity(SelfViewActivity.class);
       }
   };
    View.OnClickListener CountDownListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(CountDownActivity.class);
        }
    };
    View.OnClickListener FloatViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(FloatViewActivity.class);
        }
    };
    View.OnClickListener KeyboardListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(KeyboardActivity.class);
        }
    };
    View.OnClickListener CircleViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(CircleViewActivity.class);
        }
    };
    View.OnClickListener JcListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(JcActivity.class);
        }
    };
    View.OnClickListener DragListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(DragActivity.class);
        }
    };
    View.OnClickListener SmartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(SmartRefreshLayoutActivity.class);
        }
    };
    View.OnClickListener BannerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(BannerActivity.class);
        }
    };
    View.OnClickListener WeelViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(WeelViewActivity.class);
        }
    };
    View.OnClickListener GalleryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(GalleryActivity.class);
        }
    };
    View.OnClickListener StickyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(StickyActivity.class);
        }
    };
    View.OnClickListener MatchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(MatchActivity.class);
        }
    };

    View.OnClickListener PermissionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(PermissionTestActivity.class);
        }
    };
    View.OnClickListener ExpandTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(ExpandTextViewActivity.class);
        }
    };
    View.OnClickListener ReptileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(ReptileActivity.class);
        }
    };
    View.OnClickListener PermissionYuListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(PermissionActivity.class);
        }
    };
    View.OnClickListener MoneyEditextListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(MoneyEditextActivity.class);
        }
    };
    View.OnClickListener CommentReplyListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(FriendsActivity.class);
        }
    };
    View.OnClickListener TestListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(TestActivity.class);
        }
    };

    private void startActivity(Class<? extends AppCompatActivity> classs) {
        Intent intent = new Intent(this, classs);
        startActivity(intent);
    }
}
