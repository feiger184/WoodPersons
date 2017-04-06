package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/31.
 */

public class HomeGuanZhuRsp extends ResponseEntity {

    @SerializedName("data")
    private HomeGuanZhu homeGuanZhu;

    public HomeGuanZhu getHomeGuanZhu() {
        return homeGuanZhu;
    }

    public void setHomeGuanZhu(HomeGuanZhu homeGuanZhu) {
        this.homeGuanZhu = homeGuanZhu;
    }
}
