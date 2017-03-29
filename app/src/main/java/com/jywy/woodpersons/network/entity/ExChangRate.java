package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/27.
 */
public class ExChangRate {


    /**
     * currencycode : USD
     * currencyname : 美元
     * meanprice : 6.8725
     */

    @SerializedName("currencycode")
    private String currencyCode;
    @SerializedName("currencyname")
    private String currencyName;
    @SerializedName("meanprice")
    private String meanPrice;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getMeanPrice() {
        return meanPrice;
    }

    public void setMeanPrice(String meanPrice) {
        this.meanPrice = meanPrice;
    }

    @Override
    public String toString() {
        return "ExChangRate{" +
                "currencyCode='" + currencyCode + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", meanPrice='" + meanPrice + '\'' +
                '}';
    }
}
