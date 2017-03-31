package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/30.
 */

public class WoodBuyRsp extends ResponseEntity {

    @SerializedName("data")
    private List<WoodBuyEntity> woodBuyMarkets;

    @SerializedName("page")
    private UnSoldMarketPageEntity pageEntity;

    public List<WoodBuyEntity> getWoodBuyMarkets() {
        return woodBuyMarkets;
    }

    public void setWoodBuyMarkets(List<WoodBuyEntity> woodBuyMarkets) {
        this.woodBuyMarkets = woodBuyMarkets;
    }

    public UnSoldMarketPageEntity getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(UnSoldMarketPageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }
}
