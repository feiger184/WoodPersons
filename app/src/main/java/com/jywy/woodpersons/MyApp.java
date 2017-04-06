package com.jywy.woodpersons;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.jywy.woodpersons.network.UserPrefs;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import cn.jpush.android.api.JPushInterface;


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

        //用户本地仓库
        UserPrefs.init(getApplicationContext());

        //初始化ImageLoader(加载选项相关设置)
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)/*开启硬盘缓存*/
                .cacheInMemory(true)/*开启内存缓存*/
                .resetViewBeforeLoading(true)/*加载前重置ImageView*/
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(4 * 1024 * 1024)//设置内存缓存的大小（4M）
                .defaultDisplayImageOptions(displayImageOptions)//设置默认的加载选项
                .build();

        ImageLoader.getInstance().init(configuration);
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
