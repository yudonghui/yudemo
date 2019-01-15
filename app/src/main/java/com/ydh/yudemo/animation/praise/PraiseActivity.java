package com.ydh.yudemo.animation.praise;

import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

public class PraiseActivity extends BaseActivity {
    private Button zan_btn;
    private TextView addOne;// +1
    private TextView mPraise;
    private android.view.animation.Animation animation;
    private HeartLayout mHeartLayout;
    private TextView mSendHeart;


    @Override
    public int getInflateId() {
        return R.layout.activity_praise;
    }

    @Override
    public void initView() {
        //  初始化 动画
        animation = AnimationUtils.loadAnimation(this, R.anim.add_score_anim);
        zan_btn = (Button) findViewById(R.id.zan_btn);
        addOne = (TextView) findViewById(R.id.addOne_tv);
        mPraise = (TextView) findViewById(R.id.praise);
        mHeartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        mSendHeart = (TextView) findViewById(R.id.member_send_good);
    }

    private int isLike = 0;//0没有点赞，1点赞了
    private int likeNum = 0;//点赞的数量

    @Override
    public void addListener() {
        //  按钮点击 触发动画
        zan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOne.setVisibility(View.VISIBLE);
                addOne.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        addOne.setVisibility(View.GONE);
                    }
                }, 1000);
            }
        });
        mPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLike == 0) {
                    isLike=1;
                    mPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.red_praise), null, null, null);
                    mPraise.setTextColor(getResources().getColor(R.color.red_txt));
                    mPraise.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.praise_anim));
                    likeNum++;
                    mPraise.setText(likeNum + "");
                } else {
                    isLike=0;
                    mPraise.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.gray_praise), null, null, null);
                    likeNum--;
                    mPraise.setText(likeNum + "");
                    mPraise.setTextColor(getResources().getColor(R.color.gray_txt));
                }

            }
        });
        mSendHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeartLayout.addFavor();
            }
        });
    }

    @Override
    public void addData() {

    }
}
