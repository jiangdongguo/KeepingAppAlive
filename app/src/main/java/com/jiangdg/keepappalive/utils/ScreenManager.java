package com.jiangdg.keepappalive.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jiangdg.keepappalive.SinglePixelActivity;

import java.lang.ref.WeakReference;

/**1像素管理类
 *
 * Created by jianddongguo on 2017/7/8.
 * http://blog.csdn.net/andrexpert
 */

public class ScreenManager {
    private static final String TAG = "ScreenManager";
    private Context mContext;
    private static ScreenManager mSreenManager;
    // 使用弱引用，防止内存泄漏
    private WeakReference<Activity> mActivityRef;

    private ScreenManager(Context mContext){
        this.mContext = mContext;
    }

    // 单例模式
    public static ScreenManager getScreenManagerInstance(Context context){
        if(mSreenManager == null){
            mSreenManager = new ScreenManager(context);
        }
        return mSreenManager;
    }

    // 获得SinglePixelActivity的引用
    public void setSingleActivity(Activity mActivity){
        mActivityRef = new WeakReference<>(mActivity);
    }

    // 启动SinglePixelActivity
    public void startActivity(){
        if(Contants.DEBUG)
            Log.d(TAG,"准备启动SinglePixelActivity...");
        Intent intent = new Intent(mContext,SinglePixelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    // 结束SinglePixelActivity
    public void finishActivity(){
        if(Contants.DEBUG)
            Log.d(TAG,"准备结束SinglePixelActivity...");
        if(mActivityRef != null){
            Activity mActivity = mActivityRef.get();
            if(mActivity != null){
                mActivity.finish();
            }
        }
    }
}
