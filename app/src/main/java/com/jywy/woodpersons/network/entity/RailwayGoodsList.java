package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/21.
 */
public class RailwayGoodsList {

    /**
     * carnum : 6559
     * carnum_full : 54496559
     * phone_full : 18748370111
     * productid : 321264
     * cdkey : 1504813544417022111045956933212631
     * pic :
     * kindname : 板
     * goodtype : 1
     * lenname : 4米
     * stuffname : 落叶松
     * guige :
     * portname : 满洲里
     * contact : null
     * markettime : 02月21日
     * dumpposition : 满市西货场
     * multiguige :
     * posupdatesign : 0
     * contactphone : 0111
     * refreshtime : 02-21
     */

    @SerializedName("carnum")
    private String carNum;
    @SerializedName("carnum_full")
    private String carnumFull;
    @SerializedName("phone_full")
    private String phoneFull;
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
    private Object contact;
    @SerializedName("markettime")
    private String marketTime;
    @SerializedName("dumpposition")
    private String dumpPosition;
    @SerializedName("multiguige")
    private String multiGuiGe;
    @SerializedName("posupdatesign")
    private String posupDateSign;
    @SerializedName("contactphone")
    private String contactPhone;
    @SerializedName("refreshtime")
    private String refreshTime;

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarnumFull() {
        return carnumFull;
    }

    public void setCarnumFull(String carnumFull) {
        this.carnumFull = carnumFull;
    }

    public String getPhoneFull() {
        return phoneFull;
    }

    public void setPhoneFull(String phoneFull) {
        this.phoneFull = phoneFull;
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

    public Object getContact() {
        return contact;
    }

    public void setContact(Object contact) {
        this.contact = contact;
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

    public String getPosupDateSign() {
        return posupDateSign;
    }

    public void setPosupDateSign(String posupDateSign) {
        this.posupDateSign = posupDateSign;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }
}
