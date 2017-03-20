package com.powerzhou.dogstudy.uimodule.study.subviewlist;


import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.BaseParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyListParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;
import com.powerzhou.dogstudy.uimodule.dao.operate.StudyDao;

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

public class StudyListPresenter implements IRxBusPresenter {
    private final IStudyListView studyListView;

    private final RxBus rxBus;

    public StudyListPresenter(IStudyListView studyListView, RxBus rxBus) {
        this.studyListView = studyListView;
        this.rxBus = rxBus;
    }

    @Override
    public void getData(BaseParam baseParam) {
        if (baseParam != null && baseParam instanceof StudyListParam) {
            StudyListParam studyListParam = (StudyListParam) baseParam;
            studyListView.showLoading();
            if(studyListParam.studyType == StudyInfo.ITEM_TYPE_LOCAL) {
                Observable.create(new Observable.OnSubscribe<File[]>() {
                    @Override
                    public void call(Subscriber<? super File[]> subscriber) {
                        File[] files = StudyDao.getAllFolders();
                        subscriber.onNext(files);
                    }
                }).subscribeOn(Schedulers.newThread())
                        .map(new Func1<File[], List<StudyInfo>>() {
                            @Override
                            public List<StudyInfo> call(File[] files) {
                                List<StudyInfo> infoList = new ArrayList<>();
                                StudyInfo info = null;
                                if (files != null && files.length > 0) {
                                    for (File file : files) {
                                        info = new StudyInfo(StudyInfo.ITEM_TYPE_LOCAL, new StudyType(file.getName()));
                                        infoList.add(info);
                                    }
                                }
                                return infoList;
                            }
                        })
                        .compose(studyListView.<List<StudyInfo>>bindToLife())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<StudyInfo>>() {
                            @Override
                            public void call(List<StudyInfo> infoList) {
                                studyListView.hideLoading();
                                studyListView.loadData(infoList);
                            }
                        });
            }else{
                List<StudyInfo> infoList = StudyDao.getInstance().getStudyInfos();
                studyListView.hideLoading();
                studyListView.loadData(infoList);
            }
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
        rxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        rxBus.unSubscribe(this);
    }
}
