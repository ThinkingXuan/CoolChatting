package com.xuan.CoolChatting.module.Login;

import com.xuan.CoolChatting.app.IView;
import com.xuan.CoolChatting.bean.User;

/**
 * Created by lenovo on 2016/8/12.
 */
public interface ILoginView extends IView {

    String getUSerName();
    String getPassword();

    /**
     * 获取登录后，用户信息
     * @param user
     */
    void onSuccess(User user);
}
