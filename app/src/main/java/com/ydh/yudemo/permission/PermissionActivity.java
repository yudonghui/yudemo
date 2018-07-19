package com.ydh.yudemo.permission;

import android.Manifest;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

public class PermissionActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    public int getInflateId() {
        return R.layout.activity_permission;
    }

    @Override
    public void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void addListener() {
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE});
            }
        });
    }

    @Override
    public void addData() {

    }

    public void permissonExcute() {
        Toast.makeText(mContext, "权限申请成功", Toast.LENGTH_SHORT).show();
    }
}
