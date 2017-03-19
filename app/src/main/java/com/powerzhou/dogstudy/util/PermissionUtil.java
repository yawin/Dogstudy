package com.powerzhou.dogstudy.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class PermissionUtil {

    private static String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static int REQUEST_CODE = 0;

    public static boolean isSDPermission(Activity context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        return ContextCompat.checkSelfPermission(context, permissions[0]) == PackageManager.PERMISSION_GRANTED;
    }

    public static void checkPermissions(Activity context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        int grant = ContextCompat.checkSelfPermission(context, permissions[0]);
        if(grant != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(context,permissions,REQUEST_CODE);
        }
    }

}
