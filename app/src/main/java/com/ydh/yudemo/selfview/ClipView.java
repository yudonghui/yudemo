package com.ydh.yudemo.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Android on 2018/6/17.
 */

public class ClipView extends View {
    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);                           //设置画笔为无锯齿
        paint.setColor(Color.BLACK);                        //设置画笔颜色
        paint.setTextSize((float) 30.0);                    //设置字体大小

        canvas.clipRect(100, 100, 350, 600);                //设置显示范围
        canvas.drawColor(Color.WHITE);                      //白色背景
        canvas.drawText("Hello Android!", 100, 130, paint); //绘制字符串
    }
}
