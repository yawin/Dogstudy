package com.powerzhou.dogstudy.uimodule.base;

import com.powerzhou.dogstudy.uimodule.dao.bean.BaseParam;

/**
 * base presenter
 * Created by Administrator on 2017/3/9 0009.
 */
public interface IBasePresenter{

    void getData();

    void getMoreData();

    void getData(BaseParam baseParam);

    void getMoreData(BaseParam baseParam);
}
