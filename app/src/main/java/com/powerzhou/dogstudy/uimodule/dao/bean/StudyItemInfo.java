package com.powerzhou.dogstudy.uimodule.dao.bean;

import com.powerzhou.recylerview.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
@Entity
public class StudyItemInfo extends MultiItemEntity {

    private String type;

    private String title;
    /**it may url or local path**/
    private String address;

    @Transient
    private boolean isRead;

    public StudyItemInfo(int itemType, String type) {
        super(itemType);
        this.type = type;
    }

    @Generated(hash = 453654148)
    public StudyItemInfo(String type, String title, String address) {
        this.type = type;
        this.title = title;
        this.address = address;
    }

    @Generated(hash = 65823175)
    public StudyItemInfo() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
