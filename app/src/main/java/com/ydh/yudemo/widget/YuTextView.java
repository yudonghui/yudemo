package com.ydh.yudemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.ydh.yudemo.R;

/**
 * Created by Android on 2018/4/4.
 */

public class YuTextView extends View implements View.OnTouchListener {
    private Paint paint;
    private Rect rect;
    private Context mContext;
    private GestureDetector mGestureDetector;
    private Picture picture;
    private Picture picture1;
    private Scroller mScroller;

    public YuTextView(Context context) {
        super(context);
        this.mContext = context;
    }

    public YuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initPaint();
    }

    private float textSize = 20.0f;

    private void initPaint() {
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        rect = new Rect();
        picture = new Picture();
        mScroller = new Scroller(mContext);
        mGestureDetector = new GestureDetector(mContext, GestureListener);
        setOnTouchListener(this);
    }

    private int leftPadding = 100;
    private int topPadding = 100;
    private int maxWidth = 2000;
    private int horHeight = 200;
    private int maxHeight = 4000;
    private int verWidth = 200;
    private float mNowX = 0;
    private float mNowY = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHor();
        drawVer();
       /* boolean[] canScroll = getCanScroll();
        // if (mScroller.computeScrollOffset()) {
        if (canScroll[0]) {
            mNowX = mScroller.getCurrX();
        }
        if (canScroll[1]) {
            mNowY = mScroller.getCurrY();
        }*/
        Log.e("手势操作: ", "mNowX" + mNowX + "mNowY" + mNowY);
        // }
        int width = getWidth();
        int height = getHeight();
        int heightTop = (int) (picture.getHeight() * mScale);
        int widthTop = (int) (picture.getWidth() * mScale);
        int heightLeft = (int) (picture1.getHeight() * mScale);
        int widthLeft = (int) (picture1.getWidth() * mScale);
        canvas.save();
        rect.set((int) (leftPadding * mScale), (int) (topPadding * mScale), width, height);
        canvas.clipRect(rect);
        rect.set((int) (mNowX + (leftPadding * mScale)), (int) (topPadding * mScale), (int) (mNowX + (leftPadding * mScale) + widthTop), (int) (topPadding * mScale + heightTop));
        canvas.drawPicture(picture, rect);
        rect.set((int) (leftPadding * mScale), (int) (topPadding * mScale + heightTop + mNowY), (int) (leftPadding * mScale) + widthLeft, (int) (topPadding * mScale + heightTop + mNowY + heightLeft));
        canvas.drawPicture(picture1, rect);
        canvas.restore();
    }

    private void drawVer() {
        picture1 = new Picture();
        Canvas beginRecording1 = picture1.beginRecording(verWidth, maxHeight);
        for (int i = 0; i < 20; i++) {
            rect.set(0, i * horHeight, verWidth, (i + 1) * horHeight);
            paint.setColor(getResources().getColor(R.color.colorPrimary));
            beginRecording1.drawRect(rect, paint);
            paint.setColor(getResources().getColor(R.color.red_txt));
            beginRecording1.drawLine(0, (i + 1) * horHeight, verWidth, (i + 1) * horHeight, paint);
            paint.setColor(getResources().getColor(R.color.blackColor));
            paint.setTextSize(textSize);
            drawTextCenter("帅哥一群" + i, beginRecording1, rect, paint);
        }
        this.picture1.endRecording();
    }

    private void drawHor() {
        picture = new Picture();
        Canvas beginRecording = this.picture.beginRecording(maxWidth, horHeight);
        for (int i = 0; i < 10; i++) {
            rect.set(i * verWidth, 0, (i + 1) * verWidth, horHeight);
            paint.setColor(getResources().getColor(R.color.blue_bord));
            beginRecording.drawRect(rect, paint);
            paint.setColor(getResources().getColor(R.color.red_txt));
            beginRecording.drawLine((i + 1) * verWidth, 0, (i + 1) * verWidth, horHeight, paint);
            paint.setColor(getResources().getColor(R.color.blackColor));
            paint.setTextSize(textSize);
            drawTextCenter("美女如云" + i, beginRecording, rect, paint);
        }
        this.picture.endRecording();
    }

    private void drawTextCenter(String str, Canvas canvas, Rect rect, Paint paint) {
        if (!TextUtils.isEmpty(str)) {
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float x = rect.centerX();
            float y = (rect.top + rect.bottom - fontMetrics.top - fontMetrics.bottom) / 2.0f;
            canvas.drawText(str, x, y, paint);
        }
    }

    OnGestureListener GestureListener = new OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.e("手势操作: ", "onDown");
            if (!mScroller.isFinished()) {
                mScroller.forceFinished(true);
            }
            mLastDistance = 0.0f;
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
           /* boolean[] canScroll = getCanScroll();
            if (!canScroll[0] && !canScroll[1]) {
                return false;
            }*/
            int[] mScrollRange = new int[4];//最小x，最大x. 最小y，最大y
            mScrollRange[0] = -2526;
            mScrollRange[1] = 120;
            mScrollRange[2] = -1210;
            mScrollRange[3] = 66;
            Log.e("手势操作: ", "distanceX" + distanceX + "distanceY" + distanceY);
            mNowX -= distanceX;
            mNowX = Math.max((float) mScrollRange[0], Math.min(mNowX, (float) mScrollRange[1]));
            mNowY -= distanceY;
            mNowY = Math.max((float) mScrollRange[2], Math.min(mNowY, (float) mScrollRange[3]));
            Log.e("手势操作: ", "onScroll");
            invalidate();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e("手势操作: ", "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("手势操作: ", "onFling");
            return false;
        }
    };
    private float mLastDistance;//手指之间的距离
    private float[] scaleRange = new float[]{0.1f, 2.0f};
    private float mScale = 1.0f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int pointerCount = event.getPointerCount();
        int action = event.getAction();
        if (pointerCount == 2 && action == 2) {
            float x = event.getX(0) - event.getX(1);//两个手指x轴方向的距离
            float y = event.getY(0) - event.getY(1);//两个手指y轴方向的距离
            x = (float) Math.sqrt((x * x) + (y * y));
            if (mLastDistance == 0.0f) {
                mLastDistance = x;
                return true;
            } else if (Math.abs(x - mLastDistance) <= 10) {
                return true;
            } else {
                float a=(mScale * x) / mLastDistance;
                mScale = Math.max(scaleRange[0], Math.min(a, scaleRange[1]));//等价于下面的if语句
                /*if (a<scaleRange[0]){
                    mScale=scaleRange[0];
                }else if (a>scaleRange[1]){
                    mScale=scaleRange[1];
                }else {
                    mScale=a;
                }*/
                mLastDistance = x;
                refresh();
                return true;
            }
        }
        if (event.getPointerCount() < 2) mLastDistance = 0.0f;
        return mGestureDetector.onTouchEvent(event);
    }

    private void refresh() {
        invalidate();
    }

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

    public boolean[] getCanScroll() {
        boolean[] booleen = new boolean[2];
        float height = picture.getHeight() * mScale;
        float width = picture.getWidth() * mScale;
        float height1 = picture1.getHeight() * mScale;
        float width1 = picture1.getWidth() * mScale;
        float y = getHeight();
        float x = getWidth();
        booleen[1] = ((height + topPadding + height1) * mScale > y);
        booleen[0] = ((width + leftPadding + width1) * mScale > x);
        return booleen;
    }
}
