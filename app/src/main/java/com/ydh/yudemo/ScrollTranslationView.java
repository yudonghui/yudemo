package com.ydh.yudemo;

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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by Android on 2018/6/13.
 */

public class ScrollTranslationView extends View implements View.OnTouchListener {
    private Context mContext;
    private int width = 1720;
    private int heigh = 3080;
    private Paint paint;
    private Rect rect;
    private int mDisplayWidth;
    private int mDisplayHeight;
    private float mNowX = 0;
    private float mNowY = 0;

    public ScrollTranslationView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ScrollTranslationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    public ScrollTranslationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    private GestureDetector mGestureDetector;
    private Scroller mScroller;

    private void init(AttributeSet attrs) {
        mDisplayWidth = DisplayUtil.getDisplayWidth(mContext);
        mDisplayHeight = DisplayUtil.getDisplayHeight(mContext);
        rect = new Rect();
        initPaint();
        mScroller = new Scroller(mContext);
        mGestureDetector = new GestureDetector(mContext, GestureListener);
        setOnTouchListener(this);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    private Picture picture;

    @Override
    protected void onDraw(Canvas canvas) {
        picture = new Picture();
        Canvas canvasP = picture.beginRecording(width, heigh);
        paint.setColor(getResources().getColor(R.color.red_txt));
        rect.set(0, 0, width, heigh);
        canvasP.drawRect(rect, paint);
        paint.setColor(getResources().getColor(R.color.white));
        paint.setTextSize(20);
        String aaa = "阿斯顿发送到发送到发送到离开吉林省大V拉伸的麻烦了阿斯顿发送到发斯蒂芬" +
                "大事发生打发斯蒂芬阿斯顿发送到发斯蒂芬阿斯顿发斯蒂芬撒地方是阿斯顿发送到发斯蒂芬撒地方" +
                "类目词那里放假好零食圣诞节快快新材料苦咖啡第十六届手动阀了解到了发大几天吗闪电" +
                "发货老师来了休克容量is阿道夫历史课军绿色水电费老地方能力收代理费可接受的垃圾分类世" +
                "纪东方两节课放哪里了来看是的拉大锯阿斯蒂芬拉速度快解放路上klvkjlml.sjfoij;老婆up记录记录了；；‘靠谱" +
                "佛珠你疯了爱上了地府问题令人建瓯市都跟你说辣豆腐撒地方哈市领导赴is地方哪里玩儿";
        drawTextCenter(aaa, canvasP, rect, paint);
        picture.endRecording();
        int height = (int) (picture.getHeight() * mScale);
        int widh = (int) (picture.getWidth() * mScale);
        //canvas.save();
        if (mScroller.computeScrollOffset()) {//动画没有完成
            Log.e("动画没有结束", "111111");
            mNowX = mScroller.getCurrX();
            mNowY = mScroller.getCurrY();
            postInvalidate();
        }
        rect.set((int) mNowX, (int) mNowY, (int) mNowX + widh, (int) mNowY + height);
        canvas.drawPicture(picture, rect);
        // canvas.restore();

    }

    private void drawTextCenter(String str, Canvas canvas, Rect rect, Paint paint) {
        if (!TextUtils.isEmpty(str)) {
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float x = rect.centerX();
            float y = (rect.top + rect.bottom - fontMetrics.top - fontMetrics.bottom) / 2.0f;
            canvas.drawText(str, x, y, paint);
        }
    }

    private float mLastDistance;//手指之间的距离
    private float[] scaleRange = new float[]{0.1f, 2.0f};
    private float mScale = 1.0f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int pointerCount = event.getPointerCount();
        int action = event.getAction();
        Log.e("onTouch", "pointerCount" + pointerCount + "action" + action + "mLastDistance" + mLastDistance);
        if (pointerCount == 2 && action == 2) {//两个触摸点，并且进行了移动。
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            x = (float) Math.sqrt((x * x) + (y * y));
            if (mLastDistance == 0.0f) {
                mLastDistance = x;
                return true;
            } else if (Math.abs(x - mLastDistance) < 10) {
                return true;
            } else {
                mScale = Math.max(scaleRange[0], Math.min(mScale * x / mLastDistance, scaleRange[1]));
                mLastDistance = x;
                invalidate();
                return true;
            }
        }
        if (pointerCount < 2) mLastDistance = 0;
        return mGestureDetector.onTouchEvent(event);
    }

    GestureDetector.OnGestureListener GestureListener = new GestureDetector.OnGestureListener() {
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
            Log.e("手势操作: ", "onScroll");
            float a = getWidth() - picture.getWidth() * mScale;
            mScrollRange[0] = a < 0 ? a : 0;
            mScrollRange[1] = 0;
            float b = getHeight() - picture.getHeight() * mScale;
            mScrollRange[2] = b < 0 ? b : 0;
            mScrollRange[3] = 0;
            mNowX = mNowX - distanceX;
            mNowY = mNowY - distanceY;
            Log.e("滑动的距离", "mNowX: " + mNowX + " mNowY: " + mNowY + " mScrollRange[0]: " + mScrollRange[0]
                    + " mScrollRange[2]: " + mScrollRange[2]);
            mNowX = Math.max(mScrollRange[0], Math.min(mNowX, mScrollRange[1]));
            mNowY = Math.max(mScrollRange[2], Math.min(mNowY, mScrollRange[3]));
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
            Log.e("滑动的距离", "velocityX" + velocityX + "velocityY" + velocityY + "minx:" + mScrollRange[0] + "  maxx: " + mScrollRange[1]
                    + "  miny: " + mScrollRange[2] + "  maxy: " + mScrollRange[3]);

            mScroller.fling((int) mNowX, (int) mNowY, (int) velocityX, (int) velocityY, (int) mScrollRange[0],
                    (int) mScrollRange[1], (int) mScrollRange[2], (int) mScrollRange[3]);
            return true;
        }
    };
    float[] mScrollRange = new float[4];//最小x，最大x. 最小y，最大y

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
}
