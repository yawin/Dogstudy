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
import com.powerzhou.dogstudy.uimodule.study.subview.StudyContentFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
    public void loadData(List<StudyType> typeList) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (StudyType bean : typeList) {
            titles.add(bean.getName());
            fragments.add(StudyContentFragment.newInstance(bean.getTypeName()));
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
        mPresenter.registerRxBus(ChannelEvent.class, new Action1<ChannelEvent>() {
            @Override
            public void call(ChannelEvent channelEvent) {
                _handleChannelEvent(channelEvent);
            }
        });
    }

    /**
     * 处理频道事件
     * @param channelEvent
     */
    private void _handleChannelEvent(ChannelEvent channelEvent) {
        switch (channelEvent.eventType) {
            case ChannelEvent.ADD_EVENT:
                mPageAdapter.addItem(StudyContentFragment.newInstance(channelEvent.studyType.getTypeName()),channelEvent.studyType.getName());
                break;
            case ChannelEvent.DEL_EVENT:
                // 如果是删除操作直接切换第一项，不然容易出现加载到不存在的Fragment
                mViewPager.setCurrentItem(0);
                mPageAdapter.delItem(channelEvent.studyType.getName());
                break;
            case ChannelEvent.SWAP_EVENT:
                mPageAdapter.swapItems(channelEvent.fromPos, channelEvent.toPos);
                break;
        }
    }

    @Override
    protected void updateViews() {

        mPresenter.getData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_channel, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_channel) {
            //go to channel setting page
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterRxBus();
    }
}
