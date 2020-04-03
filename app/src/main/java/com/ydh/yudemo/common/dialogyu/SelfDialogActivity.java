package com.ydh.yudemo.common.dialogyu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.ydh.yudemo.R;
import com.ydh.yudemo.common.CommonActivity;
import com.ydh.yudemo.common.permission.YuAlertDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelfDialogActivity extends AppCompatActivity {

    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.tv_bottom_list)
    TextView tvBottomList;
    @BindView(R.id.tv_center_two)
    TextView tvCenterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_center, R.id.tv_bottom_list, R.id.tv_center_two, R.id.tv_center_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_center:
                new HisDialog.Builder().title("温馨提示")
                        .message("这是一个提示内容")
                        .cancel("取消")
                        .confirm("确定", new HisDialogInterface() {
                            @Override
                            public void callBack(View view) {
                                ToastUtils.showShort("确定");
                            }
                        }).build(this);
                break;
            case R.id.tv_bottom_list:
                ArrayList<CheckEntity> dataList = new ArrayList<>();
                dataList.add(new CheckEntity("这是第一条数据", "123456", false));
                dataList.add(new CheckEntity("这是第二条数据", "123458", false));
                dataList.add(new CheckEntity("这是第三条数据", "123457", true));
                new BottomListDialog(this).show(dataList, new BottomListDialog.OnBottomInterface() {
                    @Override
                    public void onClick() {
                        ToastUtils.showShort("确定");
                    }
                });
                break;
            case R.id.tv_center_two:
                YuAlertDialog.newBuilder(this)
                        .setCancelable(true)
                        .setTitle("提示")
                        .setMessage("撒的发生大发发撒的发生大阿斯顿发斯蒂芬")
                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtils.showShort("设置");
                            }
                        })
                        .setNegativeButton("不", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtils.showShort("不");
                            }
                        })
                        .show();
                break;
            case R.id.tv_center_three:
                new HisDialog.Builder().title("温馨提示")
                        .message("今天是个好日子！")
                        .rightBtn("知道了", new HisDialogInterface() {
                            @Override
                            public void callBack(View view) {
                                ToastUtils.showShort("知道了");
                            }
                        })
                        .build(this, 1)
                        .show();

                break;

        }
    }
}
