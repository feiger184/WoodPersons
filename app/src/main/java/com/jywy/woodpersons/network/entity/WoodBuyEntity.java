package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/30.
 */
public class WoodBuyEntity {

    /**
     * kindid : 3
     * timberid : 0
     * timbername :
     * portname : 满洲里
     * buyid : 9317
     * stuffname : 樟子松
     * kindname : 口料
     * lenname : 3米
     * viewnum : 5
     * buystatus : 求购
     * refreshtime : 02-08
     * diameterlen :
     * wide : 63~70
     * thinckness : 30~33
     * contact :
     * contactphone : 15630086386
     */

    @SerializedName("kindid")
    private String kindId;
    @SerializedName("timberid")
    private String timberId;
    @SerializedName("timbername")
    private String timberName;
    @SerializedName("portname")
    private String portName;
    @SerializedName("buyid")
    private String buyId;
    @SerializedName("stuffname")
    private String stuffName;
    @SerializedName("kindname")
    private String kindName;
    @SerializedName("lenname")
    private String lenName;
    @SerializedName("viewnum")
    private String viewNum;
    @SerializedName("buystatus")
    private String buyStatus;
    @SerializedName("refreshtime")
    private String refreshTime;
    @SerializedName("diameterlen")
    private String diameterLen;
    @SerializedName("wide")
    private String wideWidth;
    @SerializedName("thinckness")
    private String thinckNess;
    private String contact;
    @SerializedName("contactphone")
    private String contactPhone;

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getTimberId() {
        return timberId;
    }

    public void setTimberId(String timberId) {
        this.timberId = timberId;
    }

    public String getTimberName() {
        return timberName;
    }

    public void setTimberName(String timberName) {
        this.timberName = timberName;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getLenName() {
        return lenName;
    }

    public void setLenName(String lenName) {
        this.lenName = lenName;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        this.buyStatus = buyStatus;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getDiameterLen() {
        return diameterLen;
    }

    public void setDiameterLen(String diameterLen) {
        this.diameterLen = diameterLen;
    }

    public String getWideWidth() {
        return wideWidth;
    }

    public void setWideWidth(String wideWidth) {
        this.wideWidth = wideWidth;
    }

    public String getThinckNess() {
        return thinckNess;
    }

    public void setThinckNess(String thinckNess) {
        this.thinckNess = thinckNess;
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
}
