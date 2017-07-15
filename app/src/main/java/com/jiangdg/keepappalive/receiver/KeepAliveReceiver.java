package com.jiangdg.keepappalive.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.jiangdg.keepappalive.SportsActivity;
import com.jiangdg.keepappalive.utils.Contants;
import com.jiangdg.keepappalive.utils.SystemUtils;

/** 监听系统广播，复活进程
 *  (1) 网络变化广播
 *  (2) 屏幕解锁广播
 *  (3) 应用安装卸载广播
 *  (4) 开机广播
 *
 * Created by jianddongguo on 2017/7/10.
 * http://blog.csdn.net/andrexpert
 */

public class KeepAliveReceiver extends BroadcastReceiver {
    private static final String TAG = "AliveBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
//        String action = intent.getAction();
//        if(Contants.DEBUG)
//            Log.d(TAG,"AliveBroadcastReceiver---->接收到的系统广播："+action);
//        getNetworkBroadcast(context,intent);
//        if(SystemUtils.isAppAlive(context,Contants.PACKAGE_NAME)){
//            Log.i(TAG,"AliveBroadcastReceiver---->APP还是活着的");
//            return;
//        }
//        Intent intentAlive = new Intent(context, SportsActivity.class);
//        intentAlive.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intentAlive);
//        Log.i(TAG,"AliveBroadcastReceiver---->复活进程(APP)");
    }

    private void getNetworkBroadcast(Context context, Intent intent){
        String action = intent.getAction();
        // wifi状态改变
        if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)){
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,0);
            switch (wifiState){
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(context,"wifi关闭",Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    Toast.makeText(context,"wifi开启",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
        // 连接到一个有效wifi路由器
        if(WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)){
            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if(null != parcelableExtra){
                NetworkInfo networkInfo = (NetworkInfo)parcelableExtra;
                NetworkInfo.State state = networkInfo.getState();
                boolean isConnected = state == NetworkInfo.State.CONNECTED;
                if(isConnected){
                    Toast.makeText(context,"设备连接到一个有效WIFI路由器",Toast.LENGTH_SHORT).show();
                }
            }
        }
        // 监听网络连接状态，包括wifi和移动网络数据的打开和关闭
        // 由于上面已经对wifi进行处理，这里只对移动网络进行监听(该方式检测有点慢)
        // 其中，移动网络--->ConnectivityManager.TYPE_MOBILE；
        //       Wifi--->ConnectivityManager.TYPE_WIFI
        //       不明确类型：ConnectivityManager.EXTRA_NETWORK_INFO
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(action)){
            ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(gprs.isConnected()){
                Toast.makeText(context,"移动网络打开",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"移动网络关闭",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
