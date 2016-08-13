package com.xuan.CoolChatting.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by lenovo on 2016/8/11.
 */
public class NewsLifecycleHandler implements Application.ActivityLifecycleCallbacks {

    /**
     * Application通过ActivityLifecycleCallbacks使用接口提供的一套回调方法
     * 用于让开发者对Activity的生命周期进行集中处理。
     * ActivityLifecycleCallbacks接口回调可以简化检测Activity的生命周期时间，
     * 在一个类中进行统一处理
     */

    private final static String TAG = NewsLifecycleHandler.class.getSimpleName();

    private static int resumed;
    private static int paused;
    private static int started;
    private static int stoped;

    public NewsLifecycleHandler() {
        resetVariables();
    }

    public void resetVariables(){
        resumed = 0;
        paused = 0;
        started = 0;
        stoped = 0;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        android.util.Log.e(activity.getClass().getSimpleName()+"--"+TAG,"application is in foreground:"+(resumed>paused));
    }

    @Override
    public void onActivityStopped(Activity activity) {

        ++stoped;
        android.util.Log.e(activity.getClass().getSimpleName() + "--" + TAG, "application is visible: "
                + (started > stoped));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    // If you want a static function you can use to check if your application is
    // foreground/background, you can use the following:


    public static boolean isApplicationVisible() {
        return started > stoped;
    }

    public static boolean isApplicationInForeground() {
        return resumed > paused;
    }

    public static boolean isApplicationInBackground() {
        return started == stoped;
    }
}
