package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

import java.util.List;

/**
 * Created by 高 on 2017/3/27.
 */

public class ExChangeRateRsp extends ResponseEntity {

    @SerializedName("data")
    private List<ExChangRate> exChangRates;

    public List<ExChangRate> getExChangRates() {
        return exChangRates;
    }

    public void setExChangRates(List<ExChangRate> exChangRates) {
        this.exChangRates = exChangRates;
    }

    @Override
    public String toString() {
        return "ExChangeRateRsp{" +
                "exChangRates=" + exChangRates +
                '}';
    }
}

