package com.powerzhou.dogstudy.uimodule.study;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.injector.component.DaggerStudyFragmentComponent;
import com.powerzhou.dogstudy.injector.modules.StudyFragmentModule;
import com.powerzhou.dogstudy.rxbus.event.ChannelEvent;
import com.powerzhou.dogstudy.uimodule.base.BaseFragment;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;
import com.powerzhou.dogstudy.uimodule.study.subviewlist.StudyListFragment;
import com.powerzhou.dogstudy.uimodule.widget.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindArray;
import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public class StudyFragment extends BaseFragment<IRxBusPresenter> implements IStudyView{

    @Nullable
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @Nullable
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindArray(R.array.tab_names)
    String[] tab_names;

    @Inject
    ViewPagerAdapter mPageAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_study;
    }

    @Override
    protected void initInjector() {
        DaggerStudyFragmentComponent.builder()
                .appComponent(getAppComponent())
                .studyFragmentModule(new StudyFragmentModule(this))
                .build().inject(this);
    }
    @Override
    public void loadData(boolean hasLocalData,boolean hasInternet) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        if(hasLocalData){
            titles.add(tab_names[0]);
            fragments.add(StudyListFragment.newInstance(tab_names[0]));
        }
        if(hasInternet){
            titles.add(tab_names[1]);
            fragments.add(StudyListFragment.newInstance(tab_names[1]));
        }
        mPageAdapter.setItems(fragments, titles);
    }

    @Override
    protected void initViews() {
        if(mToolBar!= null) {
            initToolBar(mToolBar, true, getString(R.string.nav_study));
        }
        setHasOptionsMenu(true);
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    protected void updateViews() {
        mPresenter.getData();
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_channel, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.item_channel) {
//            //go to channel setting page
//            return true;
//        }
//        return false;
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
