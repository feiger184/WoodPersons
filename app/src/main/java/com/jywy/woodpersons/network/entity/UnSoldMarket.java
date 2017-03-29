package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/27.
 */

public class UnSoldMarket {

    /**
     * kindid : 2
     * carnum :
     * productid : 321440
     * cdkey : 1393454493117032411363513753214392
     * pic :
     * kindname : 条子
     * goodtype : 2
     * lenname : 2米
     * stuffname : 樟子松
     * guige : 35*15
     * portname : 满洲里
     * contact : 白玉
     * contactphone : 13934544931
     * viewnum : 0
     * updatetime : 2017-03-24 11:36:35
     * salestatusname : 正常
     * markettime : 03月24日
     * dumpposition :
     * multiguige :
     * sendusername : 白玉
     * sendphone : 13934544931
     * hostphone : 13934544931
     */

    @SerializedName("kindid")
    private String kindId;
    @SerializedName("carnum")
    private String carNum;
    @SerializedName("productid")
    private String productId;
    @SerializedName("cdkey")
    private String cdKey;
    private String pic;
    @SerializedName("kindname")
    private String kindName;
    @SerializedName("goodtype")
    private String goodType;
    @SerializedName("lenname")
    private String lenName;
    @SerializedName("stuffname")
    private String stuffName;
    @SerializedName("guige")
    private String guiGe;
    @SerializedName("portname")
    private String portName;
    private String contact;
    @SerializedName("contactphone")
    private String contactPhone;
    @SerializedName("viewnum")
    private String viewNum;
    @SerializedName("updatetime")
    private String updateTime;
    @SerializedName("salestatusname")
    private String saleStatusName;
    @SerializedName("markettime")
    private String marketTime;
    @SerializedName("dumpposition")
    private String dumpPosition;
    @SerializedName("multiguige")
    private String multiGuiGe;
    @SerializedName("sendusername")
    private String senduserName;
    @SerializedName("sendphone")
    private String sendPhone;
    @SerializedName("hostphone")
    private String hostPhone;

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCdKey() {
        return cdKey;
    }

    public void setCdKey(String cdKey) {
        this.cdKey = cdKey;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getLenName() {
        return lenName;
    }

    public void setLenName(String lenName) {
        this.lenName = lenName;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }

    public String getGuiGe() {
        return guiGe;
    }

    public void setGuiGe(String guiGe) {
        this.guiGe = guiGe;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSaleStatusName() {
        return saleStatusName;
    }

    public void setSaleStatusName(String saleStatusName) {
        this.saleStatusName = saleStatusName;
    }

    public String getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(String marketTime) {
        this.marketTime = marketTime;
    }

    public String getDumpPosition() {
        return dumpPosition;
    }

    public void setDumpPosition(String dumpPosition) {
        this.dumpPosition = dumpPosition;
    }

    public String getMultiGuiGe() {
        return multiGuiGe;
    }

    public void setMultiGuiGe(String multiGuiGe) {
        this.multiGuiGe = multiGuiGe;
    }

    public String getSenduserName() {
        return senduserName;
    }

    public void setSenduserName(String senduserName) {
        this.senduserName = senduserName;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getHostPhone() {
        return hostPhone;
    }

    public void setHostPhone(String hostPhone) {
        this.hostPhone = hostPhone;
    }
}
