package com.ydh.yudemo.common.fanxing;

import com.blankj.utilcode.util.ToastUtils;

public class Box extends BaseEntity<FanXingActivity, TMode> {
    public void click() {
        ToastUtils.showShort("点击了");
    }

}
