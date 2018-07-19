package com.ydh.yudemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ydh.yudemo.R;


/**
 * 广告倒计时View
 * Created on 2018/2/27.author yudonghui
 */

public class CountDownView extends View {
    private static final int BACKGROUND_COLOR = 0x50555555;
    private static final float BORDER_WIDTH = 15f;
    private static final int BORDER_COLOR = 0xFF6ADBFE;
    private static final String TEXT = "跳过";
    private static final float TEXT_SIZE = 50f;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private int backgroundColor;
    private float borderWidth;
    private int borderColor;
    private String text;
    private int textColor;
    private float textSize;

    private Paint circlePaint;
    private TextPaint textPaint;
    private Paint borderPaint;

    private float progress = 0;

    private CountDownTimerListener listener;
    private int countDownTime;//倒计时总时间，毫秒为单位

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        backgroundColor = ta.getColor(R.styleable.CountDownView_background_color, BACKGROUND_COLOR);
        borderWidth = ta.getDimension(R.styleable.CountDownView_border_width, BORDER_WIDTH);
        borderColor = ta.getColor(R.styleable.CountDownView_border_color, BORDER_COLOR);
        text = ta.getString(R.styleable.CountDownView_text);
        countDownTime = ta.getInteger(R.styleable.CountDownView_count_down_time, 3600);
        time = countDownTime / 1000;
        if (text == null) {
            text = TEXT;
        }
        textSize = ta.getDimension(R.styleable.CountDownView_text_size, TEXT_SIZE);
        textColor = ta.getColor(R.styleable.CountDownView_text_color, TEXT_COLOR);
        ta.recycle();
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setDither(true);
        circlePaint.setColor(backgroundColor);
        circlePaint.setStyle(Paint.Style.FILL);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setDither(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            width = (int) textPaint.measureText(text + time + "S");
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            height = width / (text + time + "S").length();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int min = Math.min(width, height);
        //画底盘
        canvas.drawCircle(width / 2, height / 2, min / 2, circlePaint);
        //画边框
        RectF rectF;
        if (width > height) {
            rectF = new RectF(width / 2 - min / 2 + borderWidth / 2, 0 + borderWidth / 2, width / 2 + min / 2 - borderWidth / 2, height - borderWidth / 2);
        } else {
            rectF = new RectF(borderWidth / 2, height / 2 - min / 2 + borderWidth / 2, width - borderWidth / 2, height / 2 - borderWidth / 2 + min / 2);
        }
        canvas.drawArc(rectF, -90, progress, false, borderPaint);
        int textHeight= (int) textPaint.measureText(text + time + "S")/(text + time + "S").length();
        canvas.drawText(text + time + "S", (rectF.right + rectF.left) / 2, (rectF.top + rectF.bottom) / 2+textHeight/2, textPaint);
    }

    private CountDownTimer countDownTimer;
    private int time;

    public void start() {
        if (listener != null) {
            listener.onStartCount();
        }
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(countDownTime, countDownTime / 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time = (int) ((millisUntilFinished+900) / 1000);
                    progress = ((countDownTime - millisUntilFinished) / (float) countDownTime) * 360;
                    Log.e("时间：", "progress:" + progress + "间隔" + millisUntilFinished);
                    invalidate();
                }

                @Override
                public void onFinish() {
                    progress = 360;
                    invalidate();
                    if (listener != null) {
                        listener.onFinishCount();
                    }
                }
            };
        }
        countDownTimer.cancel();
        countDownTimer.start();


    }

    public void stop() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void setCountDownTimerListener(CountDownTimerListener listener) {
        this.listener = listener;
    }

    public interface CountDownTimerListener {

        void onStartCount();

        void onFinishCount();
    }
}
