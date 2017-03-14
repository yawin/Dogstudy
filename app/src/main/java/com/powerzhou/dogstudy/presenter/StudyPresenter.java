package com.powerzhou.dogstudy.presenter;

import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyTypeDao;
import com.powerzhou.dogstudy.uimodule.study.IStudyView;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyPresenter implements IRxBusPresenter {

    private final IStudyView studyView;

    private final StudyTypeDao studyTypeDao;

    private final RxBus rxBus;

    public StudyPresenter(IStudyView studyView, StudyTypeDao studyTypeDao, RxBus rxBus) {
        this.studyView = studyView;
        this.studyTypeDao = studyTypeDao;
        this.rxBus = rxBus;
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public void getData() {
        studyTypeDao.queryBuilder().rx().list()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<StudyType>>() {
                    @Override
                    public void call(List<StudyType> studyTypeBeen) {
                        studyView.loadData(studyTypeBeen);
                    }
                });
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
