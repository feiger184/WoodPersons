package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

/**
 * Created by 高 on 2017/4/1.
 */

public class UserRegisterSuccessRsp extends ResponseEntity {

    @SerializedName("data")
    private UserBean userBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
