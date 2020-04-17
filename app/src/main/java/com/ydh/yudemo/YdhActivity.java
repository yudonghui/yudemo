package com.ydh.yudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.ydh.yudemo.draggridview.DragActivity;
import com.ydh.yudemo.expandtextview.ExpandTextViewActivity;
import com.ydh.yudemo.frame.FrameActivity;
import com.ydh.yudemo.friends.FriendsActivity;
import com.ydh.yudemo.gallery.GalleryActivity;
import com.ydh.yudemo.keyboard.KeyboardActivity;
import com.ydh.yudemo.mpandroidchart.MPAndroidChartActivity;
import com.ydh.yudemo.permissiontest.PermissionTestActivity;
import com.ydh.yudemo.qiantao.QiantaoActivity;
import com.ydh.yudemo.recyclerview.RecyclerViewDemoActivity;
import com.ydh.yudemo.recyclerview.SpaceItemDecoration;
import com.ydh.yudemo.reptile.ReptileActivity;
import com.ydh.yudemo.retrofitrxjava.RetrofitActivity;
import com.ydh.yudemo.scroll.ScrollActivity;
import com.ydh.yudemo.selfview.SelfViewActivity;
import com.ydh.yudemo.smartrefreshlayout.SmartRefreshLayoutActivity;
import com.ydh.yudemo.sticky.StickyActivity;
import com.ydh.yudemo.tree.TreeActivity;
import com.ydh.yudemo.weelview.WeelViewActivity;
import com.ydh.yudemo.yuanjiao.YuanJiaoActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<HomeEntity> mDatasList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydh);
        ButterKnife.bind(this);
        initData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10, true, SpaceItemDecoration.GRIDLAYOUT));
        recyclerView.setAdapter(new CommonAdapter<HomeEntity>(this, R.layout.item_home, mDatasList) {
            @Override
            protected void convert(ViewHolder holder, final HomeEntity o, int position) {
                holder.setText(R.id.textView, o.getName());
                holder.setOnClickListener(R.id.textView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skip(o.getType());
                    }
                });
            }
        });
    }

    private void initData() {
        mDatasList.add(new HomeEntity("组件化", Constants.ZUJIAN));
        mDatasList.add(new HomeEntity("折线图", Constants.ZHEXIANTU));
        mDatasList.add(new HomeEntity("目录结构", Constants.MULUJIEGOU));
        mDatasList.add(new HomeEntity("通用代码", Constants.TONGYONGDAIMA));
        mDatasList.add(new HomeEntity("框架", Constants.KUANGJIA));
        mDatasList.add(new HomeEntity("自定义控件", Constants.ZIDINGYIVIEW));
        mDatasList.add(new HomeEntity("引导页倒计时", Constants.YINDAOYE));
        mDatasList.add(new HomeEntity("可拖动的控件布局", Constants.KETUODONGVIEW));
        mDatasList.add(new HomeEntity("自定义键盘", Constants.ZIDINGYIJIANPAN));
        mDatasList.add(new HomeEntity("圆形图", Constants.YUANXINGTU));
        mDatasList.add(new HomeEntity("竞彩概率计算", Constants.JC));
        mDatasList.add(new HomeEntity("可拖动GridView", Constants.TUOZHUAIGRID));
        mDatasList.add(new HomeEntity("SmartRefreshLayout", Constants.REFRESH));
        mDatasList.add(new HomeEntity("轮播图", Constants.LUNBOTU));
        mDatasList.add(new HomeEntity("WheelView", Constants.WEELVIEW));
        mDatasList.add(new HomeEntity("画廊", Constants.HUALANG));
        mDatasList.add(new HomeEntity("吸附效果", Constants.STICKY));
        mDatasList.add(new HomeEntity("赛事比赛自定义", Constants.MATCHVIEW));
        mDatasList.add(new HomeEntity("权限", Constants.PERMISSION));
        mDatasList.add(new HomeEntity("可展开的textview", Constants.EXPANDTEXTVIEW));
        mDatasList.add(new HomeEntity("爬虫", Constants.PACHONG));
        mDatasList.add(new HomeEntity("朋友圈相关", Constants.PENGYOUQUAN));
        mDatasList.add(new HomeEntity("AndroidUtilCode", Constants.GONGJULEI));
        mDatasList.add(new HomeEntity("测量滑动的距离", Constants.CELIANGSCROLL));
        mDatasList.add(new HomeEntity("js交互", Constants.JS));
        mDatasList.add(new HomeEntity("动画效果", Constants.ANIMATION));
        mDatasList.add(new HomeEntity("嵌套问题的解决", Constants.QIANTAOWENTI));
        mDatasList.add(new HomeEntity("圆角或者圆形图", Constants.YUANJIAO));
        mDatasList.add(new HomeEntity("家谱树形图", Constants.SHUXINGTU));
        mDatasList.add(new HomeEntity("retrofit", Constants.RETROFIT));
        mDatasList.add(new HomeEntity("RecyclerView", Constants.RECYCLERVIEW));
        mDatasList.add(new HomeEntity("测试", Constants.TEST));
    }

    private void skip(String type) {
        switch (type) {
            case Constants.ZUJIAN:
                try {
                    Class clazz = Class.forName("com.ydh.module_first.MainActivity");
                    Intent intent = new Intent(YdhActivity.this, clazz);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    Toast.makeText(YdhActivity.this, "没有找到这个类", Toast.LENGTH_SHORT).show();
                }
                break;
            case Constants.ZHEXIANTU:
                startActivity(MPAndroidChartActivity.class);
                break;
            case Constants.MULUJIEGOU:
                startActivity(CatalogActivity.class);
                break;
            case Constants.TONGYONGDAIMA:
                startActivity(CommonActivity.class);
                break;
            case Constants.KUANGJIA:
                startActivity(FrameActivity.class);
                break;
            case Constants.ZIDINGYIVIEW://简单的自定义控件
                startActivity(SelfViewActivity.class);
                break;
            case Constants.YINDAOYE://引导页倒计时
                startActivity(CountDownActivity.class);
                break;
            case Constants.KETUODONGVIEW://可拖动的控件布局
                startActivity(FloatViewActivity.class);
                break;
            case Constants.ZIDINGYIJIANPAN://自定义键盘
                startActivity(KeyboardActivity.class);
                break;
            case Constants.YUANXINGTU://圆形图
                startActivity(CircleViewActivity.class);
                break;
            case Constants.JC://竞彩概率计算
                startActivity(JcActivity.class);
                break;
            case Constants.TUOZHUAIGRID:
                startActivity(DragActivity.class);
                break;
            case Constants.REFRESH:
                startActivity(SmartRefreshLayoutActivity.class);
                break;
            case Constants.LUNBOTU:
                startActivity(BannerActivity.class);
                break;
            case Constants.WEELVIEW:
                startActivity(WeelViewActivity.class);
                break;
            case Constants.HUALANG://画廊效果
                startActivity(GalleryActivity.class);
                break;
            case Constants.STICKY://recyclerView吸附效果
                startActivity(StickyActivity.class);
                break;
            case Constants.MATCHVIEW://赛事比赛自定义控件
                startActivity(MatchActivity.class);
                break;
            case Constants.PERMISSION:
                startActivity(PermissionTestActivity.class);
                break;
            case Constants.EXPANDTEXTVIEW://可展开的textview
                startActivity(ExpandTextViewActivity.class);
                break;
            case Constants.PACHONG://爬虫
                startActivity(ReptileActivity.class);
                break;
            case Constants.PENGYOUQUAN://与微信朋友圈相关
                startActivity(FriendsActivity.class);
                break;
            case Constants.GONGJULEI://AndroidUtilCode工具类的应用
                startActivity(UtilsActivity.class);
                break;
            case Constants.CELIANGSCROLL://测量滑动的距离
                startActivity(ScrollActivity.class);
                break;
            case Constants.JS://js交互
                startActivity(JsActivity.class);
                break;
            case Constants.ANIMATION://动画效果
                startActivity(AnimationsActivity.class);
                break;
            case Constants.QIANTAOWENTI://嵌套问题的解决
                startActivity(QiantaoActivity.class);
                break;
            case Constants.YUANJIAO://圆角或者圆形图
                startActivity(YuanJiaoActivity.class);
                break;
            case Constants.SHUXINGTU://家谱树形图
                startActivity(TreeActivity.class);
                break;
            case Constants.RETROFIT: //retrofit
                startActivity(RetrofitActivity.class);
                break;
            case Constants.RECYCLERVIEW: //retrofit
                startActivity(RecyclerViewDemoActivity.class);
                break;
            case Constants.TEST:
                startActivity(TestActivity.class);
                break;
        }
    }

    private void startActivity(Class<? extends AppCompatActivity> classs) {
        Intent intent = new Intent(this, classs);
        startActivity(intent);
    }


}
