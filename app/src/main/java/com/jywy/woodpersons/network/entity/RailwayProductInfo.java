package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/22.
 */
public class RailwayProductInfo {

    /**
     * portid : 1
     * trainid : 199355
     * productid : 321265
     * productrefwight : null
     * carnum : 54456629
     * goodtype : 1
     * portname : 满洲里
     * agent : 满洲里东晨进出口贸易有限公司
     * agentid : 523
     * contactphone : 18748370111
     * phone_back : null
     * marketsign : 0
     * rsstype : null
     * salestatusname : 待售
     * spotpositionid : 2
     * spotportid : 1
     * dumpportid : 1
     * toleranceid : null
     * timbertypeid : null
     * newoldid : null
     * blueid : null
     * wormid : null
     * decayid : null
     * fromid : null
     * slashid : null
     * ringid : null
     * oilid : null
     * blackid : null
     * knobid : null
     * climbid : null
     * deviceid : null
     * dryid : null
     * contact : null
     * tolerancename : null
     * refnum : null
     * trainweight :
     * refcapacity : null
     * dry : null
     * newold :
     * knob :
     * blue :
     * worm :
     * decay :
     * slash : null
     * black : null
     * climb :
     * oil : null
     * device :
     * ring : null
     * timbertype :
     * content : null
     * collectid : 0
     * username : null
     * userid : null
     * fromname : null
     * spotposition : 满市西货场 顺位65
     * updatetime : 02-21 14:25
     * dumpposition : null
     * salechgedtime : 02-21 11:05
     * viewnum : 31
     * price : 面议
     * timberposname :
     * traintype : 平车
     */

    @SerializedName("portid")
    private String portId;
    @SerializedName("trainid")
    private String trainId;
    @SerializedName("productid")
    private String productId;
    @SerializedName("productrefwight")
    private Object productrefWight;
    @SerializedName("carnum")
    private String carNum;
    @SerializedName("goodtype")
    private String goodType;
    @SerializedName("portname")
    private String portName;
    @SerializedName("agent")
    private String agentName;
    @SerializedName("agentid")
    private String agentId;
    @SerializedName("contactphone")
    private String contactPhone;
    @SerializedName("phone_back")
    private Object phoneBack;
    @SerializedName("marketsign")
    private String marketSign;
    @SerializedName("rsstype")
    private Object rssType;
    @SerializedName("salestatusname")
    private String salestatusName;
    @SerializedName("spotpositionid")
    private String spotPositionId;
    @SerializedName("spotportid")
    private String spotPortId;
    @SerializedName("dumpportid")
    private String dumpPortId;
    @SerializedName("toleranceid")
    private Object toleranceId;
    @SerializedName("timbertypeid")
    private Object timberTypeId;
    @SerializedName("newoldid")
    private Object newoldId;
    @SerializedName("blueid")
    private Object blueId;
    @SerializedName("wormid")
    private Object wormId;
    @SerializedName("decayid")
    private Object decayId;
    @SerializedName("fromid")
    private Object fromId;
    @SerializedName("slashid")
    private Object slashId;
    @SerializedName("ringid")
    private Object ringId;
    @SerializedName("oilid")
    private Object oilId;
    @SerializedName("blackid")
    private Object blackId;
    @SerializedName("knobid")
    private Object knobId;
    @SerializedName("climbid")
    private Object climbId;
    @SerializedName("deviceid")
    private Object deviceId;
    @SerializedName("dryid")
    private Object dryId;
    @SerializedName("contact")
    private Object contactN;
    @SerializedName("tolerancename")
    private Object toleranceName;
    @SerializedName("refnum")
    private Object refNum;
    @SerializedName("trainweight")
    private String trainWeight;
    @SerializedName("refcapacity")
    private Object refcaPacity;
    private Object dry;
    private String newold;
    private String knob;
    private String blue;
    private String worm;
    @SerializedName("spotposition")
    private String spotPosition;
    @SerializedName("updatetime")
    private String updateTime;
    @SerializedName("dumpposition")
    private Object dumpPosition;
    @SerializedName("salechgedtime")
    private String salechgedTime;
    @SerializedName("viewnum")
    private String viewNum;
    private String price;
    @SerializedName("timberposname")
    private String timberPosname;
    @SerializedName("traintype")
    private String trainType;

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Object getProductrefWight() {
        return productrefWight;
    }

