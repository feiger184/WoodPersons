package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/21.
 */

public class RailwayGoodsListRsp extends ResponseEntity {


    @SerializedName("data")
    private List<DataBean> dataX;


    public List<DataBean> getDataX() {
        return dataX;
    }

    public void setDataX(List<DataBean> dataX) {
        this.dataX = dataX;
    }

    public static class DataBean {
        /**
         * carnum : 6629
         * carnum_full : 54456629
         * phone_full : 18748370111
         * productid : 321265
         * cdkey : 1504813544417022111050918453212641
         * pic :
         * kindname : 板
         * goodtype : 1
         * lenname : 4米
         * stuffname : 樟子松
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

        private String carnum;
        private String carnum_full;
        private String phone_full;
        private String productid;
        private String cdkey;
        private String pic;
        private String kindname;
        private String goodtype;
        private String lenname;
        private String stuffname;
        private String guige;
        private String portname;
        private Object contact;
        private String markettime;
        private String dumpposition;
        private String multiguige;
        private String posupdatesign;
        private String contactphone;
        private String refreshtime;

        public String getCarnum() {
            return carnum;
        }

        public void setCarnum(String carnum) {
            this.carnum = carnum;
        }

        public String getCarnum_full() {
            return carnum_full;
        }

        public void setCarnum_full(String carnum_full) {
            this.carnum_full = carnum_full;
        }

        public String getPhone_full() {
            return phone_full;
        }

        public void setPhone_full(String phone_full) {
            this.phone_full = phone_full;
        }

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public String getCdkey() {
            return cdkey;
        }

        public void setCdkey(String cdkey) {
            this.cdkey = cdkey;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getKindname() {
            return kindname;
        }

        public void setKindname(String kindname) {
            this.kindname = kindname;
        }

        public String getGoodtype() {
            return goodtype;
        }

        public void setGoodtype(String goodtype) {
            this.goodtype = goodtype;
        }

        public String getLenname() {
            return lenname;
        }

        public void setLenname(String lenname) {
            this.lenname = lenname;
        }

        public String getStuffname() {
            return stuffname;
        }

        public void setStuffname(String stuffname) {
            this.stuffname = stuffname;
        }

        public String getGuige() {
            return guige;
        }

        public void setGuige(String guige) {
            this.guige = guige;
        }

        public String getPortname() {
            return portname;
        }

        public void setPortname(String portname) {
            this.portname = portname;
        }

        public Object getContact() {
            return contact;
        }

        public void setContact(Object contact) {
            this.contact = contact;
        }

        public String getMarkettime() {
            return markettime;
        }

        public void setMarkettime(String markettime) {
            this.markettime = markettime;
        }

        public String getDumpposition() {
            return dumpposition;
        }

        public void setDumpposition(String dumpposition) {
            this.dumpposition = dumpposition;
        }

        public String getMultiguige() {
            return multiguige;
        }

        public void setMultiguige(String multiguige) {
            this.multiguige = multiguige;
        }

        public String getPosupdatesign() {
            return posupdatesign;
        }

        public void setPosupdatesign(String posupdatesign) {
            this.posupdatesign = posupdatesign;
        }

        public String getContactphone() {
            return contactphone;
        }

        public void setContactphone(String contactphone) {
            this.contactphone = contactphone;
        }

        public String getRefreshtime() {
            return refreshtime;
        }

        public void setRefreshtime(String refreshtime) {
            this.refreshtime = refreshtime;
        }
    }
}
