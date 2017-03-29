package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 高 on 2017/3/22.
 */

public class RailwayGoodsInfo {

    @SerializedName("product")
    private RailwayProductInfo productInfo;

    @SerializedName("spec")
    private List<RailwayProductSpec> productSpec;

    @SerializedName("agent")
    private RailwayProductAgent productAgent;


    public RailwayProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(RailwayProductInfo productInfo) {
        this.productInfo = productInfo;
    }


    public List<RailwayProductSpec> getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(List<RailwayProductSpec> productSpec) {
        this.productSpec = productSpec;
    }


    public RailwayProductAgent getProductAgent() {
        return productAgent;
    }

    public void setProductAgent(RailwayProductAgent productAgent) {
        this.productAgent = productAgent;
    }
}
