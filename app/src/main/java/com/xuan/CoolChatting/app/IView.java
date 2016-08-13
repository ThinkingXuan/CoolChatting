package com.xuan.CoolChatting.app;

/**
 * Created by lenovo on 2016/8/12.
 */
public interface IView {

    void showLoading();
    void hideLoading();
    void onSuccess();
    void onFailure(int code,String msg);

}
