package com.jiangdg.keepappalive.receiver;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.android.pushagent.api.PushEventReceiver;
import com.jiangdg.keepappalive.utils.Contants;

import java.io.UnsupportedEncodingException;

/** 华为接收器子类，用于接收推送消息
 * 华为4x IMEI：865743021309132
 *
 * Created by jianddongguo on 2017/7/10.
 * http://blog.csdn.net/andrexpert
 *
 */

public class MyHwPushReceiver extends PushEventReceiver{
    private final static String TAG = "MyHwPushReceiver";

    /**
     * pushToken申请成功后，会自动回调该方法
     * 应用通过该方法获取token（必须实现）
     * */
    @Override
    public void onToken(Context context, String token, Bundle bundle) {
        Log.i(TAG,"连接到华为推送服务器，token="+token);
    }

    /**
     *  推送消息下来时会自动回调onPushMsg方法
     *  用于接收透传消息（必须实现）
     * */
    @Override
    public boolean onPushMsg(Context context, byte[] msgBytes, Bundle bundle) {
        if(Contants.DEBUG){
            try {
                Log.i(TAG,"接收透传消息："+new String(msgBytes,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // 启动应用

        return false;
    }

    /**
     * 连接状态的回调方法
     * */
    @Override
    public void onPushState(Context context, boolean connectState) {
        Log.i(TAG,"是否连接到华为推送服务器："+(connectState?"connected":"disconnected"));
    }

    /**
     * 该方法会在设置标签、LBS信息之后，点击打开通知栏消息
     * 点击通知栏上的按钮之后被调用，由业务决定是否调用该函数。
     * Event类型：
     * NOTIFICATION_OPEMED，通知栏中的通知被点击打开
     * NOTIFICATION_CLICK_BTN,通知栏中通知上的按钮被点击
     * PLUGINRSP，标签上报回应
     * */
    @Override
    public void onEvent(Context context, Event event, Bundle bundle) {
        super.onEvent(context, event, bundle);
    }
}
