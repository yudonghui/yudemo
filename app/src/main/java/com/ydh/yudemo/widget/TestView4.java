package com.ydh.yudemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ydh.yudemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Android on 2018/9/12.
 */

public class TestView4 extends LinearLayout {
    private Context mContext;
    private Paint mPaint;

    public TestView4(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TestView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public TestView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        View qqq = getChildAt(1);
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.red_txt));
    }

    private int measuredWidth, measuredHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();

    }

    List<Demo> mList = new ArrayList<>();
    Map<Integer, Demo> mClickMap = new HashMap<>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Rect rect = new Rect(10,10,60,60);
        mList.clear();
        int column = measuredHeight % 150 <= 50 ? (measuredHeight / 150) - 1 : measuredHeight / 150;
        int line = measuredWidth % 150 <= 50 ? (measuredWidth / 150) - 1 : measuredWidth / 150;
        for (int j = 1; j <= column; j++) {
            for (int i = 1; i <= line; i++) {
                Demo demo = new Demo();
                demo.setLeft(150 * i - 50);
                demo.setTop(150 * j - 50);
                demo.setRight(150 * i + 50);
                demo.setBottom(150 * j + 50);
                demo.setPositionX(i);
                demo.setPositionY(j);
                demo.setPosition(measuredWidth * (j - 1) / 150 + i);
                mList.add(demo);
                if (mClickMap.containsKey(measuredWidth * (j - 1) / 150 + i)) {
                    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                } else {
                    mPaint.setStyle(Paint.Style.STROKE);
                }
                canvas.drawCircle(150 * i, 150 * j, 50, mPaint);
            }
        }

    }

    float x, y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                float curX = event.getX();
                float curY = event.getY();
                for (int i = 0; i < mList.size(); i++) {
                    Demo demo = mList.get(i);
                    boolean contains = new Rect(demo.left, demo.top, demo.right, demo.bottom).contains((int) this.x, (int) this.y);
                    if (contains) {
                        if (Math.abs(curX - x) < 20 && Math.abs(curY - y) < 20) {
                            Toast.makeText(mContext, "第" + demo.getPositionY() + "排，第" + demo.getPositionX() + "个", Toast.LENGTH_SHORT).show();
                            if (mClickMap.containsKey(demo.position)) {
                                mClickMap.remove(demo.position);
                            } else {
                                mClickMap.put(demo.position, demo);
                            }
                            postInvalidate();
                        } else {
                            Toast.makeText(mContext, "您移动了", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                }


                break;
        }
        return super.onTouchEvent(event);
    }

    class Demo {
        private int left;
        private int right;
        private int top;
        private int bottom;
        private int position;
        private int positionX;
        private int positionY;
        private boolean isClick;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPositionX() {
            return positionX;
        }

        public void setPositionX(int positionX) {
            this.positionX = positionX;
        }

        public int getPositionY() {
            return positionY;
        }

        public void setPositionY(int positionY) {
            this.positionY = positionY;
        }

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }
    }
}
