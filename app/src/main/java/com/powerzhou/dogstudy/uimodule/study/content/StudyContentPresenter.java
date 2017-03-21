package com.powerzhou.dogstudy.uimodule.study.content;

import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.BaseParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyContentParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyItemInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.SubListParam;
import com.powerzhou.dogstudy.uimodule.dao.operate.StudyDao;
import com.powerzhou.dogstudy.uimodule.study.subview.IStudySubListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyContentPresenter implements IRxBusPresenter {
    private final IStudyContentView studyContentView;

    private final RxBus rxBus;

    public StudyContentPresenter(IStudyContentView studyContentView, RxBus rxBus) {
        this.studyContentView = studyContentView;
        this.rxBus = rxBus;
    }

    @Override
    public void getData(BaseParam baseParam) {
        StudyContentParam subListParam = (StudyContentParam) baseParam;
        final String filePath = subListParam.filePath;
        studyContentView.showLoading();
        Observable.create(new Observable.OnSubscribe<StringBuilder>() {
            @Override
            public void call(Subscriber<? super StringBuilder> subscriber) {
                StringBuilder sb = StudyDao.getInstance().getContentFromPath(filePath);
                subscriber.onNext(sb);
            }
        }).subscribeOn(Schedulers.newThread())
                .map(new Func1<StringBuilder, String>() {
                    @Override
                    public String call(StringBuilder content) {
                        return content.toString();
                    }
                })
                .compose(studyContentView.<String>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String content) {
                        studyContentView.hideLoading();
                        studyContentView.loadData(content);
                    }
                });
    }

    @Override
    public void getMoreData(BaseParam baseParam) {

    }

    @Override
    public void getData() {

    }

    @Override
    public void getMoreData() {

    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = rxBus.doSubscribe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
            }
        });
        rxBus.addSubscription(this,subscription);
    }

    @Override
    public void unregisterRxBus() {
        rxBus.unSubscribe(this);
    }
}
