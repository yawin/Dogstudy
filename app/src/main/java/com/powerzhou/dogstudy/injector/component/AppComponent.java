package com.powerzhou.dogstudy.injector.component;

import android.content.Context;

import com.powerzhou.dogstudy.injector.modules.AppModule;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.dao.bean.account.DaoSession;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    /**
     * 在这里声明接口是为了依赖类可以调用
     * @return
     */
    Context getContext();
    DaoSession getDaoSession();
    RxBus getRxBus();

}
