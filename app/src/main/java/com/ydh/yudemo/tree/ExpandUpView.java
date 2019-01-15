package com.ydh.yudemo.tree;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydh.yudemo.R;


/**
 * @author yudonghui
 * @date 2017/10/24
 * @describe May the Buddha bless bug-free!!!
 */
public class ExpandUpView extends LinearLayout {
    public View mInflate;
    private TextView mExpand;
    private Context mContext;

    public ExpandUpView(Context context) {
        super(context);
        mContext = context;
        mInflate = View.inflate(context, R.layout.view_expand_up, this);
        mExpand = (TextView) mInflate.findViewById(R.id.expand);
    }

    public void setViewSize(int textsize) {
        mExpand.setTextSize(textsize);
    }

}
