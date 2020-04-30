package com.ydh.yudemo.common.fanxing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.ydh.yudemo.R;
import com.ydh.yudemo.common.CommonBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FanXingActivity<T extends BaseEntity> extends AppCompatActivity {

    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    private T mBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_xing);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_one, R.id.tv_two, R.id.tv_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                mBox.bindView(FanXingActivity.this);
                Box mode = new Box();
                mode.click();
                break;
            case R.id.tv_two:
                break;
            case R.id.tv_three:
                break;
        }
    }

}
