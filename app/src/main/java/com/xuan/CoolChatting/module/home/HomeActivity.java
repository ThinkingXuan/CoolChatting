package com.xuan.CoolChatting.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.xuan.CoolChatting.app.BaseActivity;

import mo.netstatus.NetUtils;

/**
 * Created by lenovo on 2016/8/11.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean isOverridePendingTransition() {
        return false;
    }

    @Override
    protected PendingTranstitionMode getPendingTransitionMode() {
        return null;
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
        return false;
    }
}
