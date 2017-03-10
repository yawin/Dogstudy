package com.powerzhou.dogstudy.injector.component;

import android.content.Context;

import com.powerzhou.dogstudy.injector.modules.AppModule;
import com.powerzhou.dogstudy.uimodule.dao.bean.account.DaoSession;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
    DaoSession getDaoSession();
}
