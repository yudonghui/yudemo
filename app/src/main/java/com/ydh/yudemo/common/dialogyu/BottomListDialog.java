package com.ydh.yudemo.common.dialogyu;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ydh.yudemo.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BottomListDialog {
    private final Window mWindow;
    private TextView mTvCancel;
    private TextView mTvConfirm;
    private Dialog mDialog;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private ArrayList<CheckEntity> checkEntities = new ArrayList<>();
    private List<CheckEntity> mDataList;
    OnBottomInterface mListener;

    public BottomListDialog(Context mContext) {
        this.mContext = mContext;
        View inflate = View.inflate(mContext, R.layout.dialog_bottom_list, null);
        mRecyclerView = inflate.findViewById(R.id.rv_bottom_dialog);
        mTvCancel = inflate.findViewById(R.id.tv_cancel);
        mTvConfirm = inflate.findViewById(R.id.tv_confirm);
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(inflate);
        mWindow = mDialog.getWindow();
        //把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        mWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = mWindow.getAttributes();
        // 设置宽度
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindow.setAttributes(layoutParams);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.clear();
                mDataList.addAll(checkEntities);
                mListener.onClick();
                mDialog.dismiss();
            }
        });
    }

    public void show(List<CheckEntity> dataList, OnBottomInterface mListener) {
        if (dataList == null) return;
        this.mDataList = dataList;
        this.mListener = mListener;
        checkEntities.clear();
        for (int i = 0; i < dataList.size(); i++) {
            CheckEntity checkEntity = dataList.get(i);
            CheckEntity checkEntity1 = new CheckEntity(checkEntity.getDomainText(), checkEntity.getDomainValue(), checkEntity.getSelect());
            checkEntities.add(checkEntity1);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new CommonAdapter<CheckEntity>(mContext, R.layout.item_activty, checkEntities) {
            @Override
            protected void convert(ViewHolder holder, final CheckEntity checkEntity, int position) {
                CheckBox mCheckBox = holder.getView(R.id.name);
                mCheckBox.setText(TextUtils.isEmpty(checkEntity.getDomainText()) ? "" : checkEntity.getDomainText());
                mCheckBox.setChecked(checkEntity.getSelect());
                mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        checkEntity.setSelect(isChecked);
                    }
                });
            }
        });
        mDialog.show();
        mWindow.setGravity(Gravity.BOTTOM);
    }

    public interface OnBottomInterface {
        void onClick();
    }
}
