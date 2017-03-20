package com.powerzhou.dogstudy.uimodule.study.subviewlist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.injector.component.DaggerStudyListFragmentComponent;
import com.powerzhou.dogstudy.injector.modules.StudyListFragmentModule;
import com.powerzhou.dogstudy.uimodule.base.BaseFragment;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyListParam;
import com.powerzhou.recylerview.adapter.BaseQuickAdapter;
import com.powerzhou.recylerview.helper.RecyclerViewHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;


/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyListFragment extends BaseFragment<IRxBusPresenter> implements IStudyListView {
    private static final String STUDY_TYPE_KEY = "StudyType";

    @BindView(R.id.study_list_view)
    RecyclerView recyclerView;

    private int studyType;

    @Inject
    BaseQuickAdapter mAdapter;


    public static StudyListFragment newInstance(int studyType) {
        StudyListFragment fragment = new StudyListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(STUDY_TYPE_KEY, studyType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studyType = getArguments().getInt(STUDY_TYPE_KEY);
        }
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_study_list;
    }
    @Override
    protected void initInjector() {
        DaggerStudyListFragmentComponent.builder().appComponent(getAppComponent())
                .studyListFragmentModule(new StudyListFragmentModule(this))
                .build().inject(this);
    }
    @Override
    protected void initViews() {
        SlideInRightAnimationAdapter animAdapter = new SlideInRightAnimationAdapter(mAdapter);
        RecyclerViewHelper.initRecyclerViewV(mContext, recyclerView, true, new AlphaInAnimationAdapter(animAdapter));
//        mAdapter.setRequestDataListener(new OnRequestDataListener() {
//            @Override
//            public void onLoadMore() {
//                mPresenter.getMoreData();
//            }
//        });
    }

    @Override
    protected void updateViews() {
        mPresenter.getData(new StudyListParam(studyType));
    }

    @Override
    public void loadData(List<StudyInfo> list) {
        if (list != null && list.size() > 0) {
            mAdapter.updateItems(list);
        } else {
            // TODO: 2017/3/16 0016
        }
    }

    @Override
    public void loadMoreData(List<StudyInfo> data) {

    }

    @Override
    public void loadNoData() {

    }
}
