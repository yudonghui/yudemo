package com.ydh.yudemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;
import com.ydh.yudemo.tree.BookView;

/**
 * Created by Android on 2018/3/29.
 */

public class TestView extends View {
    private Context mContext;

    public TestView(Context context) {
        super(context);
        mContext = context;
        init();
    }


    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    Paint paint;
    Scroller mScroller;
    GestureDetector gestureDetector;

    private void init() {
        mScroller = new Scroller(mContext);
        gestureDetector = new GestureDetector(mContext, GestureListener);
        gestureDetector.setIsLongpressEnabled(true);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(mContext, R.color.red_txt));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(100, 200);
        path.lineTo(210, 250);
        path.lineTo(500, 600);
        path.lineTo(300, 800);
        path.lineTo(400, 700);
        path.lineTo(500, 800);
        path.lineTo(550, 700);
        path.lineTo(600, 800);
        path.lineTo(650, 700);
        path.lineTo(700, 800);
        path.lineTo(750, 700);
        path.lineTo(800, 800);
        path.lineTo(850, 700);
        path.lineTo(900, 800);
        path.lineTo(950, 700);
        path.lineTo(1000, 800);
        path.lineTo(1050, 700);
        path.lineTo(1100, 800);
        path.lineTo(1150, 700);
        path.lineTo(1200, 800);
        path.lineTo(1250, 700);
        path.lineTo(1300, 800);
        path.lineTo(1350, 700);
        path.lineTo(1400, 800);
        path.lineTo(1450, 700);
        path.lineTo(1500, 800);
        path.lineTo(1550, 700);
        path.lineTo(1600, 800);
        path.lineTo(1650, 700);
        path.lineTo(1700, 800);
        path.lineTo(1750, 700);
        path.lineTo(1800, 800);
        path.lineTo(1850, 700);
        path.lineTo(1900, 800);
        path.lineTo(1950, 700);
        path.lineTo(2000, 800);
        path.lineTo(2050, 700);
        path.lineTo(2100, 800);
        path.lineTo(2150, 700);
        path.lineTo(2200, 800);
        path.lineTo(2250, 700);
        path.lineTo(2300, 800);
        path.lineTo(2350, 700);
        path.lineTo(2400, 800);
        path.lineTo(2450, 700);
        path.lineTo(2500, 800);
        path.lineTo(2550, 700);
        path.lineTo(2600, 800);
        path.lineTo(2650, 700);
        path.lineTo(2700, 800);
        path.lineTo(2750, 700);
        path.lineTo(2800, 800);
        path.lineTo(2850, 700);
        path.lineTo(2900, 800);
        path.lineTo(2950, 700);
        path.lineTo(3000, 800);
        path.lineTo(3050, 700);
        path.lineTo(3100, 800);
        path.lineTo(3150, 700);
        path.lineTo(3200, 800);
        path.lineTo(3300, 700);
        path.lineTo(3400, 800);
        path.lineTo(3500, 700);
        path.lineTo(3600, 800);
        path.lineTo(3700, 700);
        path.lineTo(3800, 800);
        path.lineTo(3900, 700);
        path.lineTo(4000, 800);
        path.lineTo(4100, 800);
        path.lineTo(4200, 800);
        path.lineTo(4300, 800);
        path.lineTo(4400, 800);
        path.lineTo(4500, 800);
        path.lineTo(4600, 800);
        path.lineTo(4700, 800);
        path.lineTo(4800, 800);
        path.lineTo(4900, 800);
        path.lineTo(5000, 800);
        path.lineTo(5100, 800);
        path.lineTo(5200, 800);
        path.lineTo(5300, 800);
        path.lineTo(5400, 800);
        path.lineTo(5500, 800);
        path.lineTo(5600, 800);
        path.lineTo(5700, 800);
        path.lineTo(5800, 800);
        path.lineTo(5900, 800);
        path.lineTo(6000, 800);
        path.lineTo(6100, 800);
        path.lineTo(6200, 800);
        path.lineTo(6300, 800);
        path.lineTo(6400, 800);
        path.lineTo(6500, 800);
        path.lineTo(6600, 800);
        path.lineTo(6700, 800);
        path.lineTo(6800, 800);
        path.lineTo(6900, 800);
        path.lineTo(7000, 800);
        path.lineTo(7100, 800);


        path.lineTo(850, 1000);
        path.lineTo(900, 400);

        path.lineTo(1000, 2000);
        path.lineTo(2000, 4000);
        canvas.drawPath(path, paint);
    /*    canvas.save();
        canvas.clipRect(100, 100, 300, 500);
        canvas.drawColor(ContextCompat.getColor(mContext, R.color.blue_bg));
        canvas.drawCircle(100, 100, 100, paint);
        canvas.restore();
        canvas.drawCircle(150, 150, 100, paint);*/
        BookView view = new BookView(mContext);
        view.setView("中国人");
        Bitmap viewBitmap = convertViewToBitmap(view);
        canvas.drawBitmap(viewBitmap, 0, 0, paint);
        Log.e("画图", "ondraw");
    }

    public Bitmap convertViewToBitmap(View view) {
        //  view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();

        int displayWidth = DisplayUtil.getDisplayWidth(mContext);
        int displayHeight = DisplayUtil.getDisplayHeight(mContext);
        Log.e("控件宽高", displayWidth + "高" + displayHeight);
        view.layout(0, 0, displayWidth, displayHeight);
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache(true);
    }

    float currentX = 0;
    float currentY = 0;
    GestureDetector.OnGestureListener GestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.e("手势操作: ", "onDown");
            if (!mScroller.isFinished()) {
                mScroller.forceFinished(true);
            }
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.e("手势操作: ", "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e("手势操作: ", "onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e("手势操作: ", "onScroll");
            currentX = currentX + distanceX;
            currentY = currentY + distanceY;
            Log.e("onScroll", "currentX" + currentX + " currentY" + currentY);
            scrollTo((int) (currentX), (int) (currentY));
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e("手势操作: ", "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("手势操作: ", "onFling");
            Log.e("滑动距离：", "velocityX: " + velocityX + " velocityY: " + velocityY
                    + " currentX:" + currentX + " currentY:" + currentY);
            //  mScroller.fling((int) currentX, (int) currentY, (int) velocityX, (int) velocityY, -2000, 0, -4000, 0);
            float x = velocityX;
            if (x < 0) {
                float verX = (float) (x * 0.02);
                while (x <= 0) {
                    currentX = (float) (currentX + 50);
                    scrollTo((int) currentX, (int) currentY);
                    invalidate();
                    // postInvalidate();
                    Log.e("惯性滑动", "currentX:" + currentX + " X: " + x);
                    x = x - verX;
                }
            } else

            {

            }
            return true;
        }
    };

    @SuppressLint({"NewApi"})
    public static void setHardwareAccelerated(View view, boolean z) {//硬件加速
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        if (z) {
            view.setLayerType(2, null);
        } else {
            view.setLayerType(1, null);
        }
    }
}
