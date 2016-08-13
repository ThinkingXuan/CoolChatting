package com.xuan.CoolChatting.Util;

import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;

/**
 * Created by youxuan on 2016/8/11.
 * 第三方配置
 */
public class ThirdUtils  {


    private final static String bmobAppkey = "6e04dc661886ad55c6fa74dce46b4ea9";

    //初始化Bmob SDK
    //使用时请将第二个参数Application ID
    //替换成你在Bmob服务器端创建的Application ID

    /**
     * bmob配置
     * @param mContext  主Activity
     */
    public static void bmobInit(Context mContext){
        //初始化
        Bmob.initialize(mContext.getApplicationContext(),bmobAppkey);
        //创建后，就注释这行代码，不然会重复创建表
        //BmobUpdateAgent.initAppVersion(mContext);
    }

    public static void updateInit(Context mContext,boolean onlyWifi){

        BmobUpdateAgent.update(mContext);
        BmobUpdateAgent.setUpdateOnlyWifi(onlyWifi);
        BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {
            @Override
            public void onUpdateReturned(int i, UpdateResponse updateResponse) {

            }
        });
    }

    public static void statisticsInActivityResume(Context mContext) {
        //统计
    }

    public static void statisticsInActivityPause(Context mContext) {
    }

    public static void statisticsInFragmentResume(Class cls) {
    }

    public static void statisticsInFragmentPause(Class cls) {
    }
}
