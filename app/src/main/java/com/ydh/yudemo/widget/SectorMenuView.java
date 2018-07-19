package com.ydh.yudemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.ydh.yudemo.R;

/**
 * Created by Android on 2018/2/27.
 */

public class SectorMenuView extends View {
    private Context mContext;
    public SectorMenuView(Context context) {
        super(context);
        mContext=context;
    }

    public SectorMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init(attrs);
    }
    private void init(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SectorMenuView);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
