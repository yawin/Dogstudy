package com.powerzhou.dogstudy.uimodule.study.content;


import com.powerzhou.dogstudy.uimodule.base.ILoadDataView;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyItemInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public interface IStudyContentView extends ILoadDataView<String> {

    void loadData(String content);

}
