package com.powerzhou.dogstudy.uimodule.study.subview;


import com.powerzhou.dogstudy.uimodule.base.ILoadDataView;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyItemInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public interface IStudySubListView extends ILoadDataView<List<StudyItemInfo>> {

    void loadData(List<StudyItemInfo> list);

}
