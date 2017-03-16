package com.powerzhou.dogstudy.uimodule.study.subviewlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.injector.component.DaggerStudyContentFragmentComponent;
import com.powerzhou.dogstudy.injector.component.DaggerStudyListFragmentComponent;
import com.powerzhou.dogstudy.injector.modules.StudyContentFragmentModule;
import com.powerzhou.dogstudy.injector.modules.StudyListFragmentModule;
import com.powerzhou.dogstudy.uimodule.base.BaseFragment;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyListParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;
import com.powerzhou.dogstudy.uimodule.dao.operate.StudyDao;
import com.powerzhou.dogstudy.uimodule.study.adapter.StudyListAdapter;
import com.powerzhou.dogstudy.uimodule.study.subview.IStudyContentView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyListFragment extends BaseFragment<IRxBusPresenter> implements IStudyListView {
    private static final String CHANNEL_TYPE_KEY = "ChannelTypeKey";

    @BindView(R.id.study_list_view)
    RecyclerView recyclerView;

    private String channelType;
    private StudyListAdapter adapter;

    public static StudyListFragment newInstance(String channelType) {
        StudyListFragment fragment = new StudyListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CHANNEL_TYPE_KEY, channelType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            channelType = getArguments().getString(CHANNEL_TYPE_KEY);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StudyListAdapter(R.layout.adapter_study_list_item);
        recyclerView.setAdapter(adapter);

    }
    @Override
    protected void updateViews() {
        mPresenter.getData(new StudyListParam(channelType));
    }

    @Override
    public void loadData(File[] content) {
      if(content != null && content.length > 0){
          List<File> list = new ArrayList<>();
          for(File file : content){
              list.add(file);
          }
          adapter.setNewData(list);
      }else{
          // TODO: 2017/3/16 0016
      }
    }
}
