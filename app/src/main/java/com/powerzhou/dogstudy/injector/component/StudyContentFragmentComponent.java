package com.powerzhou.dogstudy.injector.component;

import com.powerzhou.dogstudy.injector.PerFragment;
import com.powerzhou.dogstudy.injector.modules.StudyContentFragmentModule;
import com.powerzhou.dogstudy.uimodule.study.subview.StudyContentFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@PerFragment
@Component(dependencies = AppComponent.class,modules = StudyContentFragmentModule.class)
public interface StudyContentFragmentComponent {
    void inject(StudyContentFragment fragment);
}
