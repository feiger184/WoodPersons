package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

/**
 * Created by 高 on 2017/4/1.
 */

public class UserRegisterCodeRsp extends ResponseEntity {


    @SerializedName("validcode")
    private String validCode;

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
