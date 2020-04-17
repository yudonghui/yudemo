package com.ydh.yudemo.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ydh.yudemo.R;

public class DividerDecoration extends RecyclerView.ItemDecoration {
    private Drawable drawable;
    private int orientation;

    public DividerDecoration(Context context, int orientation) {
        this(context, 0, orientation);
    }

    /**
     * @param orientation LinearLayoutManager.HORIZONTAL或者LinearLayoutManager.VERTICAL
     * @param resId       资源文件。R.drawable.divider_bg  如果是shape的话要设置size属性
     */
    public DividerDecoration(Context context, int resId, int orientation) {
        if (resId == 0) {
            drawable = ContextCompat.getDrawable(context, R.drawable.divider_default);
        } else {
            drawable = ContextCompat.getDrawable(context, resId);
        }
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("设置了不正确的列表方向");
        }
        this.orientation = orientation;
    }

    /**
     * 对每个item位移方向的分割位置进行绘制
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }

    }


    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getRight() + layoutParams.rightMargin + Math.round(ViewCompat.getTranslationX(childAt));
            int top = childAt.getTop() - layoutParams.topMargin;
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;
            int right = left + drawable.getIntrinsicWidth();

            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }


    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getLeft() - layoutParams.leftMargin;
            int right = childAt.getRight() + layoutParams.rightMargin;
            int top = childAt.getBottom() + layoutParams.bottomMargin + Math.round(ViewCompat.getTranslationY(childAt));
            int bottom = top + drawable.getIntrinsicHeight();

            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }


    /**
     * 获取每个item的偏移量
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            //水平方向
            outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        } else {
            //垂直方向
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        }
    }
}
