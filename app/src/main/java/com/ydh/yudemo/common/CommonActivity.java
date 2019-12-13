package com.ydh.yudemo.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ydh.yudemo.R;
import com.ydh.yudemo.common.moneyeditext.MoneyEditextActivity;
import com.ydh.yudemo.common.permission.PermissionActivity;
import com.ydh.yudemo.common.popup.PopupActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

public class CommonActivity extends AppCompatActivity {
    private RecyclerView mRvCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mRvCommon = findViewById(R.id.rv_common);
        ArrayList<CommonBean> mList = getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRvCommon.setLayoutManager(gridLayoutManager);
        mRvCommon.setAdapter(new CommonAdapter<CommonBean>(this, R.layout.item_common,mList) {

            @Override
            protected void convert(ViewHolder holder, final CommonBean commonBean, int position) {
                holder.setText(R.id.tv_content, commonBean.getContent());
                holder.setOnClickListener(R.id.tv_content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (commonBean.getType()){
                            case "moneyEditext":
                                startActivity(new Intent(mContext,MoneyEditextActivity.class));
                                break;
                            case "popup":
                                startActivity(new Intent(mContext, PopupActivity.class));
                                break;
                            case "permisson":
                                startActivity(new Intent(mContext, PermissionActivity.class));
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
        return mList;
    }

}
