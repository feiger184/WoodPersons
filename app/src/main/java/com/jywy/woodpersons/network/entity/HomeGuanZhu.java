package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/31.
 */
public class HomeGuanZhu {

    /**
     * fans : 16609
     * viewnum : 110
     * wagons : 0
     */

    @SerializedName("fans")
    private int fansStar;
    @SerializedName("viewnum")
    private int viewNum;
    @SerializedName("wagons")
    private int wagonsNum;

    public int getFansStar() {
        return fansStar;
    }

    public void setFansStar(int fansStar) {
        this.fansStar = fansStar;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public int getWagonsNum() {
        return wagonsNum;
    }

    public void setWagonsNum(int wagonsNum) {
        this.wagonsNum = wagonsNum;
    }
}
