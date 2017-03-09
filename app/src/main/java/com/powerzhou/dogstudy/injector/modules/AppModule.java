package com.powerzhou.dogstudy.injector.modules;

import android.app.Application;
import android.content.Context;

import com.powerzhou.dogstudy.OwnApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Module
public class AppModule {

    private final OwnApplication ownApplication;

    public AppModule(OwnApplication ownApplication) {
        this.ownApplication = ownApplication;
    }

    @Provides
    @Singleton
    public Context getOwnApplication() {
        return ownApplication;
    }
}
