package com.powerzhou.dogstudy.injector.component;

import com.powerzhou.dogstudy.injector.PerFragment;
import com.powerzhou.dogstudy.injector.modules.StudyFragmentModule;
import com.powerzhou.dogstudy.uimodule.study.StudyFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@PerFragment
@Component(dependencies = AppComponent.class,modules = StudyFragmentModule.class)
public interface StudyFragmentComponent {
    void inject(StudyFragment fragment);
}
