package com.powerzhou.dogstudy.injector.modules;

import android.app.Application;
import android.content.Context;

import com.powerzhou.dogstudy.OwnApplication;
import com.powerzhou.dogstudy.uimodule.dao.bean.account.DaoSession;

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
    public AppModule(OwnApplication ownApplication,DaoSession daoSession) {
        this.ownApplication = ownApplication;
        this.daoSession = daoSession;
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
}
