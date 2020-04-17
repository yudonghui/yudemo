package com.ydh.yudemo.common.fanxing;

public class BaseEntity<T, M> {
    private T view;
    private M mode;

    public void bindView(T view) {
        this.view = view;
    }

    public void bindMode(M mode) {
        this.mode = mode;
    }
}
