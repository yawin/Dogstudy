package com.powerzhou.dogstudy.injector.component;

import com.powerzhou.dogstudy.injector.PerActivity;
import com.powerzhou.dogstudy.injector.modules.StudyContentModule;
import com.powerzhou.dogstudy.uimodule.study.content.StudyContentActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = StudyContentModule.class)
public interface StudyContentComponent {
    void inject(StudyContentActivity view);
}
