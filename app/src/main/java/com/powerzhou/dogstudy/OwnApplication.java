package com.powerzhou.dogstudy;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.powerzhou.dogstudy.injector.component.AppComponent;
import com.powerzhou.dogstudy.injector.component.DaggerAppComponent;
import com.powerzhou.dogstudy.injector.modules.AppModule;
import com.powerzhou.dogstudy.uimodule.dao.bean.account.DaoMaster;
import com.powerzhou.dogstudy.uimodule.dao.bean.account.DaoSession;
import com.powerzhou.dogstudy.util.Constant;


import java.io.File;


/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class OwnApplication extends Application {

    private static final String DB_NAME = "info_db.db";

    private AppComponent appComponet;

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        _initDir();
        _initDatabase();
        _initInjector();
    }

    private void _initInjector (){
        appComponet = DaggerAppComponent.builder().appModule(new AppModule(this,mDaoSession)).build();
    }

    /**
     * @return Application Context
     */
    public Context getOwnApplication(){
        return appComponet.getContext();
    }

    /**
     * 初始化数据库
     */
    private void _initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new ContextWrapper(this) {
            @Override
            public File getDatabasePath(String name) {
                boolean sdExist = android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState());
                if (!sdExist) {
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
        return android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+ Constant.DIR_ROOT;
    }

    private void _initDir(){
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
