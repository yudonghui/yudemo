package com.ydh.yudemo.common.fanxing;

public class TMode extends BaseEntity{
    private String name;
    private String sex;

    public TMode() {
    }

    public TMode(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
