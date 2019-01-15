package com.ydh.yudemo;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class TestView2 extends ViewGroup {
    public TestView2(Context context) {
        super(context);
    }

    public TestView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Paint mPaint;

    public TestView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int mPaintX = l;
        int mPaintY = t;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            int height = childAt.getMeasuredHeight();
            int width = childAt.getMeasuredWidth();
            if (mPaintX + width > measuredWidth) {
                mPaintX = l;
                mPaintY += height;
            }
            childAt.layout(mPaintX, mPaintY, mPaintX + width, mPaintY + height);
            mPaintX += width;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
