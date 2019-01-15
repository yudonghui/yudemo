package com.ydh.yudemo.tree;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;

public class BookView extends LinearLayout {
    private Context mContext;
    private View inflate;
    private LinearLayout mLinearLayout;
    private TextView mShixi1;
    private TextView mGeneration1;
    private TextView mClan1;
    private TextView mBrother1;
    private TextView mSelf_name1;
    private TextView mSelf_birthday1;
    private TextView mSelf_dateDate1;
    private TextView mSelf_rank1;
    private ImageView mSelf_avatar1;
    private TextView mSpouse_name1;
    private TextView mSpouse_birthday1;
    private TextView mSpouse_dateDate1;
    private TextView mSpouse_rank1;
    private ImageView mSpouse_avatar1;
    private TextView mFather1;
    private TextView mMother1;
    private TextView mChildren1;
    private TextView mIntroduce1;
    private TextView mShixi2;
    private TextView mGeneration2;
    private TextView mClan2;
    private TextView mBrother2;
    private TextView mSelf_name2;
    private TextView mSelf_birthday2;
    private TextView mSelf_dateDate2;
    private TextView mSelf_rank2;
    private ImageView mSelf_avatar2;
    private TextView mSpouse_name2;
    private TextView mSpouse_birthday2;
    private TextView mSpouse_dateDate2;
    private TextView mSpouse_rank2;
    private ImageView mSpouse_avatar2;
    private TextView mFather2;
    private TextView mMother2;
    private TextView mChildren2;
    private TextView mIntroduce2;

    public BookView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }


    public BookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public BookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    private void init() {
        inflate = inflate(mContext, R.layout.test_view, this);
        int dp10 = DisplayUtil.dip2px(mContext, 10);
        LinearLayout linear = inflate.findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtil.getDisplayWidth(mContext) - 2 * dp10, DisplayUtil.getDisplayHeight(mContext) - 2 * dp10);
        layoutParams.bottomMargin = dp10;
        layoutParams.topMargin = dp10;
        layoutParams.leftMargin = dp10;
        layoutParams.rightMargin = dp10;
        linear.setLayoutParams(layoutParams);
        initView();
    }

    protected void initView() {
        super.onFinishInflate();
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mShixi1 = (TextView) findViewById(R.id.shixi1);
        mGeneration1 = (TextView) findViewById(R.id.generation1);
        mClan1 = (TextView) findViewById(R.id.clan1);
        mBrother1 = (TextView) findViewById(R.id.brother1);
        mSelf_name1 = (TextView) findViewById(R.id.self_name1);
        mSelf_birthday1 = (TextView) findViewById(R.id.self_birthday1);
        mSelf_dateDate1 = (TextView) findViewById(R.id.self_dateDate1);
        mSelf_rank1 = (TextView) findViewById(R.id.self_rank1);
        mSelf_avatar1 = (ImageView) findViewById(R.id.self_avatar1);
        mSpouse_name1 = (TextView) findViewById(R.id.spouse_name1);
        mSpouse_birthday1 = (TextView) findViewById(R.id.spouse_birthday1);
        mSpouse_dateDate1 = (TextView) findViewById(R.id.spouse_dateDate1);
        mSpouse_rank1 = (TextView) findViewById(R.id.spouse_rank1);
        mSpouse_avatar1 = (ImageView) findViewById(R.id.spouse_avatar1);
        mFather1 = (TextView) findViewById(R.id.father1);
        mMother1 = (TextView) findViewById(R.id.mother1);
        mChildren1 = (TextView) findViewById(R.id.children1);
        mIntroduce1 = (TextView) findViewById(R.id.introduce1);
        mShixi2 = (TextView) findViewById(R.id.shixi2);
        mGeneration2 = (TextView) findViewById(R.id.generation2);
        mClan2 = (TextView) findViewById(R.id.clan2);
        mBrother2 = (TextView) findViewById(R.id.brother2);
        mSelf_name2 = (TextView) findViewById(R.id.self_name2);
        mSelf_birthday2 = (TextView) findViewById(R.id.self_birthday2);
        mSelf_dateDate2 = (TextView) findViewById(R.id.self_dateDate2);
        mSelf_rank2 = (TextView) findViewById(R.id.self_rank2);
        mSelf_avatar2 = (ImageView) findViewById(R.id.self_avatar2);
        mSpouse_name2 = (TextView) findViewById(R.id.spouse_name2);
        mSpouse_birthday2 = (TextView) findViewById(R.id.spouse_birthday2);
        mSpouse_dateDate2 = (TextView) findViewById(R.id.spouse_dateDate2);
        mSpouse_rank2 = (TextView) findViewById(R.id.spouse_rank2);
        mSpouse_avatar2 = (ImageView) findViewById(R.id.spouse_avatar2);
        mFather2 = (TextView) findViewById(R.id.father2);
        mMother2 = (TextView) findViewById(R.id.mother2);
        mChildren2 = (TextView) findViewById(R.id.children2);
        mIntroduce2 = (TextView) findViewById(R.id.introduce2);
    }

    public void setView(String name) {
        mSelf_name1.setText(name);
        mSelf_rank1.setText(name);
        mIntroduce1.setText(name);
        mIntroduce1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "简介", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
