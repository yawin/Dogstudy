package com.powerzhou.dogstudy.uimodule.study.subviewlist;

import android.content.Context;
import android.view.View;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.uimodule.study.subview.SubListActivity;
import com.powerzhou.recylerview.adapter.BaseMultiItemQuickAdapter;
import com.powerzhou.recylerview.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class StudyListAdapter extends BaseMultiItemQuickAdapter<StudyInfo> {

    public StudyListAdapter(Context context, List<StudyInfo> data) {
        super(context, data);
    }

    public StudyListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void attachItemType() {
        addItemType(StudyInfo.ITEM_TYPE_ITERNET, R.layout.adapter_study_list_item);
        addItemType(StudyInfo.ITEM_TYPE_LOCAL, R.layout.adapter_study_list_item);
    }

    @Override
    protected void convert(BaseViewHolder holder,final StudyInfo item) {
        switch (item.getItemType()) {
            case StudyInfo.ITEM_TYPE_ITERNET:
            case StudyInfo.ITEM_TYPE_LOCAL:
                holder.setText(R.id.tx_study_item,item.getStudyType().getTypeName());
                holder.setOnClickListener(R.id.tx_study_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SubListActivity.launch(mContext,item);
                    }
                });
                break;
        }
    }


}
