package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/31.
 */

public class RailwayGoodsPlaceRsp extends ResponseEntity {

    @SerializedName("data")
    private List<RailwayGoodsPlace> railwayGoodsPlaces;

    public List<RailwayGoodsPlace> getRailwayGoodsPlaces() {
        return railwayGoodsPlaces;
    }

    public void setRailwayGoodsPlaces(List<RailwayGoodsPlace> railwayGoodsPlaces) {
        this.railwayGoodsPlaces = railwayGoodsPlaces;
    }
}
