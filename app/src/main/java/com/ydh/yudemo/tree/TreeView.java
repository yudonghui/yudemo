package com.ydh.yudemo.tree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ydh.yudemo.App;
import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;

import java.util.List;

public class TreeView extends RelativeLayout {
    private Context mContext;
    private Tree dataResurce = new Tree();
    private int top;//距离顶部的距离
    private int cardX;//卡片的宽度
    private int cardY;//卡片的高度
    private int offSetX;
    private int offSetY;
    private Tree preTree;
    private float memberRightX;
    private float maxX;
    private float maxY;//y轴方向的最大值
    private int dp5;
    private int dp10;
    private int dp20;
    private int dp30;
    private int dp70;
    private int textsize;
    private float mScale = 1.0f;
    private float minScale = 0.6f;
    private float maxScale = 1.5f;
    private int mScrollWidth;//移动范围
    private int mCurrentX;//当前X轴偏移量
    private int mCurrentY;//当前Y轴偏移量
    private int mLastTouchX;//最后一次触摸的X坐标
    private int mLastTouchY;//最后一次触摸的Y坐标
    private float mLastDistance = 0.0f;
    private String createRid = "1000796";

    public TreeView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public TreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private int displayHeight;//屏幕高度
    private int displayWidth;//屏幕宽度

    private void init() {
        // this.mGestureDetector = new GestureDetector(getContext(), GestureListener);
        mScrollWidth = DisplayUtil.dip2px(mContext, 2);
        displayHeight = DisplayUtil.getDisplayHeight(mContext);
        displayWidth = DisplayUtil.getDisplayWidth(mContext);
        top = (int) (DisplayUtil.dip2px(mContext, 80) * mScale);
        cardX = (int) (DisplayUtil.dip2px(mContext, 55) * mScale);
        cardY = (int) (DisplayUtil.dip2px(mContext, 95) * mScale);
        offSetX = (int) (DisplayUtil.dip2px(mContext, 60) * mScale);
        offSetY = (int) (DisplayUtil.dip2px(mContext, 30) * mScale);
        dp5 = (int) (DisplayUtil.dip2px(mContext, 5) * mScale);
        dp10 = (int) (DisplayUtil.dip2px(mContext, 10) * mScale);
        dp20 = (int) (DisplayUtil.dip2px(mContext, 20) * mScale);
        dp30 = (int) (DisplayUtil.dip2px(mContext, 30) * mScale);
        dp70 = (int) (DisplayUtil.dip2px(mContext, 70) * mScale);
        textsize = (int) (10 * mScale);
        removeAllViews();
    }


    public void initData(Tree dataResurce) {
        this.dataResurce = dataResurce;
        dataResurce.topPoint.y = top;
        dataResurce.bottomPoint.y = dataResurce.topPoint.y + cardY;
        setLevel(dataResurce);
        preTree = new Tree();
        preTree.center.x = dp10;
        setLastItemValueY(dataResurce);
        setAllItemValueY(dataResurce);
        initView(dataResurce);
        setLayoutParams(new LinearLayout.LayoutParams((int) maxX, (int) maxY));
        //scrollTo((int)dataResurce.center.x,(int) dataResurce.center.y);
        invalidate();
    }

    //初始化所有的节点位置
    private void initLocation(Tree model) {
        model.center.x = 0;
        model.center.y = 0;
        model.topPoint.x = 0;
        model.topPoint.y = 0;
        model.bottomPoint.x = 0;
        model.bottomPoint.y = 0;
        if (model.getChilds() != null && model.getChilds().size() > 0) {
            for (int i = 0; i < model.getChilds().size(); i++) {
                initLocation(model.getChilds().get(i));
            }
        }
    }


    private void setLevel(Tree model) {
        if (model.getChilds() != null && model.getChilds().size() > 0) {
            for (int i = 0; i < model.getChilds().size(); i++) {
                List<Tree> childs = model.getChilds();
                Tree childsBean = childs.get(i);
                childsBean.level_id = model.level_id + 1;
                childsBean.topPoint.y = model.bottomPoint.y + offSetY;
                childsBean.bottomPoint.y = childsBean.topPoint.y + cardY;
                if (childsBean.bottomPoint.y + dp70 > maxY)
                    maxY = childsBean.bottomPoint.y + dp70;
                setLevel(childsBean);
            }

        }
    }

