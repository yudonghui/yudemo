package com.ydh.yudemo.common.moneyeditext.newmethod;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * 只能输入数字和小数点
 * 小数点后只能输入两位
 * 小数点只能有一个
 * 小数点不能作为首字符
 * 如果需要监听输入内容的变化可以调用setTextChangedListener进行设置。
 */
public class MoneyEditText extends AppCompatEditText {
    public MoneyEditText(Context context) {
        super(context);
        init();
    }

    public MoneyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoneyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFilters(new InputFilter[]{inputFilter});
        //设置可以输入小数
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        setTextChangedListener(new MoneyTextWatcher(this));
    }

    /**
     * 设置内容变化的监听
     */
    public void setTextChangedListener(MoneyTextWatcher moneyTextWatcher) {
        addTextChangedListener(moneyTextWatcher);
    }

    private InputFilter inputFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            // 删除等特殊字符，直接返回
            if (TextUtils.isEmpty(source)) {
                return null;
            }
            String dValue = dest.toString();
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                int dotIndex = dValue.indexOf(".");
                if (dend <= dotIndex) {
                    return null;
                } else {
                    // 2 表示输入框的小数位数
                    int diff = dotValue.length() + 1 - 2;
                    if (diff > 0) {
                        return source.subSequence(start, end - diff);
                    }
                }
            }
            return null;
        }
    };

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
