package com.powerzhou.dogstudy.injector.modules;

import com.powerzhou.dogstudy.injector.PerActivity;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.study.content.StudyContentActivity;
import com.powerzhou.dogstudy.uimodule.study.content.StudyContentPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Module
public class StudyContentModule {

    private final StudyContentActivity view;

    public StudyContentModule(StudyContentActivity view){
        this.view = view;
    }

    @PerActivity
    @Provides
    public IRxBusPresenter providerStudyPresenter(DaoSession daoSession, RxBus rxBus){
        return new StudyContentPresenter(view,rxBus);
    }
}
