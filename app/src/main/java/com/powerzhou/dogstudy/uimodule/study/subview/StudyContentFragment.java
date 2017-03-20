package com.powerzhou.dogstudy.uimodule.study.subview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.injector.component.DaggerStudyContentFragmentComponent;
import com.powerzhou.dogstudy.injector.modules.StudyContentFragmentModule;
import com.powerzhou.dogstudy.uimodule.base.BaseFragment;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;


import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyContentFragment extends BaseFragment<IRxBusPresenter> implements IStudyContentView {
    private static final String ADDRESS = "filepath";

    @BindView(R.id.study_content_view)
    LinearLayout content;

    private String filepath;


    public static StudyContentFragment newInstance(String filepath) {
        StudyContentFragment fragment = new StudyContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ADDRESS, filepath);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filepath = getArguments().getString(ADDRESS);
        }
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_study_content;
    }

    @Override
    protected void initInjector() {
        DaggerStudyContentFragmentComponent.builder()
                .appComponent(getAppComponent())
                .studyContentFragmentModule(new StudyContentFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {
    }

    @Override
    public void loadData(String filepath) {

    }
}
