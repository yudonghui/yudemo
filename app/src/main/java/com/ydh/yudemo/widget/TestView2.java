package com.ydh.yudemo.widget;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ydh.yudemo.R;

/**
 * Created by Android on 2018/9/12.
 */

public class TestView2 extends View {
    private Paint mPaint;
    private Context mContext;
    private Path mPath;
    private Paint mPaintGreen;

    public TestView2(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TestView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public TestView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(mContext, R.color.red_txt));
        mPaint.setStyle(Paint.Style.STROKE);

        mPaintGreen = new Paint();
        mPaintGreen.setColor(ContextCompat.getColor(mContext, R.color.green_txt));

        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawDrag(canvas);//画网格
        drawLine(canvas);//画中线
        drawRegin(canvas);//画阴影部分
    }

    private float radius;
    private int centerX;
    private int centerY;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private int count = 6;//总层数
    private int countF = 8;//分的份儿数；

    private void drawDrag(Canvas canvas) {
        float r = radius / count;
        for (int i = 1; i <= count; i++) {
            float curR = r * i;
            mPath.reset();
            float angle = 360 / countF;//每两个点的角度
            for (int j = 0; j < countF; j++) {
                if (j == 0) {
                    mPath.moveTo(centerX + curR, centerY);
                } else {
                    double cos = Math.cos(Math.toRadians(angle * j));
                    float x = centerX + curR * getCos(angle * j);
                    float y = centerY + curR * getSin(angle * j);
                    Log.e("位置：", "curR:" + curR + "  cos:" + cos + "  x:" + x + "  y:" + y);
                    mPath.lineTo(x, y);
                }
            }
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void drawLine(Canvas canvas) {
        for (int i = 0; i < countF; i++) {
            mPath.reset();
            mPath.moveTo(centerX, centerY);
            float x = centerX + radius * getCos(360 * i / countF);
            float y = centerY + radius * getSin(360 * i / countF);
            mPath.lineTo(x, y);
            canvas.drawPath(mPath, mPaint);
        }
    }

    private double[] data = {2, 5, 1, 6, 4, 5, 2, 4};

    private void drawRegin(Canvas canvas) {
        mPaintGreen.setAlpha(127);
        mPath.reset();
        for (int i = 0; i < countF; i++) {
            float x = (float) (centerX + radius * getCos(360 * i / countF) * data[i] / count);
            float y = (float) (centerY + radius * getSin(360 * i / countF) * data[i] / count);
            if (i == 0) {
                mPath.moveTo(x, y);
            } else {
                mPath.lineTo(x, y);
            }
            mPaintGreen.setStyle(Paint.Style.FILL);
            canvas.drawCircle(x, y, 10, mPaintGreen);
        }
        mPath.close();
        mPaintGreen.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(mPath, mPaintGreen);
        mPaintGreen.setAlpha(255);
        mPaintGreen.setColor(ContextCompat.getColor(mContext, R.color.blue_txt));
        mPaintGreen.setTextSize(20);
        mPaintGreen.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath("窗前明月光，疑似地上霜，举头望明月，低头思故乡。", mPath, 20, 0, mPaintGreen);
        mPaintGreen.setTextSize(30);
        AssetManager assets = mContext.getAssets();
        Typeface fromAsset = Typeface.createFromAsset(assets, "fonts/jian_luobo.ttf");
        mPaintGreen.setTypeface(fromAsset);
        canvas.drawText("自定义样式", 200, 300, mPaintGreen);

    }

    /*
    * @param angle 角度
    * @return 返回的是cos结果值
    * */
    private float getCos(float angle) {
        double v = Math.toRadians(angle);
        return (float) Math.cos(v);
    }

    /*
* @param angle 角度
* @return 返回的是sin结果值
* */
    private float getSin(float angle) {
        double v = Math.toRadians(angle);
        return (float) Math.sin(v);
    }
}
