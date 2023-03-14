package com.ydh.yudemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JcActivity extends AppCompatActivity {
    private EditText mHome;
    private EditText mPing;
    private EditText mGuest;
    private TextView mHomeG;
    private TextView mPingG;
    private TextView mGuestG;
    private TextView mFanhuanlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jc);
        initView();
    }

    private void initView() {
        mHome = (EditText) findViewById(R.id.home);
        mPing = (EditText) findViewById(R.id.ping);
        mGuest = (EditText) findViewById(R.id.guest);
        mHomeG = (TextView) findViewById(R.id.homeG);
        mPingG = (TextView) findViewById(R.id.pingG);
        mGuestG = (TextView) findViewById(R.id.guestG);
        mFanhuanlv = (TextView) findViewById(R.id.fanhuanlv);
    }

    public void button(View view) {
        String home = mHome.getText().toString();
        String ping = mPing.getText().toString();
        String guest = mGuest.getText().toString();
        if (TextUtils.isEmpty(home) || TextUtils.isEmpty(ping) || TextUtils.isEmpty(guest)) {
            return;
        }
        double homeG = Double.parseDouble(home);
        double pingG = Double.parseDouble(ping);
        double guestG = Double.parseDouble(guest);
        double aaa = 1 / homeG + 1 / pingG + 1 / guestG;
        mFanhuanlv.setText("返还率：" + 100 / aaa + "%");
        mHomeG.setText("主胜概率：" + (1 / aaa) * (1 / homeG) * 100 + "%");
        mPingG.setText("主胜概率：" + (1 / aaa) * (1 / pingG) * 100 + "%");
        mGuestG.setText("主胜概率：" + (1 / aaa) * (1 / guestG) * 100 + "%");
    }
}
