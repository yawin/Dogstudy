package com.powerzhou.dogstudy.uimodule.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Entity
public class StudyType {

    private String typeName;

    @Generated(hash = 345912351)
    public StudyType(String typeName) {
        this.typeName = typeName;
    }

    @Generated(hash = 744939151)
    public StudyType() {
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
