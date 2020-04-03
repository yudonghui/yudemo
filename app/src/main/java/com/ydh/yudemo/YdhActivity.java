package com.ydh.yudemo;

import android.app.Activity;
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
import com.ydh.yudemo.catalog.CatalogActivity;
import com.ydh.yudemo.circle.CircleViewActivity;
import com.ydh.yudemo.common.CommonActivity;
import com.ydh.yudemo.common.popup.PopupActivity;
import com.ydh.yudemo.draggridview.DragActivity;
import com.ydh.yudemo.expandtextview.ExpandTextViewActivity;
import com.ydh.yudemo.frame.FrameActivity;
import com.ydh.yudemo.friends.FriendsActivity;
import com.ydh.yudemo.gallery.GalleryActivity;
import com.ydh.yudemo.keyboard.KeyboardActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YdhActivity extends AppCompatActivity {
    @BindView(R.id.zujian)
    TextView zujian;
    @BindView(R.id.MPAndroidChart)
    TextView MPAndroidChart;
    @BindView(R.id.tv_catalog)
    TextView tvCatalog;
    @BindView(R.id.money_editext)
    TextView moneyEditext;
    @BindView(R.id.frame)
    TextView frame;
    @BindView(R.id.selfView)
    TextView selfView;
    @BindView(R.id.countdown)
    TextView countdown;
    @BindView(R.id.floatView)
    TextView floatView;
    @BindView(R.id.keyboard)
    TextView keyboard;
    @BindView(R.id.circleView)
    TextView circleView;
    @BindView(R.id.jc)
    TextView jc;
    @BindView(R.id.gragGridView)
    TextView gragGridView;
    @BindView(R.id.smartRefreshLayout)
    TextView smartRefreshLayout;
    @BindView(R.id.banner)
    TextView banner;
    @BindView(R.id.weelView)
    TextView weelView;
    @BindView(R.id.gallery)
    TextView gallery;
    @BindView(R.id.sticky)
    TextView sticky;
    @BindView(R.id.matchView)
    TextView matchView;
    @BindView(R.id.permission)
    TextView permission;
    @BindView(R.id.expandTextView)
    TextView expandTextView;
    @BindView(R.id.reptile)
    TextView reptile;
    @BindView(R.id.comment_reply)
    TextView commentReply;
    @BindView(R.id.utils)
    TextView utils;
    @BindView(R.id.scroll)
    TextView scroll;
    @BindView(R.id.js)
    TextView js;
    @BindView(R.id.animation)
    TextView animation;
    @BindView(R.id.qiantao)
    TextView qiantao;
    @BindView(R.id.yuanjiao)
    TextView yuanjiao;
    @BindView(R.id.tree)
    TextView tree;
    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.retrofit)
    TextView retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydh);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.zujian, R.id.MPAndroidChart, R.id.tv_catalog, R.id.money_editext, R.id.frame, R.id.selfView,
            R.id.countdown, R.id.floatView, R.id.keyboard, R.id.circleView, R.id.jc, R.id.gragGridView, R.id.smartRefreshLayout,
            R.id.banner, R.id.weelView, R.id.gallery, R.id.sticky, R.id.matchView, R.id.permission, R.id.expandTextView,
            R.id.reptile, R.id.comment_reply, R.id.utils, R.id.scroll, R.id.js, R.id.animation, R.id.qiantao, R.id.yuanjiao,
            R.id.tree, R.id.test, R.id.retrofit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zujian:
                try {
                    Class clazz = Class.forName("com.ydh.module_first.MainActivity");
                    Intent intent = new Intent(YdhActivity.this, clazz);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    Toast.makeText(YdhActivity.this, "没有找到这个类", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.MPAndroidChart://折线图MPAndroidChart的使用
                startActivity(MPAndroidChartActivity.class);
                break;
            case R.id.tv_catalog://目录结构
                startActivity(CatalogActivity.class);
                break;
            case R.id.money_editext://通用代码
                startActivity(CommonActivity.class);
                break;
            case R.id.frame://框架
                startActivity(FrameActivity.class);
                break;
            case R.id.selfView://简单的自定义控件
                startActivity(SelfViewActivity.class);
                break;
            case R.id.countdown://引导页倒计时
                startActivity(CountDownActivity.class);
                break;
            case R.id.floatView://可拖动的控件布局
                startActivity(FloatViewActivity.class);
                break;
            case R.id.keyboard://自定义键盘
                startActivity(KeyboardActivity.class);
                break;
            case R.id.circleView://圆形图
                startActivity(CircleViewActivity.class);
                break;
            case R.id.jc://竞彩概率计算
                startActivity(JcActivity.class);
                break;
            case R.id.gragGridView:
                startActivity(DragActivity.class);
                break;
            case R.id.smartRefreshLayout:
                startActivity(SmartRefreshLayoutActivity.class);
                break;
            case R.id.banner:
                startActivity(BannerActivity.class);
                break;
            case R.id.weelView:
                startActivity(WeelViewActivity.class);
                break;
            case R.id.gallery://画廊效果
                startActivity(GalleryActivity.class);
                break;
            case R.id.sticky://recyclerView吸附效果
                startActivity(StickyActivity.class);
                break;
            case R.id.matchView://赛事比赛自定义控件
                startActivity(MatchActivity.class);
                break;
            case R.id.permission:
                startActivity(PermissionTestActivity.class);
                break;
            case R.id.expandTextView://可展开的textview
                startActivity(ExpandTextViewActivity.class);
                break;
            case R.id.reptile://爬虫
                startActivity(ReptileActivity.class);
                break;
            case R.id.comment_reply://与微信朋友圈相关
                startActivity(FriendsActivity.class);
                break;
            case R.id.utils://AndroidUtilCode工具类的应用
                startActivity(UtilsActivity.class);
                break;
            case R.id.scroll://测量滑动的距离
                startActivity(ScrollActivity.class);
                break;
            case R.id.js://js交互
                startActivity(JsActivity.class);
                break;
            case R.id.animation://动画效果
                startActivity(AnimationsActivity.class);
                break;
            case R.id.qiantao://嵌套问题的解决
                startActivity(QiantaoActivity.class);
                break;
            case R.id.yuanjiao://圆角或者圆形图
                startActivity(YuanJiaoActivity.class);
                break;
            case R.id.tree://家谱树形图
                startActivity(TreeActivity.class);
                break;
            case R.id.test:
                startActivity(TestActivity.class);
                break;
            case R.id.retrofit: //retrofit
                startActivity(RetrofitActivity.class);
                break;
        }
    }

    private void startActivity(Class<? extends AppCompatActivity> classs) {
        Intent intent = new Intent(this, classs);
        startActivity(intent);
    }


}
