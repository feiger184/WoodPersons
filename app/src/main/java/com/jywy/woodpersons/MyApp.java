package com.jywy.woodpersons;

import android.app.Activity;
import android.app.Application;

import com.jywy.woodpersons.commons.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高 on 2017/3/20.
 */

public class MyApp extends Application {

    private static MyApp instance;
    //存放Activity的List集合
    private List activityList = new ArrayList<>();

    //单例模式获得Application对象
    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }

    /**
     * add the activity in to a list end
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            if (activity != null && activityList != null) {
                int size = activityList.size();
                if (checkActivityIsVasivle(activity)) {
                    removeActivity(activity);
                    activityList.add(activityList.size(), activity);
                } else {
                    activityList.add(activity);
                }
                size = activityList.size();
                for (int i = 0; i < size; i++) {
                    LogUtils.i("addActivity ==[" + i + "]" + " " + activityList.get(i));
                }
            }
        } catch (Exception e) {
            LogUtils.e("addActivity" + e.getMessage());
        }

    }


    /**
     * remove the finished activity in the list.
     * @param activity
     * the activity is removed from activityList
     */
    public void removeActivity(Activity activity) {
        try {
            if (activityList != null) {
                activityList.remove(activity);
                LogUtils.i("removeActivity==" + " " + activity + "activityList.size===" + activityList.size());
            }
        } catch (Exception e) {
            LogUtils.e("removeActivity" + e.getMessage());
        }
    }
    /**
     * 判定某个Activity的状态,
     * */
    public boolean checkActivityIsVasivle(Activity activity) {
        LogUtils.i(" " + activityList.contains(activity));
        return activityList.contains(activity);
    }

    /**
     * finish all the activity in the list.
     *干掉所有的Activity用于程序退出
     * the activity calling this method hold the context
     */
    public void finishAllActivity() {
        if (activityList != null) {
            int size = activityList.size();
            for (int i = size - 1; i >= 0; i--) {
                Activity activity = (Activity) activityList.get(i);
                if (activity != null) {
                    activity.finish();
                }
                LogUtils.i("finishAllActivity ==[" + i + "]" + " " + activity);
                activityList.remove(activity);
            }
        }
    }

}
