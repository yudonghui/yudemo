package com.ydh.yudemo.sticky;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpf on 2018/1/16.
 */

public class StickyItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 吸附的itemView
     */
    private View mStickyItemView;

    /**
     * 吸附itemView 距离顶部
     */
    private int mStickyItemViewMarginTop;

    /**
     * 吸附itemView 高度
     */
    private int mStickyItemViewHeight;

    /**
     *  通过它获取到需要吸附view的相关信息
     */
    private StickyView mStickyView;

    /**
     * 滚动过程中当前的UI是否可以找到吸附的view
     */
    private boolean mCurrentUIFindStickView;

    /**
     * adapter
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;

    /**
     * viewHolder
     */
    private RecyclerView.ViewHolder mViewHolder;

    /**
     * position list
     */
    private List<Integer> mStickyPositionList = new ArrayList<>();

    /**
     * layout manager
     */
    private LinearLayoutManager mLayoutManager;

    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;

    /**
     * paint
     */
    private Paint mPaint;

    public StickyItemDecoration() {
        mStickyView = new ExampleStickyView();
        initPaint();
    }

    /**
     * init paint
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        mCurrentUIFindStickView = false;

        for (int m = 0, size = parent.getChildCount(); m < size; m++) {
            View view = parent.getChildAt(m);
            /**
             * 如果是吸附的view
             */
            if (mStickyView.isStickyView(view)) {
                mCurrentUIFindStickView = true;
                getStickyViewHolder(parent);
                cacheStickyViewPosition(m);

                if (view.getTop() <= 0) {
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else {
                    if (mStickyPositionList.size() > 0) {
                        if (mStickyPositionList.size() == 1) {
                            bindDataForStickyView(mStickyPositionList.get(0), parent.getMeasuredWidth());
                        } else {
                            int currentPosition = getStickyViewPositionOfRecyclerView(m);
                            int indexOfCurrentPosition = mStickyPositionList.lastIndexOf(currentPosition);
                            bindDataForStickyView(mStickyPositionList.get(indexOfCurrentPosition - 1), parent.getMeasuredWidth());
                        }
                    }
                }

                if (view.getTop() > 0 && view.getTop() <= mStickyItemViewHeight) {
                    mStickyItemViewMarginTop = mStickyItemViewHeight - view.getTop();
                } else {
                    mStickyItemViewMarginTop = 0;
                }

                drawStickyItemView(c);
                break;
            }
        }

        if (!mCurrentUIFindStickView) {
            mStickyItemViewMarginTop = 0;
            if (mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == parent.getAdapter().getItemCount()) {
                bindDataForStickyView(mStickyPositionList.get(mStickyPositionList.size() - 1), parent.getMeasuredWidth());
            }
            drawStickyItemView(c);
        }
    }

    /**
     * 给StickyView绑定数据
     * @param position
     */
    private void bindDataForStickyView(int position, int width) {
        if (mBindDataPosition == position) return;

        mBindDataPosition = position;
        mAdapter.onBindViewHolder(mViewHolder, mBindDataPosition);
        measureLayoutStickyItemView(width);
        mStickyItemViewHeight = mViewHolder.itemView.getBottom() - mViewHolder.itemView.getTop();
    }

    /**
     * 缓存吸附的view position
     * @param m
     */
    private void cacheStickyViewPosition(int m) {
        int position = getStickyViewPositionOfRecyclerView(m);
        if (!mStickyPositionList.contains(position)) {
            mStickyPositionList.add(position);
        }
    }

    /**
     * 得到吸附view在RecyclerView中 的position
     * @param m
     * @return
     */
    private int getStickyViewPositionOfRecyclerView(int m) {
        return mLayoutManager.findFirstVisibleItemPosition() + m;
    }

    /**
     * 得到吸附viewHolder
     * @param recyclerView
     */
    private void getStickyViewHolder(RecyclerView recyclerView) {
        if (mAdapter != null) return;
        mAdapter = recyclerView.getAdapter();
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, mStickyView.getStickViewType());
        mStickyItemView = mViewHolder.itemView;
    }

    /**
     * 计算布局吸附的itemView
     * @param parentWidth
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (!mStickyItemView.isLayoutRequested()) return;

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;

        ViewGroup.LayoutParams layoutParams = mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickyItemView.measure(widthSpec, heightSpec);
        mStickyItemView.layout(0, 0, mStickyItemView.getMeasuredWidth(), mStickyItemView.getMeasuredHeight());
    }

    /**
     * 绘制吸附的itemView
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        int saveCount = canvas.save();
        canvas.translate(0, -mStickyItemViewMarginTop);
        mStickyItemView.draw(canvas);
        canvas.restoreToCount(saveCount);
    }
}
