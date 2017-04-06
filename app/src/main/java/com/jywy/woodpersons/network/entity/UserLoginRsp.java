package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

/**
 * Created by 高 on 2017/4/5.
 */

public class UserLoginRsp extends ResponseEntity {


    @SerializedName("data")
    private UserLoginEntity userLoginEntity;

    public UserLoginEntity getUserLoginEntity() {
        return userLoginEntity;
    }

    public void setUserLoginEntity(UserLoginEntity userLoginEntity) {
        this.userLoginEntity = userLoginEntity;
    }
}
