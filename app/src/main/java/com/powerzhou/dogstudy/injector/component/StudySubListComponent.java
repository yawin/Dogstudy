package com.powerzhou.dogstudy.injector.component;

import com.powerzhou.dogstudy.injector.PerActivity;
import com.powerzhou.dogstudy.injector.modules.StudySubListModule;
import com.powerzhou.dogstudy.uimodule.study.subview.SubListActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = StudySubListModule.class)
public interface StudySubListComponent {
    void inject(SubListActivity view);
}
