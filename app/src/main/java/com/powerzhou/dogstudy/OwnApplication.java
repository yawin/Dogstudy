package com.powerzhou.dogstudy;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import com.powerzhou.dogstudy.injector.component.AppComponent;
import com.powerzhou.dogstudy.injector.component.DaggerAppComponent;
import com.powerzhou.dogstudy.injector.modules.AppModule;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoMaster;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.dao.operate.StudyDao;
import com.powerzhou.dogstudy.util.Constant;
import com.powerzhou.dogstudy.util.LogUtil;
import com.powerzhou.dogstudy.util.SDCardUtils;
import com.powerzhou.dogstudy.util.ToastUtils;
import com.squareup.leakcanary.LeakCanary;


import java.io.File;


/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class OwnApplication extends Application {

    private static final String DB_NAME = "info_db.db";

    private static AppComponent appComponet;

    private static OwnApplication instance;

    private DaoSession mDaoSession;
    private RxBus rxBus = null;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("OwnApplication OnCreate");
        ToastUtils.init(this);
        instance = this;
    }

    public void initInjector (){
        rxBus = new RxBus();
        appComponet = DaggerAppComponent.builder().appModule(new AppModule(this,mDaoSession,rxBus)).build();
    }

    /**
     * @return Application Context
     */
    public Context getApplication(){
        return appComponet.getContext();
    }

    public static OwnApplication getOwnApplication(){
        return instance;
    }

    public static AppComponent getAppComponet(){
        return appComponet;
    }

    /**
     * 初始化数据库
     */
    public void initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new ContextWrapper(this) {
            @Override
            public File getDatabasePath(String name) {
                if (!SDCardUtils.isAvailable()) {
                    return null;
                } else {
                    String dbPath = getRootPath() + File.separator +Constant.DIR_DB +File.separator +name;
                    File file = new File(dbPath);
                    if(!file.exists()){
                        try {
                            file.createNewFile();
                        }catch (Exception e){
                        }
                    }
                    return file;
                }
            }
            //for 2.3
            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
                return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
            }
            //for 4.0
            @Override
            public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
            }
        },DB_NAME,null);
        SQLiteDatabase database = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(database).newSession();
    }

    public static String getRootPath(){
        return SDCardUtils.getSDPath()+File.separator+ Constant.DIR_ROOT;
    }

    public void initConfig() {
        LeakCanary.install(this);
        StudyDao.getInstance().initStudyData(this);
    }

    public void initDir(){
        String rootDir = getRootPath();
        File rootFile = new File(rootDir);
        if(!rootFile.exists()){
            rootFile.mkdirs();
        }
        String dbDir = rootDir + File.separator + Constant.DIR_DB;
        File dbFile = new File(dbDir);
        if(!dbFile.exists()){
            dbFile.mkdirs();
        }
        dbDir = rootDir + File.separator + Constant.DIR_IMAGE;
        dbFile = new File(dbDir);
        if(!dbFile.exists()){
            dbFile.mkdirs();
        }
    }
}
