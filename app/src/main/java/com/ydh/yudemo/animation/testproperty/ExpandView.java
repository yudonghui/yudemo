package com.ydh.yudemo.animation.testproperty;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydh.yudemo.R;

/**
 * Created by UI on 2018/9/20.
 */

public class ExpandView extends LinearLayout implements View.OnClickListener {
    private View mInflate;
    public Context mContext;
    private TextView mCenter;
    private TextView mOne;
    private TextView mTwo;
    private TextView mThree;
    private TextView mFour;
    private TextView mFive;

    public ExpandView(Context context) {
        super(context);
        mContext = context;
        mInflate = View.inflate(context, R.layout.expand_view, this);
    }

    public ExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mInflate = View.inflate(context, R.layout.expand_view, this);
    }

    private OnItemListener mItemListener;

    public void addListener(OnItemListener mItemListener) {
        this.mItemListener = mItemListener;
    }

    public interface OnItemListener {
        void onClick(View view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mCenter = (TextView) mInflate.findViewById(R.id.center);
        mOne = (TextView) mInflate.findViewById(R.id.one);
        mTwo = (TextView) mInflate.findViewById(R.id.two);
        mThree = (TextView) mInflate.findViewById(R.id.three);
        mFour = (TextView) mInflate.findViewById(R.id.four);
        mFive = (TextView) mInflate.findViewById(R.id.five);
        addListener();
    }

    private boolean mIsMenuOpen = false;

    private void addListener() {
        mCenter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsMenuOpen) {
                    openMenu();
                } else {
                    closeMenu();
                }
            }
        });
        mOne.setOnClickListener(this);
        mTwo.setOnClickListener(this);
        mThree.setOnClickListener(this);
        mFour.setOnClickListener(this);
        mFive.setOnClickListener(this);
    }

    private void openMenu() {
        mIsMenuOpen = true;
        doAnimateOpen(mOne, 0, 5, 200);
        doAnimateOpen(mTwo, 1, 5, 200);
        doAnimateOpen(mThree, 2, 5, 200);
        doAnimateOpen(mFour, 3, 5, 200);
        doAnimateOpen(mFive, 4, 5, 200);
    }

    private void closeMenu() {
        mIsMenuOpen = false;
        doAnimateClose(mOne, 0, 5, 200);
        doAnimateClose(mTwo, 1, 5, 200);
        doAnimateClose(mThree, 2, 5, 200);
        doAnimateClose(mFour, 3, 5, 200);
        doAnimateClose(mFive, 4, 5, 200);
    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != VISIBLE) {
            view.setVisibility(VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f));
        animatorSet.setDuration(500);
        animatorSet.start();

    }

    private void doAnimateClose(View view, int index, int total, int radius) {
        if (view.getVisibility() != VISIBLE) {
            view.setVisibility(VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    @Override
    public void onClick(View v) {
        if (mItemListener == null) return;
        closeMenu();
        mItemListener.onClick(v);
    }
}
