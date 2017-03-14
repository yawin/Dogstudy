package com.powerzhou.dogstudy.uimodule.study;

import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public interface IStudyView {

    void loadData(List<StudyType> typeList);

}
