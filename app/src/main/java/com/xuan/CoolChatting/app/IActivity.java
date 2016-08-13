package com.xuan.CoolChatting.app;

import android.view.KeyEvent;

/**
 * Created by lenovo on 2016/8/11.
 */

public interface IActivity  {

    void initView();
    void initData();
    void PreOnStart();
    void PreOnResume();
    void PreOnRestart();
    void PreOnPause();
    void PreOnStop();
    void PreOnDestroy();
    boolean PreOnKeyDown(int keyCode, KeyEvent event);
}
