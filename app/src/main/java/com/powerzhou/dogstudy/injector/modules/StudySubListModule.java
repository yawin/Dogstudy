package com.powerzhou.dogstudy.injector.modules;

import com.powerzhou.dogstudy.injector.PerActivity;
import com.powerzhou.dogstudy.injector.PerFragment;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.study.subview.SubListAdapter;
import com.powerzhou.dogstudy.uimodule.study.subview.SubListPresenter;
import com.powerzhou.dogstudy.uimodule.study.subview.SubListActivity;
import com.powerzhou.dogstudy.uimodule.study.subviewlist.StudyListAdapter;
import com.powerzhou.recylerview.adapter.BaseQuickAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@Module
public class StudySubListModule {

    private final SubListActivity view;

    public StudySubListModule(SubListActivity view){
        this.view = view;
    }

    @PerActivity
    @Provides
    public IRxBusPresenter providerStudyPresenter(DaoSession daoSession, RxBus rxBus){
        return new SubListPresenter(view,rxBus);
    }
    @PerActivity
    @Provides
    public BaseQuickAdapter provideAdapter() {
        return new SubListAdapter(view);
    }
}
