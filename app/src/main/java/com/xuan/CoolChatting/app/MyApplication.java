package com.xuan.CoolChatting.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.xuan.CoolChatting.Util.ThirdUtils;
import com.xuan.CoolChatting.bmob.BmobMessageHandler;

import java.util.List;
import java.util.Stack;

import cn.bmob.newim.BmobIM;


/**
 * Created by youxuan on 2016/8/11.
 * 自定义 Application
 */
public class MyApplication extends Application {

    //用于获取Application对象
    private static MyApplication instance = null;
    //用于管理Activity
    private static Stack<Activity> activityStack;
    //获取ActivityLifecycelCallbacks,用于管理Activity的生命周期
    public NewsLifecycleHandler mNewLifecyclehandler;

    @Override
    public void onCreate() {
        super.onCreate();

        if(shouldInit()){
            instance = this;
            mNewLifecyclehandler = new NewsLifecycleHandler();
            //registerActivityLifecycleCallbacks(mNewLifecyclehandler);
            //bmob
            ThirdUtils.bmobInit(this);
            //newIM初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new BmobMessageHandler(this));
            //检测代码问题
            LeakCanary.install(this);
        }
    }

    private boolean shouldInit(){
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos =
                am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info:processInfos) {
            if(info.pid == myPid && mainProcessName.equals(info.processName)){
                return true;
            }
        }
        return false;
    }

    public NewsLifecycleHandler getmNewsLifecycleHandler() {
        return mNewLifecyclehandler;
    }

    /**单例模式*/
    public static MyApplication getInstance(){
        if(instance == null){
            instance = new MyApplication();
        }
        return instance;
    }

    /**
     * add Activity添加Activity到栈
     */

    public void addActivity(Activity activity){
        if(activity == null){
            activityStack = new Stack<Activity>();
        }

        if(activity !=null){
            activityStack.add(activity);
        }
    }

    /**
     * get Current Activity 获取当前的Activtiy (栈中最后一个)
     */


    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();

        return activity;
    }

    /**
     * finish 结束当前Activity(栈中最后一个压入的)
     */
    public void finishActivty(){
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    //结束指定的Activtiy
    public void finishActivity(Activity activity){
        if(activity !=null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 结束指定类名的Activity
     */

    public void finishActiviy(Class<?> cls){
        for (Activity activtiy : activityStack) {
            if(activtiy.getClass().equals(cls)){
                finishActivity(activtiy);
            }
        }
    }
    /**
     * 结束所有的Activity
     */
    public void finishAllActivity(){
        for(int i = 0,size = activityStack.size();i<size;i++){
            if(null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */

    public void AppExit(){
        try {
            finishAllActivity();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
