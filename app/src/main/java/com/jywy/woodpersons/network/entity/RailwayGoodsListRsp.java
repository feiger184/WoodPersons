package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/21.
 */

public class RailwayGoodsListRsp extends ResponseEntity {


    @SerializedName("data")
    private List<RailwayGoodsList> railwayGoodsLists;

    public List<RailwayGoodsList> getRailwayGoodsListsData() {
        return railwayGoodsLists;
    }
}
