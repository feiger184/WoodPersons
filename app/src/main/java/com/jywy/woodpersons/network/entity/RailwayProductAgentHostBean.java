package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/23.
 */

public class RailwayProductAgentHostBean {

    @SerializedName("hostphone")
    private String agentHost;

    public String getAgentHost() {
        return agentHost;
    }

    public void setAgentHost(String agentHost) {
        this.agentHost = agentHost;
    }

    @Override
    public String toString() {
        return "RailwayProductAgentHostBean{" +
                "agentHost='" + agentHost + '\'' +
                '}';
    }
}
