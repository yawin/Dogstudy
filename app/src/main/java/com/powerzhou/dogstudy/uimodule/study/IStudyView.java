package com.powerzhou.dogstudy.uimodule.study;

import com.powerzhou.dogstudy.uimodule.base.IBaseView;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public interface IStudyView extends IBaseView {

    void loadData(boolean hasLocalData,boolean hasInternet);

}
