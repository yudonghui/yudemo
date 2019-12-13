package com.ydh.yudemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.ydh.yudemo.R;

public class TestView5 extends View {
    private Context mContext;
    private Paint paint;

    public TestView5(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public TestView5(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public TestView5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(mContext, R.color.theme_color));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
