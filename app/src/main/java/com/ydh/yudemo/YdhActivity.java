package com.ydh.yudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.yudemo.android_js.JsActivity;
import com.ydh.yudemo.androidutilcode.UtilsActivity;
import com.ydh.yudemo.animation.AnimationsActivity;
import com.ydh.yudemo.banner.BannerActivity;
import com.ydh.yudemo.circle.CircleViewActivity;
import com.ydh.yudemo.common.CommonActivity;
import com.ydh.yudemo.draggridview.DragActivity;
import com.ydh.yudemo.expandtextview.ExpandTextViewActivity;
import com.ydh.yudemo.frame.FrameActivity;
import com.ydh.yudemo.friends.FriendsActivity;
import com.ydh.yudemo.gallery.GalleryActivity;
import com.ydh.yudemo.keyboard.KeyboardActivity;
import com.ydh.yudemo.common.permission.PermissionActivity;
import com.ydh.yudemo.mpandroidchart.MPAndroidChartActivity;
import com.ydh.yudemo.permissiontest.PermissionTestActivity;
import com.ydh.yudemo.qiantao.QiantaoActivity;
import com.ydh.yudemo.reptile.ReptileActivity;
import com.ydh.yudemo.retrofitrxjava.RetrofitActivity;
import com.ydh.yudemo.scroll.ScrollActivity;
import com.ydh.yudemo.selfview.SelfViewActivity;
import com.ydh.yudemo.smartrefreshlayout.SmartRefreshLayoutActivity;
import com.ydh.yudemo.sticky.StickyActivity;
import com.ydh.yudemo.tree.TreeActivity;
import com.ydh.yudemo.weelview.WeelViewActivity;
import com.ydh.yudemo.yuanjiao.YuanJiaoActivity;

public class YdhActivity extends AppCompatActivity {
    private TextView mZuJian;
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
    private TextView mMoneyEditext;
    private TextView mCommentReply;
    private TextView mScroll;
    private TextView mJs;
    private TextView mAnimation;
    private TextView mQiantao;
    private TextView mYuanJiao;
    private TextView mRetrofit;
    private TextView mMPAndroidChart;
    private TextView mTree;
    private TextView mUtils;
    private TextView mFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydh);
        initView();
        addListener();
    }

    private void initView() {
        mZuJian = (TextView) findViewById(R.id.zujian);
        mSelfView = (TextView) findViewById(R.id.selfView);
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
        mMoneyEditext = (TextView) findViewById(R.id.money_editext);
        mCommentReply = (TextView) findViewById(R.id.comment_reply);
        mScroll = (TextView) findViewById(R.id.scroll);
        mJs = (TextView) findViewById(R.id.js);
        mAnimation = (TextView) findViewById(R.id.animation);
        mQiantao = (TextView) findViewById(R.id.qiantao);
        mYuanJiao = (TextView) findViewById(R.id.yuanjiao);
        mRetrofit = (TextView) findViewById(R.id.retrofit);
        mMPAndroidChart = findViewById(R.id.MPAndroidChart);
        mTree = findViewById(R.id.tree);
        mUtils = findViewById(R.id.utils);
        mFrame = findViewById(R.id.frame);
        mTest = (TextView) findViewById(R.id.test);
    }

    private void addListener() {
        mZuJian.setOnClickListener(ZuJianListener);//组件化测试
        mMPAndroidChart.setOnClickListener(MPAndroidChartListener);//折线图MPAndroidChart的使用
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
        mReptile.setOnClickListener(ReptileListener);//爬虫
        mMoneyEditext.setOnClickListener(MoneyEditextListener);//EditText 仿微信充值
        mCommentReply.setOnClickListener(CommentReplyListener);//与微信朋友圈相关
        mUtils.setOnClickListener(UtilsListener);//AndroidUtilCode工具类的应用
        mScroll.setOnClickListener(ScrollListener);//测量滑动的距离
        mJs.setOnClickListener(JsListener);//js交互
        mAnimation.setOnClickListener(AnimationListener);//动画效果
        mQiantao.setOnClickListener(QiantaoListener);//嵌套问题的解决
        mYuanJiao.setOnClickListener(YuanJiaoListener);//圆角或者圆形图
        mRetrofit.setOnClickListener(RetrofitListener);//retrofit
        mTree.setOnClickListener(TreeListener);//家谱树形图
        mFrame.setOnClickListener(FrameListener);//框架
        mTest.setOnClickListener(TestListener);//测试用的

    }

    View.OnClickListener MPAndroidChartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(MPAndroidChartActivity.class);
        }
    };
    View.OnClickListener ZuJianListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Class clazz = Class.forName("com.ydh.module_first.MainActivity");
                Intent intent = new Intent(YdhActivity.this, clazz);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                Toast.makeText(YdhActivity.this, "没有找到这个类", Toast.LENGTH_SHORT).show();
            }
        }
    };
    View.OnClickListener SelfViewListener = new View.OnClickListener() {
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
    View.OnClickListener MoneyEditextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(CommonActivity.class);
        }
    };
    View.OnClickListener UtilsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(UtilsActivity.class);
        }
    };
    View.OnClickListener CommentReplyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(FriendsActivity.class);
        }
    };
    View.OnClickListener ScrollListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(ScrollActivity.class);
        }
    };
    View.OnClickListener JsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(JsActivity.class);
        }
    };
    View.OnClickListener AnimationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(AnimationsActivity.class);
        }
    };
    View.OnClickListener QiantaoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(QiantaoActivity.class);
        }
    };
    View.OnClickListener YuanJiaoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(YuanJiaoActivity.class);
        }
    };
    View.OnClickListener RetrofitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(RetrofitActivity.class);
        }
    };
    View.OnClickListener TreeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(TreeActivity.class);
        }
    };
    View.OnClickListener FrameListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(FrameActivity.class);
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
