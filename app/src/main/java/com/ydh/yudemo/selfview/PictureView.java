package com.ydh.yudemo.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Android on 2018/6/17.
 */

public class PictureView extends View {
    private Picture picture;
    private Paint paint;

    public PictureView(Context context) {
        super(context);
    }

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(1, null);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawRect(0, 0, 200, 400, paint);
        paint.setColor(Color.BLACK);
        recording();
        PictureDrawable pictureDrawable = new PictureDrawable(picture);
        pictureDrawable.setBounds(0, 0, 200, 400);
        pictureDrawable.draw(canvas);
    }

    private void recording() {
        picture = new Picture();
        Canvas canvas = picture.beginRecording(100, 200);
        canvas.translate(50, 50);
        canvas.drawCircle(0, 0, 50, paint);
        picture.endRecording();
    }


}
