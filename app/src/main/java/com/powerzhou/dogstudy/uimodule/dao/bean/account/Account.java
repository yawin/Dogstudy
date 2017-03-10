package com.powerzhou.dogstudy.uimodule.dao.bean.account;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
@Entity
public class Account {
    @Id(autoincrement = true)
    private Long accId;

    private String name;

    private int level;

    @Generated(hash = 1734551023)
    public Account(Long accId, String name, int level) {
        this.accId = accId;
        this.name = name;
        this.level = level;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    public Long getAccId() {
        return this.accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
