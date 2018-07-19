package com.ydh.yudemo.circle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ydh.yudemo.R;

/**
 * Created by Android on 2018/2/28.
 */

public class CirclerView extends View {
    private Context mContext;
    private boolean isRadio;//是否带有百分号。true 有百分号，false没有百分号
    private int totalNum;//总数量
    private int num;//亮色的数量
    private int colorLight;//亮色
    private int colorGray;//暗色
    private float cvWidth;
    private float textSize;
    private RectF mRectF;
    private Handler mHandler;

    public CirclerView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CirclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    private String text;//中间显示的文字
    private float sweepAngle;//亮圈的角度

    private void init(AttributeSet attrs) {
        TypedArray typedArray = mContext.getResources().obtainAttributes(attrs, R.styleable.CirclerView);
        isRadio = typedArray.getBoolean(R.styleable.CirclerView_cv_isradio, false);
        totalNum = typedArray.getInteger(R.styleable.CirclerView_cv_total, 1);
        num = typedArray.getInteger(R.styleable.CirclerView_cv_num, 1);
        colorLight = typedArray.getColor(R.styleable.CirclerView_cv_color, mContext.getResources().getColor(R.color.gray_bg));
        colorGray = typedArray.getColor(R.styleable.CirclerView_cv_color_gray, mContext.getResources().getColor(R.color.gray_bg));
        cvWidth = typedArray.getDimension(R.styleable.CirclerView_cv_width, 10);
        textSize = typedArray.getDimension(R.styleable.CirclerView_cv_textsize, 12);
        typedArray.recycle();
        if (isRadio) {
            text = num * 100 / totalNum + "%";
        } else {
            text = num + "";
        }
        sweepAngle = num * 360 / (float) totalNum;
        mHandler = new Handler();
        initPaint();
    }

    private Paint paintLight;//画亮圆圈的画笔
    private Paint paintGray;//画暗圆圈的画笔
    private Paint paintText;//写文字

    private void initPaint() {
        paintLight = new Paint();
        paintLight.setAntiAlias(true);
        paintLight.setColor(colorLight);
        paintLight.setStyle(Paint.Style.STROKE);
        paintLight.setStrokeWidth(cvWidth);

        paintGray = new Paint();
        paintGray.setAntiAlias(true);
        paintGray.setColor(colorGray);
        paintGray.setStyle(Paint.Style.STROKE);
        paintGray.setStrokeWidth(cvWidth);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setColor(colorLight);
        paintText.setTextSize(textSize);
        paintText.setTextAlign(Paint.Align.CENTER);

        mRectF = new RectF();

    }

    private float centerX;
    private float centerY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            width = (int) (paintText.measureText(text) + 10);
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            height = (int) (paintText.measureText(text) + 10);
        }
        mRectF.left = (width - height + cvWidth) / 2;
        mRectF.top = cvWidth / 2;
        mRectF.right = (width + height - cvWidth) / 2;
        mRectF.bottom = height - cvWidth / 2;
        centerX = width / 2;
        centerY = height / 2 + cvWidth / 4;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int min = Math.min(measuredWidth, measuredHeight);
        canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, (min - cvWidth) / 2, paintGray);
        canvas.drawArc(mRectF, -90, progress, false, paintLight);
        //canvas.drawArc(mRectF, -90 + sweepAngle, 360 - sweepAngle, false, paintGray);
        int aa = (int) (progress * 100 / 360);
        if (isRadio) {
            text = aa + "%";
        } else {
            text = aa + "";
        }
        canvas.drawText(text, centerX, centerY+cvWidth/2, paintText);

    }

    private float progress;

    public void start() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                progress++;
                if (progress <= sweepAngle) {
                    invalidate();
                    mHandler.postDelayed(this, 5);
                    Log.e("循环次数",progress+"");
                } else {
                    progress = sweepAngle;
                }
            }
        });
    }

    public void setIsRadio(boolean isRadio) {
        this.isRadio = isRadio;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setColorLight(int colorLight) {
        this.colorLight = colorLight;
    }

    public void setColorGray(int colorGray) {
        this.colorGray = colorGray;
    }

    public void setCvWidth(int cvWidth) {
        this.cvWidth = cvWidth;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
