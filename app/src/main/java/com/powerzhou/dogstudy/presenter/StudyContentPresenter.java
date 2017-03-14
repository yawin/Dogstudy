package com.powerzhou.dogstudy.presenter;

import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.study.subview.IStudyContentView;

import rx.Subscription;
import rx.functions.Action1;

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
