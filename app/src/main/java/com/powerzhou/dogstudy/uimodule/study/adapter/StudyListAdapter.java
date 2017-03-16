package com.powerzhou.dogstudy.uimodule.study.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.powerzhou.dogstudy.R;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class StudyListAdapter extends BaseQuickAdapter<File,BaseViewHolder> {

    public StudyListAdapter(int layoutResId) {
        super(layoutResId);
    }

    public StudyListAdapter(int layoutResId, List<File> data) {
        super(layoutResId, data);
    }

    @Override
    public void setNewData(List<File> data) {
        super.setNewData(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, File item) {
        helper.setText(R.id.tx_study_item,item.getName());
    }
}
