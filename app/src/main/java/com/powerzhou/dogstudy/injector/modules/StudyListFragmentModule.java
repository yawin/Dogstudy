package com.powerzhou.dogstudy.injector.modules;

import com.powerzhou.dogstudy.injector.PerFragment;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.study.subviewlist.StudyListAdapter;
import com.powerzhou.dogstudy.uimodule.study.subviewlist.StudyListFragment;
import com.powerzhou.dogstudy.uimodule.study.subviewlist.StudyListPresenter;
import com.powerzhou.recylerview.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Module
public class StudyListFragmentModule {

    private final StudyListFragment fragment;

    public StudyListFragmentModule(StudyListFragment fragment){
        this.fragment = fragment;
    }

    @PerFragment
    @Provides
    public IRxBusPresenter providerStudyPresenter(DaoSession daoSession, RxBus rxBus){
        return new StudyListPresenter(fragment,rxBus);
    }


    @PerFragment
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new StudyListAdapter(fragment.getContext());
    }
}
