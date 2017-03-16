package com.powerzhou.dogstudy.injector.component;

import com.powerzhou.dogstudy.injector.PerFragment;
import com.powerzhou.dogstudy.injector.modules.StudyListFragmentModule;
import com.powerzhou.dogstudy.uimodule.study.subviewlist.StudyListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@PerFragment
@Component(dependencies = AppComponent.class,modules = StudyListFragmentModule.class)
public interface StudyListFragmentComponent {
    void inject(StudyListFragment fragment);
}
