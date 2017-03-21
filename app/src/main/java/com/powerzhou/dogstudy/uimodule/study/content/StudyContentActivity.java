package com.powerzhou.dogstudy.uimodule.study.content;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.injector.component.DaggerStudyContentComponent;
import com.powerzhou.dogstudy.injector.component.DaggerStudySubListComponent;
import com.powerzhou.dogstudy.injector.modules.StudyContentModule;
import com.powerzhou.dogstudy.injector.modules.StudySubListModule;
import com.powerzhou.dogstudy.uimodule.base.BaseSwipeBackActivity;
import com.powerzhou.dogstudy.uimodule.base.IRxBusPresenter;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyContentParam;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyItemInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.SubListParam;
import com.powerzhou.dogstudy.uimodule.study.subview.IStudySubListView;
import com.powerzhou.dogstudy.uimodule.widget.EmptyLayout;
import com.powerzhou.dogstudy.util.StringUtils;
import com.powerzhou.dogstudy.util.SwipeRefreshHelper;
import com.powerzhou.recylerview.adapter.BaseQuickAdapter;
import com.powerzhou.recylerview.helper.RecyclerViewHelper;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.zzhoujay.richtext.RichText;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class StudyContentActivity extends BaseSwipeBackActivity<IRxBusPresenter> implements IStudyContentView{

    public static final String SUB_CONTENT = "sub_content";

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.tv_content)
    TextView tvContent;
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
    private StudyItemInfo itemInfo;

    public static void launch(Context context, StudyItemInfo itemInfo){
        Intent intent = new Intent(context, StudyContentActivity.class);
        intent.putExtra(SUB_CONTENT, itemInfo);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_study_content;
    }

    @Override
    protected void initInjector() {
        DaggerStudyContentComponent.builder().appComponent(getAppComponent())
                .studyContentModule(new StudyContentModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {
        itemInfo = (StudyItemInfo)getIntent().getSerializableExtra(SUB_CONTENT);
        tvSubTitle.setText(itemInfo.getTitle());
        initSwipeRefresh();
    }

    @Override
    protected void updateViews() {
        mPresenter.getData(new StudyContentParam(itemInfo.getAddress()));
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
    public void loadData(String content) {
        if(StringUtils.isEmpty(content)){
            tvContent.setText("nothing to show!");
        }else {
            RichText.fromMarkdown(content).into(tvContent);
        }
    }

    @Override
    public void loadMoreData(String data) {

    }

    @Override
    public void loadNoData() {

    }
}
