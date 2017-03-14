package com.powerzhou.dogstudy.injector.modules;

import com.powerzhou.dogstudy.injector.PerFragment;
import com.powerzhou.dogstudy.presenter.StudyContentPresenter;
import com.powerzhou.dogstudy.presenter.StudyPresenter;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.study.StudyFragment;
import com.powerzhou.dogstudy.uimodule.study.ViewPagerAdapter;
import com.powerzhou.dogstudy.uimodule.study.subview.StudyContentFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Module
public class StudyContentFragmentModule {

    private final StudyContentFragment fragment;

    public StudyContentFragmentModule(StudyContentFragment fragment){
        this.fragment = fragment;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providerStudyPresenter(DaoSession daoSession, RxBus rxBus){
        return new StudyContentPresenter(fragment,rxBus);
    }

}