    public void setProductrefWight(Object productrefWight) {
        this.productrefWight = productrefWight;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Object getPhoneBack() {
        return phoneBack;
    }

    public void setPhoneBack(Object phoneBack) {
        this.phoneBack = phoneBack;
    }

    public String getMarketSign() {
        return marketSign;
    }

    public void setMarketSign(String marketSign) {
        this.marketSign = marketSign;
    }

    public Object getRssType() {
        return rssType;
    }

    public void setRssType(Object rssType) {
        this.rssType = rssType;
    }

    public String getSalestatusName() {
        return salestatusName;
    }

    public void setSalestatusName(String salestatusName) {
        this.salestatusName = salestatusName;
    }

    public String getSpotPositionId() {
        return spotPositionId;
    }

    public void setSpotPositionId(String spotPositionId) {
        this.spotPositionId = spotPositionId;
    }

    public String getSpotPortId() {
        return spotPortId;
    }

    public void setSpotPortId(String spotPortId) {
        this.spotPortId = spotPortId;
    }

    public String getDumpPortId() {
        return dumpPortId;
    }

    public void setDumpPortId(String dumpPortId) {
        this.dumpPortId = dumpPortId;
    }

    public Object getToleranceId() {
        return toleranceId;
    }

    public void setToleranceId(Object toleranceId) {
        this.toleranceId = toleranceId;
    }

    public Object getTimberTypeId() {
        return timberTypeId;
    }

    public void setTimberTypeId(Object timberTypeId) {
        this.timberTypeId = timberTypeId;
    }

    public Object getNewoldId() {
        return newoldId;
    }

    public void setNewoldId(Object newoldId) {
        this.newoldId = newoldId;
    }

    public Object getBlueId() {
        return blueId;
    }

    public void setBlueId(Object blueId) {
        this.blueId = blueId;
    }

    public Object getWormId() {
        return wormId;
    }

    public void setWormId(Object wormId) {
        this.wormId = wormId;
    }

    public Object getDecayId() {
        return decayId;
    }

    public void setDecayId(Object decayId) {
        this.decayId = decayId;
    }

    public Object getFromId() {
        return fromId;
    }

    public void setFromId(Object fromId) {
        this.fromId = fromId;
    }

    public Object getSlashId() {
        return slashId;
    }

    public void setSlashId(Object slashId) {
        this.slashId = slashId;
    }

    public Object getRingId() {
        return ringId;
    }

    public void setRingId(Object ringId) {
        this.ringId = ringId;
    }

    public Object getOilId() {
        return oilId;
    }

    public void setOilId(Object oilId) {
        this.oilId = oilId;
    }

    public Object getBlackId() {
        return blackId;
    }

    public void setBlackId(Object blackId) {
        this.blackId = blackId;
    }

    public Object getKnobId() {
        return knobId;
    }

    public void setKnobId(Object knobId) {
        this.knobId = knobId;
    }

    public Object getClimbId() {
        return climbId;
    }

    public void setClimbId(Object climbId) {
        this.climbId = climbId;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public Object getDryId() {
        return dryId;
    }

    public void setDryId(Object dryId) {
        this.dryId = dryId;
    }

    public Object getContactN() {
        return contactN;
    }

    public void setContactN(Object contactN) {
        this.contactN = contactN;
    }

    public Object getToleranceName() {
        return toleranceName;
    }

    public void setToleranceName(Object toleranceName) {
        this.toleranceName = toleranceName;
    }

    public Object getRefNum() {
        return refNum;
    }

    public void setRefNum(Object refNum) {
        this.refNum = refNum;
    }

    public String getTrainWeight() {
        return trainWeight;
    }

    public void setTrainWeight(String trainWeight) {
        this.trainWeight = trainWeight;
    }

    public Object getRefcaPacity() {
        return refcaPacity;
    }

    public void setRefcaPacity(Object refcaPacity) {
        this.refcaPacity = refcaPacity;
    }

    public Object getDry() {
        return dry;
    }

    public void setDry(Object dry) {
        this.dry = dry;
    }

    public String getNewold() {
        return newold;
    }

    public void setNewold(String newold) {
        this.newold = newold;
    }

    public String getKnob() {
        return knob;
    }

    public void setKnob(String knob) {
        this.knob = knob;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getWorm() {
        return worm;
    }

    public void setWorm(String worm) {
        this.worm = worm;
    }

    public String getSpotPosition() {
        return spotPosition;
    }

    public void setSpotPosition(String spotPosition) {
        this.spotPosition = spotPosition;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Object getDumpPosition() {
        return dumpPosition;
    }

    public void setDumpPosition(Object dumpPosition) {
        this.dumpPosition = dumpPosition;
    }

    public String getSalechgedTime() {
        return salechgedTime;
    }

    public void setSalechgedTime(String salechgedTime) {
        this.salechgedTime = salechgedTime;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimberPosname() {
        return timberPosname;
    }

    public void setTimberPosname(String timberPosname) {
        this.timberPosname = timberPosname;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }
}