    /**
     * 计算每一个分支的最后一个的x轴坐标
     */
    private void setLastItemValueY(Tree model) {
        if (model.getChilds() != null && model.getChilds().size() > 0) {
            for (int i = 0; i < model.getChilds().size(); i++) {
                Tree subTree = model.getChilds().get(i);
                setLastItemValueY(subTree);
            }
        } else {
            model.center.y = model.level_id * offSetY;
            model.center.x = preTree.center.x + offSetX;
            model.topPoint.x = preTree.center.x + offSetX;
            memberRightX = model.bottomPoint.x = preTree.center.x + offSetX;
            Log.e("setLastItemValueY", model.topPoint.x + "双方都" + model.center.x);
            preTree = model;
        }
    }

    /**
     * 计算除了每支除了最后一个的剩余的节点的x轴坐标
     */
    private void setAllItemValueY(Tree model) {
        if (model.center.x == 0) {
            boolean isSubItemHasCenterX = true;
            for (int i = 0; i < model.childs.size(); i++) {
                Tree tree = model.childs.get(i);
                if (tree.center.x <= 0) {
                    isSubItemHasCenterX = false;
                    setAllItemValueY(tree);
                }
            }
            List<Tree> childs = model.getChilds();
            Tree firstTree = childs.get(0);//下一级的第一个元素
            Tree lastTree = childs.get(childs.size() - 1);//下一级的最后一个元素
            Log.e("lailailai" + isSubItemHasCenterX, lastTree.center.x + "第几天" + firstTree.center.x + "世代" + model.level_id);
            if (isSubItemHasCenterX) {
                model.center.y = model.level_id * offSetY;
                model.center.x = lastTree.center.x / 2 + firstTree.center.x / 2;
                model.topPoint.x = model.center.x;
                model.bottomPoint.x = model.center.x;
                Log.e("lailailai", model.topPoint.x + "第几天" + model.level_id);
                setAllItemValueY(dataResurce);
            }
        }

    }

    private ClickTreeView mClickTreeView;
    private boolean flag = false;//false 家谱，true族谱

    private void initView(final Tree model) {

        mClickTreeView = new ClickTreeView(mContext);
        LinearLayout.LayoutParams layoutP = new LinearLayout.LayoutParams(cardY * 3, cardY * 3);
        mClickTreeView.mMenuLayout.setLayoutParams(layoutP);
        mClickTreeView.setLayoutParams(layoutP);

        TextView textView = new TextView(mContext);
        textView.setText("世代");
        textView.setTextSize(textsize * 6 / 5);
        textView.setTextColor(getResources().getColor(R.color.browns_bg));
        textView.setX(dp10);
        textView.setY(dp10);
        addView(textView);

        TextView textView2 = new TextView(mContext);
        textView2.setText("辈分");
        textView2.setTextSize(textsize * 6 / 5);
        textView2.setTextColor(getResources().getColor(R.color.browns_bg));
        textView2.setX(memberRightX + cardX + dp20);
        textView2.setY(dp10);
        addView(textView2);


        TextView textView3 = new TextView(mContext);
        textView3.setBackgroundResource(R.drawable.shape_circle_brown);
        textView3.setLayoutParams(new RelativeLayout.LayoutParams(cardX * 2 / 3, cardX * 2 / 3));
        textView3.setGravity(Gravity.CENTER);
        textView3.setTextSize(textsize);
        textView3.setText(dataResurce.level_id + "");
        textView3.setTextColor(getResources().getColor(R.color.browns_bg));
        textView3.setY(dataResurce.topPoint.y + cardY / 2 - cardX / 3);
        textView3.setX(dp10);
        addView(textView3);

        TextView textView4 = new TextView(mContext);
        textView4.setBackgroundResource(R.drawable.shape_circle_brown);
        textView4.setLayoutParams(new RelativeLayout.LayoutParams(cardX * 2 / 3, cardX * 2 / 3));
        textView4.setGravity(Gravity.CENTER);
        textView4.setText(TextUtils.isEmpty(dataResurce.getGeneration()) ? "" : dataResurce.getGeneration());
        textView4.setTextSize(textsize);
        textView4.setTextColor(getResources().getColor(R.color.browns_bg));
        textView4.setY(dataResurce.topPoint.y + cardY / 3);
        textView4.setX(memberRightX + cardX + dp20);
        addView(textView4);


        String pid = dataResurce.getPid();
        if (!TextUtils.isEmpty(pid) && !"0".equals(pid)) {//向上可以展開
            final ExpandUpView expandView = new ExpandUpView(mContext);
            expandView.setX(dataResurce.topPoint.x + cardX / 2 - dp20 / 2);
            expandView.setY(dataResurce.topPoint.y - dp20 - dp5);
            expandView.setLayoutParams(new RelativeLayout.LayoutParams(dp30, dp20));
            expandView.setViewSize(textsize - 1);
            expandView.setTag(dataResurce.getPid());
            expandView.setOnClickListener(ExpandViewListener);
            addView(expandView);
        }

        XGCardView xgCardView = new XGCardView(mContext);
        Log.e("位置", dataResurce.topPoint.x + "  " + dataResurce.topPoint.y);
        xgCardView.setX(dataResurce.topPoint.x);
        xgCardView.setY(dataResurce.topPoint.y);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(cardX, cardY);
        xgCardView.setLayoutParams(layoutParams1);
        xgCardView.setViewSize(textsize);
        //名字，配偶名字，性别，头像
        xgCardView.setView(dataResurce.getName(), dataResurce.getSpouse_name(), dataResurce.getSex(), dataResurce.getAvatar_url());
        xgCardView.setTag(dataResurce);
        xgCardView.setOnClickListener(mOnClickListener);
        addView(xgCardView);
        drawView(model);

    }

