package com.ydh.yudemo.common.dialogyu;


public class CheckEntity {
    private String domainText;
    private String domainValue;
    private boolean select;

    public CheckEntity(String domainText, String domainValue, boolean select) {
        this.domainText = domainText;
        this.domainValue = domainValue;
        this.select = select;
    }

    public CheckEntity(String domainText,
                       String domainValue) {
        this.domainText = domainText;
        this.domainValue = domainValue;
    }

    public String getDomainText() {
        return domainText;
    }

    public void setDomainText(String domainText) {
        this.domainText = domainText;
    }

    public String getDomainValue() {
        return domainValue;
    }

    public void setDomainValue(String domainValue) {
        this.domainValue = domainValue;
    }

    public boolean getSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

}
