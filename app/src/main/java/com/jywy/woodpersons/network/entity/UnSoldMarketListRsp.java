package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;


/**
 * Created by 高 on 2017/3/28.
 */

public class UnSoldMarketListRsp extends ResponseEntity {

    @SerializedName("data")
    private UnSoldMarketList unSoldMarketList;

    public UnSoldMarketList getUnSoldMarketList() {
        return unSoldMarketList;
    }

    public void setUnSoldMarketList(UnSoldMarketList unSoldMarketList) {
        this.unSoldMarketList = unSoldMarketList;
    }
}
