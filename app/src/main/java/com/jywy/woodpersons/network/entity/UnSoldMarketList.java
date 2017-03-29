package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 高 on 2017/3/28.
 */
public class UnSoldMarketList {

    @SerializedName("kind")
    private List<UnSoldMarketKind> unSoldMarketKind;
    @SerializedName("stuff")
    private List<UnSoldMarketStuff> unSoldMarketStuff;
    @SerializedName("length")
    private List<UnSoldMarketLength> unSoldMarketLength;
    @SerializedName("port")
    private List<UnSoldMarketPort> unSoldMarketPort;


    public List<UnSoldMarketKind> getUnSoldMarketKind() {
        return unSoldMarketKind;
    }

    public void setUnSoldMarketKind(List<UnSoldMarketKind> unSoldMarketKind) {
        this.unSoldMarketKind = unSoldMarketKind;
    }

    public List<UnSoldMarketStuff> getUnSoldMarketStuff() {
        return unSoldMarketStuff;
    }

    public void setUnSoldMarketStuff(List<UnSoldMarketStuff> unSoldMarketStuff) {
        this.unSoldMarketStuff = unSoldMarketStuff;
    }

    public List<UnSoldMarketLength> getUnSoldMarketLength() {
        return unSoldMarketLength;
    }

    public void setUnSoldMarketLength(List<UnSoldMarketLength> unSoldMarketLength) {
        this.unSoldMarketLength = unSoldMarketLength;
    }

    public List<UnSoldMarketPort> getUnSoldMarketPort() {
        return unSoldMarketPort;
    }

    public void setUnSoldMarketPort(List<UnSoldMarketPort> unSoldMarketPort) {
        this.unSoldMarketPort = unSoldMarketPort;
    }
}
