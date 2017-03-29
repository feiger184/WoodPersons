package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/27.
 */

public class UnSoldMarketRsp extends ResponseEntity {

    @SerializedName("data")
    private List<UnSoldMarket> unSoldMarkets;

    @SerializedName("page")
    private UnSoldMarketPageEntity pageEntity;



    public List<UnSoldMarket> getUnSoldMarkets() {
        return unSoldMarkets;
    }

    public void setUnSoldMarkets(List<UnSoldMarket> unSoldMarkets) {
        this.unSoldMarkets = unSoldMarkets;
    }

    public UnSoldMarketPageEntity getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(UnSoldMarketPageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }


}
