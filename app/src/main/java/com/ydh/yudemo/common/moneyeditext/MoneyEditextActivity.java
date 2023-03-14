package com.ydh.yudemo.common.moneyeditext;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;

import com.ydh.yudemo.R;
import com.ydh.yudemo.common.moneyeditext.newmethod.MoneyEditText;
import com.ydh.yudemo.common.moneyeditext.newmethod.MoneyTextWatcher;

public class MoneyEditextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_editext);
        MoneyEditText mMoneyEditext = findViewById(R.id.money_editext);
        mMoneyEditext.setTextChangedListener(new MoneyTextWatcher(mMoneyEditext) {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                Log.e("来了来了", "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                Log.e("来了来了", "afterTextChanged  "+s.toString());
            }
        });
    }
}
