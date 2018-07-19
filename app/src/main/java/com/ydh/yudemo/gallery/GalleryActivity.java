package com.ydh.yudemo.gallery;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;

import java.util.ArrayList;

/*
* 1、setClipChildren(boolean clipChildren)方法：
*   clipChild用来定义他的子控件是否要在他应有的边界内进行绘制。 默认情况下，clipChild被设置为true。
*   也就是不允许进行扩展绘制。而在这个Demo中承载ViewPager的父容器必须要将其设置为false才可以绘制旁边的两页
*2、setPageTransformer(boolean reverseDrawingOrder, PageTransformer transformer))方法
*   用于设置ViewPager切换时的动画效果，
*    许多ViewPager切换效果都是通过创建一个类实现ViewPager.PageTransformer然后重写transformPage方法来实现各种切换效果
* */
public class GalleryActivity extends AppCompatActivity {
    private Context mContext;
    private ViewPager mViewPager;
    private RelativeLayout mViewPagerContainer;
    private static int TOTAL_COUNT = 10;
    private ArrayList<ImageView> mData = new ArrayList<>();
    private ArrayList<String> pathList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mContext = this;
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPagerContainer = (RelativeLayout) findViewById(R.id.activity_gallery);
        addData();
        initViewPager();
    }

    private void addData() {
        pathList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521889338786&di=04f17b74de49770213abf3cab0fa34a7&imgtype=0&src=http%3A%2F%2Fimg5.xiazaizhijia.com%2Fwalls%2F20160708%2F1440x900_2f172c09d079701.jpg");
        pathList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521889338786&di=25b149edf198ff590604fb9296055d58&imgtype=0&src=http%3A%2F%2Fpic.yesky.com%2FuploadImages%2F2015%2F215%2F45%2F04L5VRR21C5W.jpg");
        pathList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521889338786&di=458c230ff4061c7e22ee2ae961936fd3&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1309%2F05%2Fc3%2F25274577_1378321466195.jpg");
        pathList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521889338786&di=a69108d9c8f56fdf7a9b94bcb7a5213d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Decfe83b9042442a7ba03f5e5b83bc827%2F728da9773912b31bc2fe74138d18367adab4e17e.jpg");
        pathList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521889338786&di=131c79092c94ab758e80050ba0da5e75&imgtype=0&src=http%3A%2F%2Fpic.yesky.com%2FuploadImages%2F2015%2F131%2F15%2F2O4G259J20K3.jpg");
        ImageView e1 = new ImageView(mContext);
        Glide.with(mContext).load(pathList.get(0)).into(e1);
        mData.add(e1);

        ImageView e2 = new ImageView(mContext);
        Glide.with(mContext).load(pathList.get(1)).into(e2);
        mData.add(e2);

        ImageView e3 = new ImageView(mContext);
        Glide.with(mContext).load(pathList.get(2)).into(e3);
        mData.add(e3);

        ImageView e4 = new ImageView(mContext);
        Glide.with(mContext).load(pathList.get(3)).into(e4);
        mData.add(e4);


        ImageView e5 = new ImageView(mContext);
        Glide.with(mContext).load(pathList.get(4)).into(e5);
        mData.add(e5);

    }

    private void initViewPager() {
        //设置ViewPager的布局
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                DisplayUtil.getDisplayWidth(this) * 7 / 10,
                DisplayUtil.getDisplayHeight(this) * 6 / 10);
        /**** 重要部分  ******/
        //clipChild用来定义他的子控件是否要在他应有的边界内进行绘制。 默认情况下，clipChild被设置为true。 也就是不允许进行扩展绘制。
        mViewPager.setClipChildren(false);
        //父容器一定要设置这个，否则看不出效果
        mViewPagerContainer.setClipChildren(false);

        mViewPager.setLayoutParams(params);
        //为ViewPager设置PagerAdapter
        mViewPager.setAdapter(new MyPagerAdapter(mData));
        //设置ViewPager切换效果，即实现画廊效果
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //设置预加载数量
        mViewPager.setOffscreenPageLimit(2);
        //设置每页之间的左右间隔
        mViewPager.setPageMargin(100);

        //将容器的触摸事件反馈给ViewPager
        mViewPagerContainer.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // dispatch the events to the ViewPager, to solve the problem that we can swipe only the middle view.
                return mViewPager.dispatchTouchEvent(event);
            }

        });
    }

    class MyPagerAdapter extends PagerAdapter {
        ArrayList<ImageView> mData;

        public MyPagerAdapter(ArrayList<ImageView> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            //return viewList==null?0:viewList.size();
            return mData.size();//ViewPager里的个数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mData.get(position);
            container.addView(imageView);
            return imageView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            //这里做切换ViewPager时，底部RadioButton的操作
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mViewPagerContainer != null) {
                mViewPagerContainer.invalidate();
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * 实现的原理是，在当前显示页面放大至原来的MAX_SCALE
     * 其他页面才是正常的的大小MIN_SCALE
     */
    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MAX_SCALE = 1.2f;
        private static final float MIN_SCALE = 1.0f;//0.85f

        @Override
        public void transformPage(View view, float position) {
            Log.e("位置：", position + "");
            //setScaleY只支持api11以上
            if (position < -1) {
                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);
            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
//              Log.e("TAG", view + " , " + position + "");
                float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
                view.setScaleX(scaleFactor);
                //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
                if (position > 0) {
                    view.setTranslationX(-scaleFactor * 2);
                } else if (position < 0) {
                    view.setTranslationX(scaleFactor * 2);
                }
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]

                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);

            }
        }

    }
}
