package com.xuan.CoolChatting.bean;

import android.content.Context;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by youxuan on 2016/8/12.
 * 用来保存一些用户信息
 */

public class User extends BmobUser {

    private Integer sex; //性别(0男,1女)
    private String nick; //别名
    private Boolean isok; //是否完善个人信息
    private Boolean havaLove; //是否含有另一半
    private String mood;  //心情
    private String loveDateObjectId; //设置love

    public User() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Boolean getIsok() {
        return isok;
    }

    public void setIsok(Boolean isok) {
        this.isok = isok;
    }

    public Boolean getHavaLove() {
        return havaLove;
    }

    public void setHavaLove(Boolean havaLove) {
        this.havaLove = havaLove;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getLoveDateObjectId() {
        return loveDateObjectId;
    }

    public void setLoveDateObjectId(String loveDateObjectId) {
        this.loveDateObjectId = loveDateObjectId;
    }
}
