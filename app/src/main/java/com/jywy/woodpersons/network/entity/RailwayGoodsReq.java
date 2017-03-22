package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.RequestParam;

/**
 * Created by 高 on 2017/3/21.
 */

public class RailwayGoodsReq extends RequestParam {

    @SerializedName("portid")  //表示口岸
    private int portId;

    @SerializedName("userid")  //表示用户id
    private int user;

    public RailwayGoodsReq(int portId, int user) {
        this.portId = portId;
        this.user = user;
    }
}
