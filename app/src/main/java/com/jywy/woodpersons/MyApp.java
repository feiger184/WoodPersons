package com.jywy.woodpersons;

import android.app.Activity;
import android.app.Application;

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


}
