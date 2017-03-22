package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/21.
 */

public class RailwayGoodsRsp extends ResponseEntity {

    @SerializedName("data")
    private List<RailwayGoods> railwayGoodses;

    public List<RailwayGoods> getRailwayGoodsesData() {
        return railwayGoodses;
    }
}
