package com.ydh.yudemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.ydh.yudemo.R;

/**
 * Created by Android on 2018/9/12.
 */

public class TestView3 extends ImageView {
    private Context mContext;
    private Bitmap bitmap;
    private Paint mPaint;
    private Path mPath;

    public TestView3(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public TestView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    public TestView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    private void init() {
      setLayerType(LAYER_TYPE_SOFTWARE,null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        mPaint = new Paint();
        mPath = new Path();
        Log.e("生命周期：","init");
    }
    private int width;
    private int height;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        Log.e("生命周期：","onMeasure");

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("生命周期：","onSizeChanged");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("生命周期：","onDraw");
        /*int width = bitmap.getWidth();
        int height = bitmap.getHeight();*/
        mPath.addCircle(width/2,height/2,width/2, Path.Direction.CCW);
        canvas.save();
        canvas.clipPath(mPath);
        canvas.drawBitmap(bitmap,0,0,mPaint);
        canvas.restore();

    }
}
