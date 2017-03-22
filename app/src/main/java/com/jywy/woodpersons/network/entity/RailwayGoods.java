package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/21.
 */
public class RailwayGoods {

    /**
     * train_display : 第03列
     * train : 03
     * traindate : 2017-02-21
     * arrivetime : 02-21
     * log : 原木20
     * board : 板材45
     * capacity : 到货65车
     */

    @SerializedName("train_display")
    private String trainDisplay;
    @SerializedName("train")
    private String trainNum;
    @SerializedName("traindate")
    private String trainDate;
    @SerializedName("arrivetime")
    private String arriveTime;
    @SerializedName("log")
    private String logWood;
    @SerializedName("board")
    private String boardWood;
    @SerializedName("capacity")
    private String goodsCount;

    public String getTrainDisplay() {
        return trainDisplay;
    }

    public void setTrainDisplay(String trainDisplay) {
        this.trainDisplay = trainDisplay;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getLogWood() {
        return logWood;
    }

    public void setLogWood(String logWood) {
        this.logWood = logWood;
    }

    public String getBoardWood() {
        return boardWood;
    }

    public void setBoardWood(String boardWood) {
        this.boardWood = boardWood;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }
}
