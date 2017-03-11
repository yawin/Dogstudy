package com.powerzhou.dogstudy.util;

import android.util.Log;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class LogUtil {

    private static final boolean isLoggerable = true;
    private static final String defaultTag = "Powerzhou";


    public static void d(String message){
        if(isLoggerable){
            Log.d(defaultTag,message == null?"":message);
        }
    }
    public static void d(String tag, String message){
        if(isLoggerable){
            Log.d(tag == null?"":tag,message == null?"":message);
        }
    }

    public static void e( String message){
        if(isLoggerable){
            Log.e(defaultTag,message == null?"":message);
        }
    }

    public static void e(String tag, String message){
        if(isLoggerable){
            Log.e(tag == null?"":tag,message == null?"":message);
        }
    }

    public static void w(String tag, String message){
        if(isLoggerable){
            Log.w(tag == null?"":tag,message == null?"":message);
        }
    }

    public static void w( String message){
        if(isLoggerable){
            Log.w(defaultTag,message == null?"":message);
        }
    }
    public static void i(String tag, String message){
        if(isLoggerable){
            Log.i(tag == null?"":tag,message == null?"":message);
        }
    }

    public static void i( String message){
        if(isLoggerable){
            Log.i(defaultTag,message == null?"":message);
        }
    }
}
