package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/31.
 */
public class RailwayGoodsPlace {


    /**
     * positionname : 后贝加尔
     * displaysign : 1
     * spotpositionid : 51
     * updatesign : 0
     * log : 原木36
     * board : 板材252
     * capacity : 到货288车
     */

    @SerializedName("positionname")
    private String positionName;
    @SerializedName("displaysign")
    private String displaySign;
    @SerializedName("spotpositionid")
    private String spotPositionId;
    @SerializedName("updatesign")
    private String updateSign;
    @SerializedName("log")
    private String logSize;
    @SerializedName("board")
    private String boardSize;
    @SerializedName("capacity")
    private String capaCity;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDisplaySign() {
        return displaySign;
    }

    public void setDisplaySign(String displaySign) {
        this.displaySign = displaySign;
    }

    public String getSpotPositionId() {
        return spotPositionId;
    }

    public void setSpotPositionId(String spotPositionId) {
        this.spotPositionId = spotPositionId;
    }

    public String getUpdateSign() {
        return updateSign;
    }

    public void setUpdateSign(String updateSign) {
        this.updateSign = updateSign;
    }

    public String getLogSize() {
        return logSize;
    }

    public void setLogSize(String logSize) {
        this.logSize = logSize;
    }

    public String getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(String boardSize) {
        this.boardSize = boardSize;
    }

    public String getCapaCity() {
        return capaCity;
    }

    public void setCapaCity(String capaCity) {
        this.capaCity = capaCity;
    }
}
