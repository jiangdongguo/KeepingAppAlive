package com.jiangdg.keepappalive.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.jiangdg.keepappalive.R;

/** 移除前台Service通知栏标志，这个Service选择性使用
 *
 * Created by jianddongguo on 2017/7/7.
 * http://blog.csdn.net/andrexpert
 */

public class CancelNoticeService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(DaemonService.NOTICE_ID,builder.build());
            // 开启一条线程，去移除DaemonService弹出的通知
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 延迟1s
                    SystemClock.sleep(1000);
                    // 取消CancelNoticeService的前台
                    stopForeground(true);
                    // 移除DaemonService弹出的通知
                    NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    manager.cancel(DaemonService.NOTICE_ID);
                    // 任务完成，终止自己
                    stopSelf();
                }
            }).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
