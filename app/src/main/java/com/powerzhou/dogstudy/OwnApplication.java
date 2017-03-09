package com.powerzhou.dogstudy;

import android.app.Application;

import com.powerzhou.dogstudy.injector.component.AppComponent;
import com.powerzhou.dogstudy.injector.component.DaggerAppComponent;
import com.powerzhou.dogstudy.injector.modules.AppModule;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class OwnApplication extends Application {

    private AppComponent appComponet;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpComponent();
    }

    private void setUpComponent (){
        appComponet = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
