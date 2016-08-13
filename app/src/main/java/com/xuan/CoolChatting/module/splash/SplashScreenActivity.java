package com.xuan.CoolChatting.module.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.xuan.CoolChatting.app.BaseActivity;
import com.xuan.CoolChatting.bean.User;
import com.xuan.CoolChatting.module.Invite.InviteActivity;
import com.xuan.CoolChatting.module.Login.LoginActivity;
import com.xuan.CoolChatting.module.Welcome.WelcomeActivity;

import java.lang.ref.WeakReference;

import app.coolchatting.lenovo.coolchattingle.R;
import cn.bmob.v3.BmobUser;
import mo.netstatus.NetUtils;

/**
 * Created by lenovo on 2016/8/12.
 */
public class SplashScreenActivity extends BaseActivity{
    private StaticHandler handler;

    private static class StaticHandler extends Handler{
        private final WeakReference<Activity> weakReference;

        public StaticHandler(Activity activity) {
           this.weakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    private static class SplachHandler implements Runnable{

        private final WeakReference<Activity> weakReference;

        public SplachHandler(Activity activity) {
            this.weakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void run() {
            Activity activity = weakReference.get();
            if(activity !=null){
                User user = BmobUser.getCurrentUser(User.class);

                if(user !=null){
                    if(user.getIsok() && user.getHavaLove()){
                        activity.startActivity(new Intent(activity,LoginActivity.class));
                    } else if (user.getIsok() && !user.getHavaLove()){
                        activity.startActivity(new Intent(activity,InviteActivity.class));
                    } else {
                        activity.startActivity(new Intent(activity,WelcomeActivity.class));
                    }
                }else {
                    //缓存用户对象为空时，可打开用户注册页面
                    activity.startActivity(new Intent(activity,LoginActivity.class));
                }
                activity.finish();
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }

    @Override
    public boolean isOverridePendingTransition() {
        return true;
    }

    @Override
    protected PendingTranstitionMode getPendingTransitionMode() {
        return PendingTranstitionMode.Top;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type0) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        handler = new StaticHandler(this);
        handler.postDelayed(new SplachHandler(this),3000);
    }

    @Override
    public void PreOnStart() {

    }

    @Override
    public void PreOnResume() {

    }

    @Override
    public void PreOnRestart() {

    }

    @Override
    public void PreOnPause() {

    }

    @Override
    public void PreOnStop() {

    }

    @Override
    public void PreOnDestroy() {

    }

    @Override
    public boolean PreOnKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return false;
    }
}
