package com.ydh.yudemo.animation.testproperty;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.ydh.yudemo.R;

/**
 * Created by UI on 2018/9/19.
 */

public class LoadingImageView extends ImageView {
    private Context mContext;
    private int mCurImgIndex=0;//当前图片的索引
    private int mImgCount=4;//动画图片总个数
    public LoadingImageView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private int mTop;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }

    private void init() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 150,0);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());//加减速插值器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int top = (int) valueAnimator.getAnimatedValue();
                setTop(mTop-top);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                setImageResource(R.mipmap.loading1);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
               mCurImgIndex++;
                switch (mCurImgIndex%mImgCount){
                    case 0:
                        setImageResource(R.mipmap.loading1);
                        break;
                    case 1:
                        setImageResource(R.mipmap.loading2);
                        break;
                    case 2:
                        setImageResource(R.mipmap.loading3);
                        break;
                    case 3:
                        setImageResource(R.mipmap.loading4);
                        break;
                }
            }
        });
        valueAnimator.start();
    }
}
