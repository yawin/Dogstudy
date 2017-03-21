package com.powerzhou.dogstudy.uimodule.study.subview;

import android.content.Context;
import android.view.View;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyItemInfo;
import com.powerzhou.dogstudy.uimodule.study.content.StudyContentActivity;
import com.powerzhou.recylerview.adapter.BaseMultiItemQuickAdapter;
import com.powerzhou.recylerview.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class SubListAdapter extends BaseMultiItemQuickAdapter<StudyItemInfo> {

    public SubListAdapter(Context context, List<StudyItemInfo> data) {
        super(context, data);
    }

    public SubListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void attachItemType() {
        addItemType(0, R.layout.adapter_study_list_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, final StudyItemInfo item) {
        holder.setText(R.id.tx_study_item, item.getTitle());
        holder.setOnClickListener(R.id.tx_study_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudyContentActivity.launch(mContext, item);
            }
        });
    }

}
