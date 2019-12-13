package com.ydh.yudemo.common.moneyeditext.newmethod;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

public  class MoneyTextWatcher implements TextWatcher {
    private MoneyEditText moneyEditText;

    public MoneyTextWatcher(MoneyEditText moneyEditText) {
        this.moneyEditText = moneyEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //如果输入框为空则不处理
        if (TextUtils.isEmpty(s)) {
            return;
        }
        //第一个字符不为小数点
        if (s.length() == 1 && s.toString().equals(".")) {
            moneyEditText.setText("");
            return;

        }
        int counter = counter(s.toString(), '.');
        if (counter > 1) {
            //小数点第一次出现的位置
            int index = s.toString().indexOf('.');
            moneyEditText.setText(s.subSequence(0, index + 1));
        }
        moneyEditText.setSelection(moneyEditText.getText().toString().length());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 统计一个字符在字符串中出现的次数
     *
     * @param s 字符串
     * @param c 字符
     * @return 數量
     */

    public int counter(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;

    }

}
