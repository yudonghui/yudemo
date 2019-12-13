package com.ydh.yudemo.frame.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserDb {
    @Id(autoincrement = true)//是否可以自动增长
    private Long id;
    private String name;
    private String age;
    private String remark;

    @Generated(hash = 760068787)
    public UserDb(Long id, String name, String age, String remark) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.remark = remark;
    }

    @Generated(hash = 334083576)
    public UserDb() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserDb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
