package com.powerzhou.dogstudy.uimodule.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Entity
public class StudyType {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String typeId;
    private String typeName;


    @Generated(hash = 217376252)
    public StudyType(Long id, String name, String typeId, String typeName) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
    }
    @Generated(hash = 744939151)
    public StudyType() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTypeId() {
        return this.typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
