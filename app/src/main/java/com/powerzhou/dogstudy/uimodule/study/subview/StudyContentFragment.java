package com.powerzhou.dogstudy.uimodule.study.subview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.injector.component.DaggerStudyContentFragmentComponent;
import com.powerzhou.dogstudy.injector.modules.StudyContentFragmentModule;
import com.powerzhou.dogstudy.uimodule.base.BaseFragment;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;
import com.powerzhou.dogstudy.util.AssetsHelper;
import com.powerzhou.dogstudy.util.LogUtil;
import com.zzhoujay.richtext.RichText;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyContentFragment extends BaseFragment<IRxBusPresenter> implements IStudyContentView {
    private static final String CHANNEL_TYPE_KEY = "ChannelTypeKey";

    @BindView(R.id.study_content_view)
    LinearLayout content;

    private String channelType;


    public static StudyContentFragment newInstance(String channelType) {
        StudyContentFragment fragment = new StudyContentFragment();
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
        String[] result = AssetsHelper.readLists(getContext(),channelType);
        for(String res : result){
            LogUtil.d(res);
        }
        if(channelType.equals("arithmetic")) {
            String ss = AssetsHelper.readData(getContext(), channelType + "/BinaryInsertSort.md");
            if(ss == null){
                return;
            }
            LogUtil.d(ss);
            TextView textView = new TextView(getContext());
            textView.setTextColor(Color.BLACK);
            RichText.fromMarkdown(ss).into(textView);
            content.addView(textView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void loadData(StudyType studyType) {
    }
}
