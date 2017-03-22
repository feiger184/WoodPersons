package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/22.
 */
public class RailwayProductSpec {

    /**
     * wide : null
     * thinckness : null
     * kindid : 5
     * kindname : 板
     * stuffid : 1
     * stuffname : 樟子松
     * productlen : 4
     * lenname : 4米
     * lenid : 4
     * diameterlen : null
     * timberid : null
     * timbername : null
     * refnum : null
     * spec : null
     */

    private Object wide;
    private Object thinckness;
    @SerializedName("kindid")
    private String kindId;
    @SerializedName("kindname")
    private String kindName;
    private String stuffid;
    @SerializedName("stuffname")
    private String stuffName;
    @SerializedName("productlen")
    private String productLen;
    @SerializedName("lenname")
    private String lenName;
    @SerializedName("lenid")
    private String lenId;
    private Object diameterlen;
    private Object timberid;
    private Object timbername;
    private Object refnum;
    private Object spec;

    public Object getWide() {
        return wide;
    }

    public void setWide(Object wide) {
        this.wide = wide;
    }

    public Object getThinckness() {
        return thinckness;
    }

    public void setThinckness(Object thinckness) {
        this.thinckness = thinckness;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getStuffid() {
        return stuffid;
    }

    public void setStuffid(String stuffid) {
        this.stuffid = stuffid;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }

    public String getProductLen() {
        return productLen;
    }

    public void setProductLen(String productLen) {
        this.productLen = productLen;
    }

    public String getLenName() {
        return lenName;
    }

    public void setLenName(String lenName) {
        this.lenName = lenName;
    }

    public String getLenId() {
        return lenId;
    }

    public void setLenId(String lenId) {
        this.lenId = lenId;
    }

    public Object getDiameterlen() {
        return diameterlen;
    }

    public void setDiameterlen(Object diameterlen) {
        this.diameterlen = diameterlen;
    }

    public Object getTimberid() {
        return timberid;
    }

    public void setTimberid(Object timberid) {
        this.timberid = timberid;
    }

    public Object getTimbername() {
        return timbername;
    }

    public void setTimbername(Object timbername) {
        this.timbername = timbername;
    }

    public Object getRefnum() {
        return refnum;
    }

    public void setRefnum(Object refnum) {
        this.refnum = refnum;
    }

    public Object getSpec() {
        return spec;
    }

    public void setSpec(Object spec) {
        this.spec = spec;
    }
}
