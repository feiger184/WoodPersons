package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/4/1.
 */
public class UserBean {


    @SerializedName("userid")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
