package com.powerzhou.dogstudy.uimodule.dao.operate;

import android.content.Context;

import com.powerzhou.dogstudy.OwnApplication;
import com.powerzhou.dogstudy.uimodule.dao.bean.StudyInfo;
import com.powerzhou.dogstudy.util.AssetsHelper;
import com.powerzhou.dogstudy.util.Constant;
import com.powerzhou.dogstudy.util.FileUtils;
import com.powerzhou.dogstudy.util.GsonHelper;
import com.powerzhou.dogstudy.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class StudyDao {

    private static StudyDao instance = new StudyDao();

    private static final String sourceRootPath = OwnApplication.getRootPath()+ File.separator+ Constant.DIR_SOURCE;
    //在线数据
    private List<StudyInfo> studyInfos;
    //本地数据
    private boolean isLocaldata = false;

    public static StudyDao getInstance(){
        return instance;
    }

    private StudyDao() {
    }

    public static File[] getAllFolders(){
        if(!FileUtils.isFolderExist(sourceRootPath)){
            return null;
        }
        return new File(sourceRootPath).listFiles();
    }
    public static File[] getAllSubFoldersByStudyType(StudyInfo studyInfo){
        if(!FileUtils.isFolderExist(sourceRootPath)){
            return null;
        }
        return new File(sourceRootPath+File.separator+studyInfo.getStudyType().getTypeName()).listFiles();
    }

    public void initStudyData(Context context){
        //初始化在线数据，暂时不支持放入数据库中
        studyInfos = GsonHelper.convertEntities(AssetsHelper.readData(context, Constant.FILE_STUDYCHANNEL),StudyInfo.class);
        //其次加载本地数据,列出source文件夹下所有的子文件夹和文件
        if(!FileUtils.isFolderExist(sourceRootPath)){
            isLocaldata = false;
            return;
        }
        isLocaldata = new File(sourceRootPath).listFiles().length > 0;
    }

    public boolean isLocaldata() {
        return isLocaldata;
    }

    public List<StudyInfo> getStudyInfos() {
        return studyInfos;
    }

    public StringBuilder getContentFromPath(String filePath){
        if(StringUtils.isEmpty(filePath)){
            return null;
        }
        File file = new File(filePath);
        if(!file.exists() || file.isDirectory()){
            return null;
        }
        BufferedReader sr = null;
        InputStreamReader isr = null;
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try{
            is = new FileInputStream(file);
            isr = new InputStreamReader(is,"UTF-8");
            sr = new BufferedReader(isr);
            String line = null;
            while((line = sr.readLine())!=null){
                sb.append(line).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
                if(isr != null){
                    isr.close();
                }
                if(sr != null){
                    sr.close();
                }
            }catch (Exception e){

            }
        }
        return sb;
    }

}
