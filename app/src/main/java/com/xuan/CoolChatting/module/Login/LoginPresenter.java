package com.xuan.CoolChatting.module.Login;

import android.content.Context;
import android.text.TextUtils;

import com.xuan.CoolChatting.Util.BmobNetUtils;
import com.xuan.CoolChatting.Util.ToastUtils;
import com.xuan.CoolChatting.bean.User;
import com.xuan.CoolChatting.config.Constants;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by youxuan on 2016/8/12.
 */

public class LoginPresenter  {

    public ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void toLogin(Context context){
        if(TextUtils.isEmpty(loginView.getUSerName())){
            ToastUtils.showToast(context.getApplicationContext(),"请输入你的邮箱地址");
            return;
        }

        if(TextUtils.isEmpty(loginView.getPassword())){
            ToastUtils.showToast(context.getApplicationContext(),"请输入你的密码");
            return;
        }
        loginView.showLoading();

        BmobNetUtils.login(context, loginView.getUSerName(), loginView.getPassword(), new SaveListener() {
            @Override
            public void onSuccess() {
                loginView.onSuccess();
                loginView.hideLoading();
            }

            @Override
            public void onFailure(int i, String s) {
                loginView.onFailure(i, s);
                loginView.hideLoading();
            }
        });
    }

    public void toLoginByAccount(Context context) {
        if (TextUtils.isEmpty(loginView.getUSerName())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入您的邮箱地址");
            return;
        }
        if (TextUtils.isEmpty(loginView.getPassword())) {
            ToastUtils.showToast(context.getApplicationContext(), "请输入您的密码");
            return;
        }
        loginView.showLoading();
        BmobNetUtils.loginByAccount(context, loginView.getUSerName(), loginView.getPassword(), new LogInListener<User>() {
            @Override
            public void done(User user,BmobException e) {
                if (user != null) {
                    loginView.onSuccess(user);
                } else {
                    loginView.onFailure(Constants.login_code, e.getMessage());
                }
                loginView.hideLoading();
            }
        });
    }
}
