package com.jywy.woodpersons.network;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户仓库,使用前必须先对其进行初始化 init()
 */

public class UserPrefs {

    private final SharedPreferences preferences;

    private static final String PREFS_NAME = "user_info";
    private static UserPrefs userPrefs;

    private static final String KEY_USERID = "key_userid";

    private UserPrefs(Context context) {
        preferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);

    }

    public static void init(Context context){
        userPrefs = new UserPrefs(context);
    }

    public static UserPrefs getInstance() {
        return userPrefs;
    }

    public void setUserid(int userid) {
        preferences.edit().putInt(KEY_USERID, userid).commit();
    }

    public int getUserid() {
        return preferences.getInt(KEY_USERID, -1);
    }

    public void clearUser(){
        preferences.edit().clear().commit();
    }



}
