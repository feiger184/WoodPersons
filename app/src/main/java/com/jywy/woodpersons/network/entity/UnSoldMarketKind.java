package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/27.
 */

public class UnSoldMarketKind {

    /**
     * kindid : 1
     * kindname : 原木
     * kind_bj : 0
     * portid : 1
     * kindname_en-us : 0
     */

    @SerializedName("kindid")
    private String kindId;
    @SerializedName("kindname")
    private String kindName;

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}
