package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/30.
 */
public class WoodBuyInfo {


    /**
     * userid : 4437
     * buyid : 9312
     * timberid : 0
     * portname : 满洲里
     * kindname : 板
     * kindid : 5
     * stuffname : 樟子松
     * lenname : 3米
     * contact :
     * contactphone : 13964977908
     * portid : 1
     * stuffid : 1
     * productlenid : 3
     * rsstype : 1
     * diameterlen1 :
     * diameterlenmax :
     * wide1 : 63
     * widemax : 63
     * thinckness1 : 33
     * thincknessmax : 33
     * diameterlen : 0φ
     * wide : 63~63
     * thinckness : 33~33
     * content :
     * publishtime : 2017-02-06 11:47:32
     * timbername : null
     * price :
     * refcapacity :
     * viewnum : 1
     * refreshtime : 02-06
     */

    @SerializedName("userid")
    private String userId;
    @SerializedName("buyid")
    private String buyId;
    @SerializedName("timberid")
    private String timberId;
    @SerializedName("portname")
    private String portName;
    @SerializedName("kindname")
    private String kindName;
    @SerializedName("kindid")
    private String kindId;
    @SerializedName("stuffname")
    private String stuffName;
    @SerializedName("lenname")
    private String lenName;
    private String contact;
    @SerializedName("contactphone")
    private String contactPhone;
    @SerializedName("portid")
    private String portId;
    @SerializedName("stuffid")
    private String stuffId;
    @SerializedName("productlenid")
    private String productLenId;
    @SerializedName("rsstype")
    private String rssType;
    @SerializedName("diameterlen1")
    private String diameterlen;
    @SerializedName("diameterlenmax")
    private String diameterLenMax;
    @SerializedName("wide1")
    private String wideW;
    @SerializedName("widemax")
    private String wideMax;
    @SerializedName("thinckness1")
    private String thinckNessOne;
    @SerializedName("thincknessmax")
    private String thincknessMax;
    @SerializedName("diameterlen")
    private String diameterLen;
    private String wide;
    @SerializedName("thinckness")
    private String thinckNess;
    private String content;
    @SerializedName("publishtime")
    private String publishTime;
    @SerializedName("timbername")
    private Object timberName;
    private String price;
    @SerializedName("refcapacity")
    private String refcaPacity;
    @SerializedName("viewnum")
    private String viewNum;
    @SerializedName("refreshtime")
    private String refreshTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getTimberId() {
        return timberId;
    }

    public void setTimberId(String timberId) {
        this.timberId = timberId;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }

    public String getLenName() {
        return lenName;
    }

    public void setLenName(String lenName) {
        this.lenName = lenName;
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

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getStuffId() {
        return stuffId;
    }

    public void setStuffId(String stuffId) {
        this.stuffId = stuffId;
    }

    public String getProductLenId() {
        return productLenId;
    }

    public void setProductLenId(String productLenId) {
        this.productLenId = productLenId;
    }

    public String getRssType() {
        return rssType;
    }

    public void setRssType(String rssType) {
        this.rssType = rssType;
    }

    public String getDiameterlen() {
        return diameterlen;
    }

    public void setDiameterlen(String diameterlen) {
        this.diameterlen = diameterlen;
    }

    public String getDiameterLenMax() {
        return diameterLenMax;
    }

    public void setDiameterLenMax(String diameterLenMax) {
        this.diameterLenMax = diameterLenMax;
    }

    public String getWideW() {
        return wideW;
    }

    public void setWideW(String wideW) {
        this.wideW = wideW;
    }

    public String getWideMax() {
        return wideMax;
    }

    public void setWideMax(String wideMax) {
        this.wideMax = wideMax;
    }

    public String getThinckNessOne() {
        return thinckNessOne;
    }

    public void setThinckNessOne(String thinckNessOne) {
        this.thinckNessOne = thinckNessOne;
    }

    public String getThincknessMax() {
        return thincknessMax;
    }

    public void setThincknessMax(String thincknessMax) {
        this.thincknessMax = thincknessMax;
    }

    public String getDiameterLen() {
        return diameterLen;
    }

    public void setDiameterLen(String diameterLen) {
        this.diameterLen = diameterLen;
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    public String getThinckNess() {
        return thinckNess;
    }

    public void setThinckNess(String thinckNess) {
        this.thinckNess = thinckNess;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Object getTimberName() {
        return timberName;
    }

    public void setTimberName(Object timberName) {
        this.timberName = timberName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRefcaPacity() {
        return refcaPacity;
    }

    public void setRefcaPacity(String refcaPacity) {
        this.refcaPacity = refcaPacity;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(String refreshTime) {
        this.refreshTime = refreshTime;
    }
}
