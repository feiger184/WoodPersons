package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/28.
 */
public class UnSoldMarketLength {

    @SerializedName("lenid")
    private String lenId;
    @SerializedName("lenname")
    private String lenName;

    public String getLenId() {
        return lenId;
    }

    public void setLenId(String lenId) {
        this.lenId = lenId;
    }

    public String getLenName() {
        return lenName;
    }

    public void setLenName(String lenName) {
        this.lenName = lenName;
    }
}
