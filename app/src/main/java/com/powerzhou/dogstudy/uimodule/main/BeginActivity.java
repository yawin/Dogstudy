package com.powerzhou.dogstudy.uimodule.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpinKitView;
import com.powerzhou.dogstudy.OwnApplication;
import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.uimodule.base.BaseActivity;
import com.powerzhou.dogstudy.uimodule.widget.EmptyLayout;
import com.powerzhou.dogstudy.util.LogUtil;
import com.powerzhou.dogstudy.util.PermissionUtil;
import com.powerzhou.dogstudy.util.ToastUtils;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BeginActivity extends BaseActivity {

    @BindView(R.id.bp_begin)
    SpinKitView kitView;

    /**
     * refer to http://www.jb51.net/article/78098.htm
     * 启动速度 测试 adb shell am start -W [packageName]/[packageName.MainActivity]
     * 执行成功后将返回三个测量到的时间：
         1、ThisTime:一般和TotalTime时间一样，除非在应用启动时开了一个透明的Activity预先处理一些事再显示出主Activity，这样将比TotalTime小。
         2、TotalTime:应用的启动时间，包括创建进程+Application初始化+Activity初始化到界面显示。
         3、WaitTime:一般比TotalTime大点，包括系统影响的耗时
     * 优化启动速度，启动界面的theme设置为一个比较简单的theme，启动完成后在onCreate再加载真实的theme
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBaseTheme);
        super.onCreate(savedInstanceState);
        if(!PermissionUtil.isSDPermission(this)) {
            PermissionUtil.checkPermissions(this);
        }else{
            initDataFlow();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_begin;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    private void initDataFlow(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                LogUtil.d(Thread.currentThread().toString());
                OwnApplication.getOwnApplication().initDir();
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.newThread())
                .map(new Func1<String, Void>() {
                    @Override
                    public Void call(String aVoid) {
                        LogUtil.d("initDatabase "+Thread.currentThread().toString());
                        OwnApplication.getOwnApplication().initDatabase();
                        try {
                            Thread.sleep(500);
                        }catch (Exception e){

                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Void, Void>() {
                    @Override
                    public Void call(Void aVoid) {
                        LogUtil.d(Thread.currentThread().toString());
                        return null;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Void, Void>() {
                    @Override
                    public Void call(Void aVoid) {
                        LogUtil.d("initInjector "+Thread.currentThread().toString());
                        OwnApplication.getOwnApplication().initInjector();
                        try {
                            Thread.sleep(500);
                        }catch (Exception e){

                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Void, Void>() {
                    @Override
                    public Void call(Void aVoid) {
                        LogUtil.d(Thread.currentThread().toString());
                        return null;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Void, Void>() {
                    @Override
                    public Void call(Void aVoid) {
                        LogUtil.d("initConfig "+Thread.currentThread().toString());
                        OwnApplication.getOwnApplication().initConfig();
                        try {
                            Thread.sleep(300);
                        }catch (Exception e){

                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        LogUtil.d("gotoMain "+Thread.currentThread().toString());
                        kitView.setVisibility(View.GONE);
                        gotoMain();
                    }
                });
    }

    private void gotoMain() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PermissionUtil.REQUEST_CODE){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                ToastUtils.showToast(R.string.begin_no_permission);
                finish();
                return;
            }
            initDataFlow();
        }
    }

    @Override
    protected void updateViews() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError(EmptyLayout.OnRetryListener onRetryListener) {

    }
}
