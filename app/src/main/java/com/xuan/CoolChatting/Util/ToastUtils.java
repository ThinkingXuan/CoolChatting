package com.xuan.CoolChatting.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/19.
 */
public class ToastUtils {

    //包装Toast，并且设置一下属性，让其不同时多次出现
    private static Toast toast;
    public static void showToast(Context context, String msg) {
//        Toast.makeText(context.getApplicationContext(), msg + "", Toast.LENGTH_SHORT).show();
        if(toast == null){
            toast = Toast.makeText(context.getApplicationContext(),msg+"",Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }

        toast.show();
    }
}