    private void drawView(Tree model) {
        if (model.getChilds() != null && model.getChilds().size() > 0) {
            for (int i = 0; i < model.getChilds().size(); i++) {
                final Tree subTree = model.getChilds().get(i);

                final XGCardView xgCardView = new XGCardView(mContext);
                Log.e("位置", subTree.topPoint.x + "  " + i + "  " + subTree.topPoint.y);
                xgCardView.setX(subTree.topPoint.x);
                xgCardView.setY(subTree.topPoint.y);
                RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(cardX, cardY);
                xgCardView.setLayoutParams(layoutParams1);
                xgCardView.setViewSize(textsize);
                //名字，配偶名字，性别，头像
                xgCardView.setView(subTree.getName(), subTree.getSpouse_name(), subTree.getSex(), subTree.getAvatar_url());
                xgCardView.setTag(subTree);
                xgCardView.setOnClickListener(mOnClickListener);
                addView(xgCardView);
                float beginY = model.bottomPoint.y;
                float beginX = model.bottomPoint.x + cardX / 2;//左下角角往右偏移一半的空控件高度
                float stopY = subTree.topPoint.y;
                float stopX = subTree.topPoint.x + cardX / 2;//左上角往右偏移一半的空控件高度
                DrawGeometryView lineView = new DrawGeometryView(mContext, beginX, beginY, stopX, stopY);
                if (stopX < beginX) {
                    lineView.setX(stopX);
                    lineView.setY(beginY);
                } else if (stopX > beginX) {
                    lineView.setX(beginX);
                    lineView.setY(beginY);
                } else {
                    lineView.setX(beginX);
                    lineView.setY(beginY - 2.5f);
                }
                addView(lineView);
                final TextView textView = new TextView(mContext);
                textView.setBackgroundResource(R.drawable.shape_circle_brown);
                textView.setLayoutParams(new RelativeLayout.LayoutParams(cardX * 2 / 3, cardX * 2 / 3));
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(textsize);
                textView.setText(subTree.level_id + "");
                textView.setTextColor(getResources().getColor(R.color.browns_bg));
                textView.setY(subTree.topPoint.y + cardY / 2 - cardX / 3);
                textView.setX(dp10);
                addView(textView);
                TextView textView2 = new TextView(mContext);
                textView2.setBackgroundResource(R.drawable.shape_circle_brown);
                textView2.setLayoutParams(new RelativeLayout.LayoutParams(cardX * 2 / 3, cardX * 2 / 3));
                textView2.setGravity(Gravity.CENTER);
                textView2.setText(TextUtils.isEmpty(subTree.getGeneration()) ? "" : subTree.getGeneration());
                textView2.setTextSize(textsize);
                textView2.setTextColor(getResources().getColor(R.color.browns_bg));
                textView2.setY(subTree.topPoint.y + cardY / 3);
                textView2.setX(memberRightX + cardX + dp20);
                maxX = memberRightX + cardX + dp30 + cardX;
                addView(textView2);
                if ("1".equals(subTree.getFlag())) {
                    final ExpandDownView expandView = new ExpandDownView(mContext);
                    expandView.setX(subTree.bottomPoint.x + cardX / 2 - dp20 / 2);
                    expandView.setY(subTree.bottomPoint.y + dp5);
                    expandView.setLayoutParams(new RelativeLayout.LayoutParams(dp30, dp20));
                    expandView.setViewSize(textsize - 1);
                    expandView.setTag(subTree.getPid());
                    expandView.setOnClickListener(ExpandViewListener);
                    addView(expandView);
                }
                drawView(subTree);
            }
        }
    }

