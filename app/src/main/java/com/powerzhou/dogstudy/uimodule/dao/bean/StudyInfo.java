package com.powerzhou.dogstudy.uimodule.dao.bean;

import android.support.annotation.IntDef;

import com.powerzhou.recylerview.entity.MultiItemEntity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class StudyInfo extends MultiItemEntity {

    public static final int ITEM_TYPE_ITERNET = 1;
    public static final int ITEM_TYPE_LOCAL = 2;

    private StudyType studyType;

    private List<StudyItemInfo> itemInfoList;

    public StudyInfo(@StudyInfoType int infoType,StudyType studyType){
        super(infoType);
        this.studyType = studyType;
    }

    public StudyType getStudyType() {
        return studyType;
    }

    public void setStudyType(StudyType studyType) {
        this.studyType = studyType;
    }

    public List<StudyItemInfo> getItemInfoList() {
        return itemInfoList;
    }

    public void setItemInfoList(List<StudyItemInfo> itemInfoList) {
        this.itemInfoList = itemInfoList;
    }
    @Override
    public void setItemType(@StudyInfoType int itemType) {
        super.setItemType(itemType);
    }
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ITEM_TYPE_ITERNET, ITEM_TYPE_LOCAL})
    public @interface StudyInfoType {}
}
