package com.ydh.yudemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.ydh.yudemo.R;

/**
 * Created by Android on 2018/3/28.
 */

public class MatchView extends View {
    private Context mContext;
    private int lostNum;
    private int winNum;
    private int pingNum;
    private int totalNum;
    private Paint paintWinBg;
    private Paint paintPingBg;
    private Paint paintLostBg;
    private Paint paintText;
    private float textSize;
    private int winTextW;
    private int winTextH;
    private int pingTextW;
    private int pingTextH;
    private int lostTextW;
    private int lostTextH;

    public MatchView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MatchView);
        lostNum = typedArray.getInteger(R.styleable.MatchView_mv_lost, 0);
        winNum = typedArray.getInteger(R.styleable.MatchView_mv_win, 0);
        pingNum = typedArray.getInteger(R.styleable.MatchView_mv_ping, 0);
        totalNum = typedArray.getInteger(R.styleable.MatchView_mv_total, 0);
        textSize = typedArray.getDimension(R.styleable.MatchView_mv_text_size, 12);
        typedArray.recycle();
        initPaint();
    }
    public void setNum(int winNum, int pingNum, int lostNum, int totalNum) {
        this.winNum = winNum;
        this.pingNum = pingNum;
        this.lostNum = lostNum;
        this.totalNum = totalNum;
        invalidate();
    }
    private void initPaint() {
        paintWinBg = new Paint();
        paintWinBg.setAntiAlias(true);
        paintWinBg.setColor(getResources().getColor(R.color.match_win));
        paintWinBg.setStrokeCap(Paint.Cap.ROUND);//两头圆的画笔

        paintPingBg = new Paint();
        paintPingBg.setAntiAlias(true);
        paintPingBg.setColor(getResources().getColor(R.color.match_ping));
        paintPingBg.setStrokeCap(Paint.Cap.ROUND);//两头圆的画笔

        paintLostBg = new Paint();
        paintLostBg.setAntiAlias(true);
        paintLostBg.setColor(getResources().getColor(R.color.match_lost));
        paintLostBg.setStrokeCap(Paint.Cap.ROUND);//两头圆的画笔

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(textSize);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth() - height;
        int radius = height / 2;
        Rect rect = new Rect();
        paintText.getTextBounds(winNum + "胜", 0, (winNum + "胜").length(), rect);
        winTextW = rect.width();
        winTextH = rect.height();
        paintText.getTextBounds(pingNum + "平", 0, (pingNum + "平").length(), rect);
        pingTextW = rect.width();
        pingTextH = rect.height();
        paintText.getTextBounds(lostNum + "负", 0, (lostNum + "负").length(), rect);
        lostTextW = rect.width();
        lostTextH = rect.height();


        float winWidth = (float) winNum * width / totalNum;
        float pingWidth = (float) pingNum * width / totalNum;//平的宽度
        float lostWidth = (float) lostNum * width / totalNum;//平的宽度
        float pingWidthRight = (float) (pingNum + winNum) * width / totalNum;//平右边的坐标

        paintLostBg.setStrokeWidth(height);
        canvas.drawLine(pingWidthRight, radius, width - radius, radius, paintLostBg);
        paintPingBg.setStrokeWidth(height);
        canvas.drawLine(winWidth, radius, pingWidthRight, radius, paintPingBg);
        paintWinBg.setStrokeWidth(height);//设置画笔的宽度
        canvas.drawLine(radius, radius, winWidth + radius, radius, paintWinBg);
        if (winNum != 0) {
            canvas.drawText(winNum + "胜", winWidth / 2+radius, (height + winTextH) / 2, paintText);
        }
        if (pingNum != 0) {
            canvas.drawText(pingNum + "平", winWidth+radius + (pingWidth - pingTextW) / 2, (height + pingTextH) / 2, paintText);
        }
        if (lostNum != 0) {
            canvas.drawText(lostNum + "输", pingWidthRight+radius + (lostWidth - lostTextW) / 2, (height + lostTextH) / 2, paintText);
        }
    }
}
