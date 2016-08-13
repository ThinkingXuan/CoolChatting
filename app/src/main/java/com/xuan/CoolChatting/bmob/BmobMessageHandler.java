package com.xuan.CoolChatting.bmob;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.xuan.CoolChatting.Util.VolleyLog;
import com.xuan.CoolChatting.module.home.HomeActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import app.coolchatting.lenovo.coolchattingle.R;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * Created by lenovo on 2016/8/11.
 */
public class BmobMessageHandler extends BmobIMMessageHandler{

    private Context mContext;

    public BmobMessageHandler(Context context) {
        mContext = context;
    }

    @Override
    public void onMessageReceive(final MessageEvent messageEvent) {

        //当接收到服务器发来的消息时，此方法被调用
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);

        //指定跳转的页面
        Intent notificationIntent = new Intent(mContext,HomeActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(mContext,Notification.FLAG_SHOW_LIGHTS,notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent)
                .setDefaults(Notification.DEFAULT_ALL) //设置默认震动声音
                .setSmallIcon(R.mipmap.ic_launcher) //设置状态里面的图标(小图标)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.ic_launcher))//下拉列表里面的图标(大图标)
               // .setTicker("您有新的消息") //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis()) //设置时间 发生的时间
                .setAutoCancel(true) //设置可以清除
                .setContentTitle("您有新的消息") //设置下拉列表的标题
                .setContentText(messageEvent.getMessage().getContent()+"");//设置上下文内容

        Notification notification = builder.build();
        notificationManager.notify(1,notification);

        //利用EventBus传信息
        EventBus.getDefault().post(messageEvent);

    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        //每次调用connect 方法时会查询一次离线消息，如果有此方法会被调用

        Map<String,List<MessageEvent>> map = offlineMessageEvent.getEventMap();
        for (Map.Entry<String,List<MessageEvent>> entry :map.entrySet()){
            List<MessageEvent> list = entry.getValue();
            for (MessageEvent messageEvent :list){
                VolleyLog.e("%s",messageEvent.getMessage().getContent());
            }
        }







    }
}