    private boolean clickFlag = false;
    private Tree clickTree;
    private XGCardView clickXGCardView;//点击的节点控件
    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            XGCardView xgCardView = (XGCardView) v;
            clickXGCardView = xgCardView;
            clickAfter();
            xgCardView.setSlideBg(3);
            Tree tree = (Tree) v.getTag();
            Log.e("点击了", tree.name + "");
            if (flag) {//族谱
                String[] text = new String[]{"详情"};
                mClickTreeView.setData(text, TreeClickListener);
                mClickTreeView.setX(dataResurce.topPoint.x - cardY * 3 / 2 + cardX / 2);
                mClickTreeView.setY(dataResurce.topPoint.y - cardY);
                addView(mClickTreeView);
                clickFlag = true;
                clickTree = dataResurce;
            } else {
                String id = dataResurce.getId();//父亲的id
                String spouse_id = dataResurce.getSpouse_id();//母亲的id
                if (TextUtils.isEmpty(id) || id.contains("-")) {
                    if (TextUtils.isEmpty(spouse_id)) {
                        Intent intent = new Intent(mContext, TreeAddActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("clickTree", dataResurce);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                        return;
                    } else {
                        Intent intent = new Intent(mContext, TreeDetailActivity.class);
                        intent.putExtra("flag", flag);
                        intent.putExtra("id", dataResurce.getSpouse_id());
                        ((Activity) mContext).startActivityForResult(intent, 113);
                    }
                } else {
                    String[] text;
                    if (App.rid.equals(createRid))
                        text = new String[]{"添加", "编辑", "详情", "删除"};
                    else text = new String[]{"详情"};
                    mClickTreeView.setData(text, TreeClickListener);
                    mClickTreeView.setX(dataResurce.topPoint.x - cardY * 3 / 2 + cardX / 2);
                    mClickTreeView.setY(dataResurce.topPoint.y - cardY);
                    addView(mClickTreeView);
                    clickFlag = true;
                    clickTree = dataResurce;
                }
            }

        }
    };
    OnClickListener ExpandViewListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setVisibility(GONE);
            Log.e("点击展开", String.valueOf(v.getTag()));
        }

    };

    InterfaceTreeClick TreeClickListener = new InterfaceTreeClick() {
        @Override
        public void itemClick(View view, int pos) {
            if (clickTree == null) return;
            final String id = clickTree.getId();
            String name = clickTree.getName();
            TextView tv = (TextView) view
                    .findViewById(R.id.id_circle_menu_item_text);
            String text = tv.getText().toString();
            switch (text) {
                case "添加":
                    Intent intent = new Intent(mContext, TreeAddActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("clickTree", clickTree);
                    intent.putExtras(bundle);
                    ((Activity) mContext).startActivityForResult(intent, 111);
                    break;
                case "删除":

                    break;
                case "编辑":

                    break;
                case "设为中心":
                    break;
                case "详情":
                    Intent intent2 = new Intent(mContext, TreeDetailActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("flag", flag);
                    bundle2.putString("id", clickTree.getId());
                    intent2.putExtras(bundle2);
                    ((Activity) mContext).startActivityForResult(intent2, 113);
                    break;
            }
            clickAfter();
        }

        @Override
        public void itemCenterClick(View view) {
            clickAfter();
        }
    };
    private void clickAfter() {
        if (clickFlag = true) {
            removeView(mClickTreeView);
            if (clickXGCardView != null)
                clickXGCardView.setSlideBg(1);
            clickFlag = false;
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getPointerCount() == 2 && event.getAction() == 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            x = (float) Math.sqrt((double) ((x * x) + (y * y)));
            if (mLastDistance == 0.0f) {
                mLastDistance = x;
                return true;
            } else if (Math.abs(x - mLastDistance) <= 40) {
                return true;
            } else {
                mScale = Math.max(minScale, Math.min((mScale * x) / mLastDistance, maxScale));
                Log.e("缩小的倍数", "移动的距离" + x + "" + mScale);
                mLastDistance = x;
                init();
                initLocation(dataResurce);
                initData(dataResurce);
                return true;
            }
        }
        Log.e("事件", "onTouchEvent " + event.getAction() + "");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                final int currentTouchX = (int) event.getX();
                final int currentTouchY = (int) event.getY();

                final int distanceX = currentTouchX - mLastTouchX;//滑动的x轴方向的距离
                final int distanceY = currentTouchY - mLastTouchY;
                getIsScroll(distanceX, distanceY);
                if (isScroll[0])
                    mCurrentX -= distanceX;
                if (isScroll[1])
                    mCurrentY -= distanceY;
                Log.e("位置", "currentTouchX" + currentTouchX + " currentTouchY" + currentTouchY +
                        " distanceX" + distanceX + " distanceY" + distanceY +
                        " mCurrentX" + mCurrentX + " mCurrentY" + mCurrentY +
                        " mLastTouchX" + mLastTouchX + " mLastTouchY" + mLastTouchY);
                this.scrollTo(mCurrentX, mCurrentY);
                // if (isScroll[0])
                mLastTouchX = currentTouchX;
                //  if (isScroll[1])
                mLastTouchY = currentTouchY;
                // return false;
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e("事件", "onInterceptTouchEvent " + event.getAction() + "");
        boolean intercerpt = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastDistance = 0.0f;
                mCurrentX = getScrollX();//相对于视图初始位置所滑动的x轴的位置。如果初始视图坐标是0,0 可以认为是左边距的坐标
                mCurrentY = getScrollY();
                mLastTouchX = (int) event.getX();
                mLastTouchY = (int) event.getY();
                Log.e("位置", " mLastTouchX" + mLastTouchX + " mLastTouchY" + mLastTouchY
                        + " mCurrentX" + mCurrentX + " mCurrentY" + mCurrentY);
                intercerpt = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final int distanceX = Math.abs((int) event.getX() - mLastTouchX);
                final int distanceY = Math.abs((int) event.getY() - mLastTouchY);
                if (distanceX < mScrollWidth && distanceY < mScrollWidth) {
                    intercerpt = false;
                } else {
                    intercerpt = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercerpt = false;
                break;
        }
        Log.e("是否拦截", intercerpt + "");
        return intercerpt;
    }

    GestureDetector.OnGestureListener GestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };
    private boolean[] isScroll = new boolean[2];

    private void getIsScroll(int moveX, int moveY) {
        Log.e("屏幕宽高和最大值", "displayWidth" + displayWidth +
                " displayHeight" + displayHeight + " maxX" + maxX + " maxY" + maxY);
        if (mCurrentX < 0 && moveX > 0) {
            isScroll[0] = false;
        } else if ((displayWidth + mCurrentX) >= maxX && moveX < 0) {
            isScroll[0] = false;
        } else isScroll[0] = true;
        if (mCurrentY < 0 && moveY > 0) {
            isScroll[1] = false;
        } else if ((displayHeight + mCurrentY) >= maxY && moveY < 0) {
            isScroll[1] = false;
        } else {
            isScroll[1] = true;
        }

    }
}
