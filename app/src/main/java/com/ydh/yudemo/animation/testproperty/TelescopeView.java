package com.ydh.yudemo.animation.testproperty;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ydh.yudemo.R;

/**
 * Created by UI on 2018/9/26.
 */

public class TelescopeView extends View {
    private Context mContext;
    private Paint mPaint;
    private Bitmap mBitmap;
    private int mDy;
    private int mDx;
    private Bitmap mBitmapBG;

    public TelescopeView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TelescopeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.loading1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDx = -1;
                mDy = -1;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmapBG==null){
            mBitmapBG = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas1 = new Canvas(mBitmapBG);
            canvas1.drawBitmap(mBitmap,null,new Rect(0,0,getWidth(),getHeight()),mPaint);
        }
        if (mDx!=-1&&mDy!=-1){
            mPaint.setShader(new BitmapShader(mBitmapBG, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT));
            canvas.drawCircle(mDx,mDy,150,mPaint);
        }
    }
}
