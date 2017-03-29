package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/28.
 */

public class HomeUnSoldMarketRsp extends ResponseEntity {

    @SerializedName("data")
    private List<UnSoldMarket> homeUnSoldMarket;

    public List<UnSoldMarket> getHomeUnSoldMarket() {
        return homeUnSoldMarket;
    }

    public void setHomeUnSoldMarket(List<UnSoldMarket> homeUnSoldMarket) {
        this.homeUnSoldMarket = homeUnSoldMarket;
    }
}
