package com.ydh.yudemo.animation.testproperty;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;
import com.ydh.yudemo.utils.LogUtils;

public class PropertyAnimationActivity extends BaseActivity {
    private final static String TAG = PropertyAnimationActivity.class.getSimpleName();
    private Button mButton, mCancel, mRemove;
    private TextView mTextView;
    private ExpandView mExpandView;
    private ValueAnimator mValueAnimator;

    @Override
    public int getInflateId() {
        return R.layout.activity_property_animation;
    }

    @Override
    public void initView() {
        mButton = (Button) findViewById(R.id.button);
        mCancel = (Button) findViewById(R.id.cancel);
        mRemove = (Button) findViewById(R.id.remove);
        mTextView = (TextView) findViewById(R.id.textView);
        mExpandView = (ExpandView) findViewById(R.id.expandView);
    }

    @Override
    public void addListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  mValueAnimator = doAnimation();
                //  mValueAnimator=doAnimationColor();
                //mValueAnimator = doAnimationObject();
                //doObjectAnimation();
                doAnimationSet();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mValueAnimator.cancel();
            }
        });
        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mValueAnimator.removeUpdateListener(updateListener);
                // mValueAnimator.removeAllListeners();
                mValueAnimator.removeListener(animatorListener);
                //mValueAnimator.removeAllListeners();
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.error("点击了textview");
            }
        });
        mExpandView.addListener(new ExpandView.OnItemListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, (String) view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ValueAnimator doAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(3);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(updateListener);
        valueAnimator.addListener(animatorListener);
        valueAnimator.setStartDelay(2000);//延时开始  不设置默认立即开始
        valueAnimator.start();
        return valueAnimator;
    }

    private ValueAnimator doAnimationColor() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0xff0000ff, 0xffffff00);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(3);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (Integer) valueAnimator.getAnimatedValue();
                mTextView.setBackgroundColor(animatedValue);
            }
        });
        valueAnimator.addListener(animatorListener);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.start();
        return valueAnimator;
    }

    private ValueAnimator doAnimationObject() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), 'A', 'Z');
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                char animatedValue = (char) valueAnimator.getAnimatedValue();
                mTextView.setText(String.valueOf(animatedValue));
            }
        });
        valueAnimator.addListener(animatorListener);
        valueAnimator.start();
        return valueAnimator;
    }

    private ObjectAnimator doObjectAnimation() {
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView, "alpha", 1, 0, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView, "rotation", 0, 180, 0);
        objectAnimator.setDuration(5000);
        objectAnimator.start();
        return objectAnimator;
    }

    private AnimatorSet doAnimationSet() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mTextView, "alpha", 1, 0, 1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mTextView, "rotation", 0, 180, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(objectAnimator1, objectAnimator2);
        animatorSet.setDuration(5000);
        animatorSet.start();
        return animatorSet;
    }

    ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int animatedValue = (int) valueAnimator.getAnimatedValue();
            mTextView.layout(animatedValue, animatedValue, animatedValue + mTextView.getWidth(), animatedValue + mTextView.getHeight());
        }
    };
    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
            LogUtils.error("onAnimationStart");
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            LogUtils.error("onAnimationEnd");
        }

        @Override
        public void onAnimationCancel(Animator animator) {
            LogUtils.error("onAnimationCancel");
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
            LogUtils.error("onAnimationRepeat");
        }
    };

    @Override
    public void addData() {

    }

    class CharEvaluator implements TypeEvaluator<Character> {

        @Override
        public Character evaluate(float v, Character startValue, Character endValue) {
            int start = (int) startValue;
            int end = (int) endValue;
            int cur = (int) (startValue + v * (end - start));
            return (char) cur;
        }
    }
}
