package com.ydh.yudemo.common;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;
import com.ydh.yudemo.common.dialogyu.SelfDialogActivity;
import com.ydh.yudemo.common.fanxing.FanXingActivity;
import com.ydh.yudemo.common.moneyeditext.MoneyEditextActivity;
import com.ydh.yudemo.common.permission.PermissionActivity;
import com.ydh.yudemo.common.permission.YuAlertDialog;
import com.ydh.yudemo.common.popup.PopupActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class CommonActivity extends AppCompatActivity {
    private RecyclerView mRvCommon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mRvCommon = findViewById(R.id.rv_common);
        ArrayList<CommonBean> mList = getData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 123456);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRvCommon.setLayoutManager(gridLayoutManager);
        mRvCommon.setAdapter(new CommonAdapter<CommonBean>(this, R.layout.item_common, mList) {

            @Override
            protected void convert(ViewHolder holder, final CommonBean commonBean, int position) {
                holder.setText(R.id.tv_content, commonBean.getContent());
                holder.setOnClickListener(R.id.tv_content, new View.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        switch (commonBean.getType()) {
                            case "moneyEditext":
                                startActivity(new Intent(mContext, MoneyEditextActivity.class));
                                break;
                            case "popup":
                                startActivity(new Intent(mContext, PopupActivity.class));
                                break;
                            case "permisson":
                                startActivity(new Intent(mContext, PermissionActivity.class));
                                break;
                            case "dialog":
                                startActivity(new Intent(mContext, SelfDialogActivity.class));
                                break;
                            case "fanxing":
                                startActivity(new Intent(mContext, FanXingActivity.class));
                                break;
                            case "copy":
                                String sourcePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                                String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                                CopyFileUtil.copyFile(sourcePath + "/aaatest/one/test.txt", targetPath + "/aaatest/two/test1.txt");
                                break;
                        }
                    }
                });
            }
        });
    }

    private ArrayList<CommonBean> getData() {
        ArrayList<CommonBean> mList = new ArrayList<>();
        mList.add(new CommonBean("moneyEditext", "自定义金额输入控件"));
        mList.add(new CommonBean("popup", "弹出框popup"));
        mList.add(new CommonBean("permisson", "权限的申请"));
        mList.add(new CommonBean("dialog", "自定义dialog"));
        mList.add(new CommonBean("fanxing", "泛型"));
        mList.add(new CommonBean("copy", "复制文件"));
        return mList;
    }

}
