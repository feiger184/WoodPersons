package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/4/5.
 */
public class UserLoginEntity {


    /**
     * username : null
     * nickname : null
     * userpic : null
     * loginnum : 2
     * userid : 8569
     * logintime : 2017-04-05 09:32:29
     */

    @SerializedName("username")
    private Object userName;
    @SerializedName("nickname")
    private Object nickName;
    @SerializedName("userpic")
    private Object userPic;
    @SerializedName("loginnum")
    private int loginNum;
    @SerializedName("userid")
    private String userId;
    @SerializedName("logintime")
    private String loginTime;

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getNickName() {
        return nickName;
    }

    public void setNickName(Object nickName) {
        this.nickName = nickName;
    }

    public Object getUserPic() {
        return userPic;
    }

    public void setUserPic(Object userPic) {
        this.userPic = userPic;
    }

    public int getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(int loginNum) {
        this.loginNum = loginNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
