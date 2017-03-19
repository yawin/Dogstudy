package com.powerzhou.dogstudy.util;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class HookViewUtil {
    /**
     * getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
            HookViewUtil.hookViewOnClickListener(next_step);
            }
            });
     * @param view
     */
    public static void hookViewOnClickListener(View view){
        if(view == null){
            return;
        }
        try {
            //first ,get ListenerInfo object
            /**
             * getDeclaredMethod 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法,也包括它所实现接口的方法，但不包括没有重载的父类的方法。
             */
            //在这里需要用android.view.View，这样通过declaredMethod方法才能获取到getListenerInfo method对象
            Method method = Class.forName("android.view.View").getDeclaredMethod("getListenerInfo");
            if(!method.isAccessible()) {
                method.setAccessible(true);
            }
            Object listenerInfoObj = method.invoke(view);
//            Field listenerField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnClickListener");
            Field listenerField = listenerInfoObj.getClass().getDeclaredField("mOnClickListener");
            if(!listenerField.isAccessible()) {
                listenerField.setAccessible(true);
            }
            View.OnClickListener listenerObj = (View.OnClickListener)listenerField.get(listenerInfoObj);
            listenerField.set(listenerInfoObj,new OnViewClickListenerProxy(listenerObj));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * view 事件点击代理，防止重复点击
     *
     */
    private static class OnViewClickListenerProxy implements View.OnClickListener{
        private View.OnClickListener originListener;

        private long preClickTime = 0L;
        private static int MIN_CLICK_DELAY_TIME = 1000;

        private OnViewClickListenerProxy(View.OnClickListener originListener){
            this.originListener = originListener;
        }

        @Override
        public void onClick(View v) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - preClickTime>MIN_CLICK_DELAY_TIME){
                preClickTime = currentTime;
                if(originListener != null){
                    originListener.onClick(v);
                }
            }
        }
    }
}
