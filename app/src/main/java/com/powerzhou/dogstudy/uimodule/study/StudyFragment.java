package com.powerzhou.dogstudy.uimodule.study;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.uimodule.base.BaseFragment;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public class StudyFragment extends BaseFragment<IRxBusPresenter> {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, getString(R.string.nav_study));
    }

    @Override
    protected void updateViews() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        mPresenter.unregisterRxBus();
    }
}
