package com.powerzhou.dogstudy.uimodule.study.subview;

import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.BaseParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyItemInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.SubListParam;
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

public class SubListPresenter implements IRxBusPresenter {
    private final IStudySubListView studyContentView;

    private final RxBus rxBus;

    public SubListPresenter(IStudySubListView studyContentView, RxBus rxBus) {
        this.studyContentView = studyContentView;
        this.rxBus = rxBus;
    }

    @Override
    public void getData(BaseParam baseParam) {
        SubListParam subListParam = (SubListParam) baseParam;
        final StudyInfo studyInfo = subListParam.studyInfo;
        studyContentView.showLoading();
            Observable.create(new Observable.OnSubscribe<File[]>() {
                @Override
                public void call(Subscriber<? super File[]> subscriber) {
                    File[] files = StudyDao.getAllSubFoldersByStudyType(studyInfo);
                    subscriber.onNext(files);
                }
            }).subscribeOn(Schedulers.newThread())
                    .map(new Func1<File[], List<StudyItemInfo>>() {
                        @Override
                        public List<StudyItemInfo> call(File[] files) {
                            List<StudyItemInfo> infoList = new ArrayList<>();
                            StudyItemInfo itemInfo = null;
                            if (files != null && files.length > 0) {
                                for (File file : files) {
                                    itemInfo = new StudyItemInfo(studyInfo.getStudyType().getTypeName(),file.getName(),file.getAbsolutePath());
                                    infoList.add(itemInfo);
                                }
                            }
                            return infoList;
                        }
                    })
                    .compose(studyContentView.<List<StudyItemInfo>>bindToLife())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<StudyItemInfo>>() {
                        @Override
                        public void call(List<StudyItemInfo> infoList) {
                            studyContentView.hideLoading();
                            studyContentView.loadData(infoList);
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
