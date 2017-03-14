package com.powerzhou.dogstudy.injector.modules;

import com.powerzhou.dogstudy.injector.PerFragment;
import com.powerzhou.dogstudy.presenter.StudyPresenter;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.study.StudyFragment;
import com.powerzhou.dogstudy.uimodule.study.ViewPagerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Module
public class StudyFragmentModule {

    private final StudyFragment fragment;

    public StudyFragmentModule(StudyFragment fragment){
        this.fragment = fragment;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providerStudyPresenter(DaoSession daoSession, RxBus rxBus){
        return new StudyPresenter(fragment,daoSession.getStudyTypeDao(),rxBus);
    }

    @PerFragment
    @Provides
    public ViewPagerAdapter providerViewPageAdapter(){
        return new ViewPagerAdapter(fragment.getChildFragmentManager());
    }
}
