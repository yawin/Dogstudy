package com.powerzhou.dogstudy.presenter;

import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.BaseParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyListParam;
import com.powerzhou.dogstudy.uimodule.dao.operate.StudyDao;
import com.powerzhou.dogstudy.uimodule.study.subviewlist.IStudyListView;

import java.io.File;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyListPresenter implements IRxBusPresenter {
    private final IStudyListView studyListView;

    private final RxBus rxBus;

    public StudyListPresenter(IStudyListView studyListView, RxBus rxBus) {
        this.studyListView = studyListView;
        this.rxBus = rxBus;
    }

    @Override
    public void getData(BaseParam baseParam) {
        if(baseParam != null && baseParam instanceof StudyListParam){
            StudyListParam studyListParam = (StudyListParam)baseParam;
            File[] files = StudyDao.getFileListByStudyType(studyListParam.channelType);
            studyListView.loadData(files);
        }
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
