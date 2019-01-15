package com.ydh.yudemo.tree;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;


/**
 * @author yudonghui
 * @date 2017/7/26
 * @describe May the Buddha bless bug-free!!!
 */
public class XGCardView extends LinearLayout {
    private View mInflate;
    private ImageView mHeader;
    private TextView mName;
    private Context mContext;
    private int anInt;
    private LinearLayout mSlide;

    public XGCardView(Context context) {
        super(context);
        mContext = context;
        anInt = DisplayUtil.dip2px(mContext, 3);
        mInflate = View.inflate(context, R.layout.xgcardview, this);
        initView();
    }

    public XGCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mInflate = View.inflate(context, R.layout.xgcardview, this);
        initView();
    }

    public XGCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mInflate = View.inflate(context, R.layout.xgcardview, this);
    }

    private TextView mMate;

    protected void initView() {
        mSlide = (LinearLayout) mInflate.findViewById(R.id.slideBg);
        mHeader = (ImageView) mInflate.findViewById(R.id.avator);
        mName = (TextView) mInflate.findViewById(R.id.name);
        mMate = (TextView) mInflate.findViewById(R.id.mate);
    }

    public void setView(String name, String mate, String sex, String avator) {
        String sexName;//性别
        if ("1".equals(sex)) sexName = "(男)";
        else if ("2".equals(sex)) sexName = "(女)";
        else sexName = "(-)";
        mName.setText((TextUtils.isEmpty(name) ? "--" : name) + sexName);
        mMate.setText("配偶: " + (TextUtils.isEmpty(mate) ? "--" : mate));
        PicassoUtils.setImgView(avator, R.drawable.family_header, mContext, mHeader);
    }

    public void removeDrawable() {
        mHeader.setImageResource(R.drawable.family_header);
    }

    public Drawable getDrawable() {
        return mHeader.getDrawable();
    }

    public void setViewSize(int textsize) {
        mName.setTextSize(textsize);
        mMate.setTextSize(textsize - 2);
    }

    public void setDrawble(Drawable drawble) {
        mHeader.setImageDrawable(drawble);
    }

    public void setSlideBg(int mode) {//1粗棕色，2细棕色 3粗绿色 4细绿色
        switch (mode) {
            case 1:
                mSlide.setBackgroundResource(R.drawable.zongse);
                break;
            case 2:
                mSlide.setBackgroundResource(R.drawable.zongse);
                break;
            case 3:
                mSlide.setBackgroundResource(R.drawable.green);
                break;
            case 4:
                mSlide.setBackgroundResource(R.drawable.green);
                break;
        }
    }
}
