package com.jiangdg.keepappalive.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/** 静态监听锁屏、解锁、开屏广播
 *  a) 当用户锁屏时，将SportsActivity置于前台，同时开启1像素悬浮窗；
 *  b) 当用户解锁时，关闭1像素悬浮窗；
 *
 * Created by jianddongguo on 2017/7/8.
 * http://blog.csdn.net/andrexpert
 */
public class ScreenReceiverUtil {
    private Context mContext;
    // 锁屏广播接收器
    private SreenBroadcastReceiver mScreenReceiver;
    // 屏幕状态改变回调接口
    private SreenStateListener mStateReceiverListener;

    public ScreenReceiverUtil(Context mContext){
        this.mContext = mContext;
    }

    public void setScreenReceiverListener(SreenStateListener mStateReceiverListener){
        this.mStateReceiverListener = mStateReceiverListener;
        // 动态启动广播接收器
        this.mScreenReceiver = new SreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mScreenReceiver,filter);
    }

    public void stopScreenReceiverListener(){
        mContext.unregisterReceiver(mScreenReceiver);
    }

    public  class SreenBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("KeepAppAlive","SreenLockReceiver-->监听到系统广播："+action);
            if(mStateReceiverListener == null){
                return;
            }
            if(Intent.ACTION_SCREEN_ON.equals(action)){         // 开屏
                mStateReceiverListener.onSreenOn();
            }else if(Intent.ACTION_SCREEN_OFF.equals(action)){  // 锁屏
                mStateReceiverListener.onSreenOff();
            }else if(Intent.ACTION_USER_PRESENT.equals(action)){ // 解锁
                mStateReceiverListener.onUserPresent();
            }
        }
    }

    // 监听sreen状态对外回调接口
    public interface SreenStateListener {
        void onSreenOn();
        void onSreenOff();
        void onUserPresent();
    }
}
