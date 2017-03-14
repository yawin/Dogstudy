package com.powerzhou.dogstudy.uimodule.dao.operate;

import android.content.Context;

import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyTypeDao;
import com.powerzhou.dogstudy.util.AssetsHelper;
import com.powerzhou.dogstudy.util.GsonHelper;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyDao {
    // 所有栏目
    private static List<StudyType> sAllChannels;

    private StudyDao() {
    }

    /**
     * 更新本地数据，如果数据库新闻列表栏目为 0 则添加头 3 个栏目
     *
     * @param context
     * @param daoSession
     */
    public static void updateStudyChannelData(Context context, DaoSession daoSession) {
        sAllChannels = GsonHelper.convertEntities(AssetsHelper.readData(context, "StudyChannel"), StudyType.class);
        StudyTypeDao beanDao = daoSession.getStudyTypeDao();
        if (beanDao.count() == 0) {
            beanDao.insertInTx(sAllChannels.subList(0, 4));
        }
    }

    /**
     * 获取所有栏目
     *
     * @return
     */
    public static List<StudyType> getAllChannels() {
        return sAllChannels;
    }
}
