package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/30.
 */

public class WoodBuyInfoRsp extends ResponseEntity {

    @SerializedName("data")
    private WoodBuyInfo unSoldMarkets;


}
