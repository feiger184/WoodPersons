package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/23.
 */

public class RailwayProductAgentBean {
    @SerializedName("phone")
    private String agentPhone;

    @SerializedName("agentid")
    private String agentId;

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    @Override
    public String toString() {
        return "RailwayProductAgentBean{" +
                "agentPhone='" + agentPhone + '\'' +
                ", agentId='" + agentId + '\'' +
                '}';
    }
}
