package com.powerzhou.dogstudy.injector.modules;

import android.content.Context;

import com.powerzhou.dogstudy.OwnApplication;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Module
public class AppModule {

    private final OwnApplication ownApplication;
    private final DaoSession daoSession;
    private final RxBus rxBus;

    public AppModule(OwnApplication ownApplication,DaoSession daoSession,RxBus rxBus) {
        this.ownApplication = ownApplication;
        this.daoSession = daoSession;
        this.rxBus = rxBus;
    }

    @Provides
    @Singleton
    public Context getOwnApplication() {
        return ownApplication;
    }

    @Provides
    @Singleton
    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Provides
    @Singleton
    public RxBus getRxBus(){
        return rxBus;
    }
}
