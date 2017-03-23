package com.jywy.woodpersons;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.jywy.woodpersons.commons.LogUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

import static okhttp3.internal.Internal.instance;

/**
 * Created by 高 on 2017/3/20.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //极光推送调试模式
        JPushInterface.setDebugMode(true);
        //极光推送初始化
        JPushInterface.init(this);
    }


    public static String getAppPackageName(Context context) {

        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return null;

    }

}
