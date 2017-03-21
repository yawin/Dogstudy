package com.powerzhou.dogstudy.uimodule.study.subview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.injector.component.DaggerStudySubListComponent;
import com.powerzhou.dogstudy.injector.modules.StudySubListModule;
import com.powerzhou.dogstudy.uimodule.base.BaseSwipeBackActivity;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyItemInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.SubListParam;
import com.powerzhou.dogstudy.uimodule.widget.EmptyLayout;
import com.powerzhou.dogstudy.util.SwipeRefreshHelper;
import com.powerzhou.recylerview.adapter.BaseQuickAdapter;
import com.powerzhou.recylerview.helper.RecyclerViewHelper;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class SubListActivity extends BaseSwipeBackActivity<IRxBusPresenter> implements IStudySubListView{

    public static final String SUBLIST_KEY = "sublist_key";

    @BindView(R.id.study_list_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_subtitle)
    TextView tvSubTitle;

    @OnClick(R.id.iv_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:{
                this.finish();
                break;
            }
            default:
                break;
        }
    }

    @Inject
    BaseQuickAdapter adapter;

    private StudyInfo studyInfo;

    public static void launch(Context context, StudyInfo studyInfo){
        Intent intent = new Intent(context, SubListActivity.class);
        intent.putExtra(SUBLIST_KEY, studyInfo);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_study_sub_list;
    }

    @Override
    protected void initInjector() {
        DaggerStudySubListComponent.builder().appComponent(getAppComponent())
                .studySubListModule(new StudySubListModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {
        studyInfo = (StudyInfo)getIntent().getSerializableExtra(SUBLIST_KEY);
        tvSubTitle.setText(studyInfo.getStudyType().getTypeName());
        initSwipeRefresh();
        SlideInRightAnimationAdapter animAdapter = new SlideInRightAnimationAdapter(adapter);
        RecyclerViewHelper.initRecyclerViewV(this, recyclerView, true, new AlphaInAnimationAdapter(animAdapter));
    }

    @Override
    protected void updateViews() {
        mPresenter.getData(new SubListParam(studyInfo));
    }

    @Override
    public void loadData(List<StudyItemInfo> list) {
        if (list != null && list.size() > 0) {
            adapter.updateItems(list);
        } else {
            // TODO: 2017/3/16 0016
        }
    }
    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
        SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
        SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);
        SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);
    }

    @Override
    public void showNetError(final EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(onRetryListener);
        }
        SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
    }
    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {
        if (mSwipeRefresh != null) {
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews();
                }
            });
        }
    }
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void loadMoreData(List<StudyItemInfo> data) {

    }

    @Override
    public void loadNoData() {

    }
}
