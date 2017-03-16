package com.powerzhou.dogstudy.uimodule.dao.operate;

import android.content.Context;

import com.powerzhou.dogstudy.OwnApplication;
import com.powerzhou.dogstudy.uimodule.dao.bean.DaoSession;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyType;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyTypeDao;
import com.powerzhou.dogstudy.util.AssetsHelper;
import com.powerzhou.dogstudy.util.Constant;
import com.powerzhou.dogstudy.util.FileUtils;
import com.powerzhou.dogstudy.util.GsonHelper;
import com.powerzhou.dogstudy.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyDao {
    private static final String sourceRootPath = OwnApplication.getRootPath()+ File.separator+ Constant.DIR_SOURCE;
    private StudyDao() {
    }

    /**
     * @param context
     * @param daoSession
     */
    public static void updateStudyChannelData(Context context, DaoSession daoSession) {
        String sourceFilePath = sourceRootPath + File.separator+Constant.FILE_STUDYCHANNEL;
        try {
            List<StudyType> sAllChannels = GsonHelper.convertEntities(FileUtils.readFile(sourceFilePath).toString(), StudyType.class);
            StudyTypeDao beanDao = daoSession.getStudyTypeDao();
            if (beanDao.count() == 0) {
                beanDao.insertInTx(sAllChannels);
            }else{
                beanDao.deleteAll();
                beanDao.insertInTx(sAllChannels);
            }
        }catch (Exception e){
        }
    }

    public static File[] getFileListByStudyType(String studyType){
        if(StringUtils.isEmpty(studyType)){
            return null;
        }
        String typePath = sourceRootPath + File.separator + studyType;
        if(FileUtils.isFolderExist(typePath)){
            return FileUtils.getFileList(typePath);
        }
        return null;
    }
}
