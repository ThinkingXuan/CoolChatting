package com.xuan.CoolChatting.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.xuan.CoolChatting.Util.ThirdUtils;
import com.xuan.CoolChatting.app.spotsDialog.SpotsDialog;
import com.xuan.CoolChatting.widget.dialog.widget.MaterialDialog;

import app.coolchatting.lenovo.coolchatting.R;
import mo.netstatus.NetChangeObserver;
import mo.netstatus.NetStateReceiver;
import mo.netstatus.NetUtils;

/**
 * Created by lenovo on 2016/8/11.
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivity {


    protected MaterialDialog materialDialog;
    protected AlertDialog alertDialog;

    /**
     * network status
     */
    protected NetChangeObserver mNetChangeObserver = null;

    public enum PendingTranstitionMode {
        Right, Top;
    }

    @Override
    protected void onStart() {
        MyApplication.getInstance().getmNewsLifecycleHandler().onActivityStarted(this);
        PreOnStart();
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        materialDialog = new MaterialDialog(this);
        alertDialog = new SpotsDialog(this, R.style.Custom);
        MyApplication.getInstance().addActivity(this);

        mNetChangeObserver = new NetChangeObserver(){
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                super.onNetConnected(type);
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                super.onNetDisConnect();
                onNetworkDisConnected();
            }
        };

        NetStateReceiver.registerObserver(mNetChangeObserver);

        super.onCreate(savedInstanceState);
    }

    /**
     * 是否显示返回键
     * @param isShow
     */
    public void showActionBarBack(boolean isShow){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(getResources().getColor(R.color.system_press));
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        ThirdUtils.statisticsInActivityResume(this);
        PreOnResume();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        PreOnRestart();
        super.onRestart();

    }

    @Override
    protected void onPause() {
        MyApplication.getInstance().getmNewsLifecycleHandler().onActivityStopped(this);
        ThirdUtils.statisticsInActivityPause(this);
        if(alertDialog != null && alertDialog.isShowing()){
            alertDialog.hide();
        }
        if(materialDialog !=null && materialDialog.isShowing()){
            materialDialog.hide();
        }
        PreOnPause();
        super.onPause();
    }

    @Override
    protected void onStop() {

        MyApplication.getInstance().getmNewsLifecycleHandler().onActivityStopped(this);
        PreOnStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        alertDialog.dismiss();
        materialDialog.dismiss();
        alertDialog = null;
        materialDialog = null;
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        MyApplication.getInstance().finishActivity(this);
        PreOnDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(PreOnKeyDown(keyCode,event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if(isOverridePendingTransition() && getPendingTransitionMode()!=null){

            switch (getPendingTransitionMode()){
                case Right:
                    overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                    break;
                case Top:
                    overridePendingTransition(R.anim.in_from_buttom,R.anim.out_from_top);
                    break;
            }
        }
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (isOverridePendingTransition() && getPendingTransitionMode() != null) {
            switch (getPendingTransitionMode()) {
                case Right:
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                    //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;
                case Top:
                    //overridePendingTransition(R.anim.in_from_buttom, R.anim.out_from_top);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    break;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (isOverridePendingTransition() && getPendingTransitionMode() != null) {
            switch (getPendingTransitionMode()) {
                case Right:
                    overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                    //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;
                case Top:
                    //overridePendingTransition(R.anim.in_from_buttom, R.anim.out_from_top);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    break;
            }
        }
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public abstract boolean isOverridePendingTransition();
    protected abstract PendingTranstitionMode getPendingTransitionMode();

    /**
     * network Connected
     */

    protected  abstract void onNetworkConnected(NetUtils.NetType type0);

    /**
     * network disconnected
     */
    protected  abstract void onNetworkDisConnected();

    @Override
    public void onBackPressed() {

        finish();
    }
}
