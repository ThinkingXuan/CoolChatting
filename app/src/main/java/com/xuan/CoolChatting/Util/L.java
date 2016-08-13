package com.xuan.CoolChatting.Util;

import android.util.Log;

/**
 * Created by youxuan on 2016/8/11.
 * Log统一管理类
 */
public class L {

    private L(){
        /*cannot be instantiated*/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;//是否需要打印Bug,可以在application的onCreated函数里面
    private static final String TAG = "youxuan";

    //info级别
    public static void i(String msg){
        if(isDebug){
            Log.i(TAG,msg);
        }
    }
    //debug级别
    public static void d(String msg){
        if(isDebug){
            Log.d(TAG,msg);
        }
    }

    //error级别
    public static void e(String msg){
        if(isDebug){
            Log.e(TAG,msg);
        }
    }

    public static void v(String msg){
        if(isDebug){
            Log.v(TAG,msg);
        }
    }

    //下面是传入自定义tag的函数
    public static void i(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

}
