package com.jywy.woodpersons.network.entity;

/**
 * Created by 高 on 2017/3/21.
 */

public class RailwayGoodsListReq {

    public int getTrainSign() {
        return trainSign;
    }

    public void setTrainSign(int trainSign) {
        this.trainSign = trainSign;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int trainSign;

    private int portId;

    private String train;


   //日期  2017-02-21
    private String trainDate;

    //登录用户id
    private int userId;


}
