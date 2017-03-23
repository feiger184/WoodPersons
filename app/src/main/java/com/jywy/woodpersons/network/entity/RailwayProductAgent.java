package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 高 on 2017/3/23.
 */
public class RailwayProductAgent {


    /**
     * host : {}
     * agent : {}
     */

    @SerializedName("host")
    private List<RailwayProductAgentHostBean> hostBean;


    @SerializedName("agent")
    private RailwayProductAgentBean agentBean;


    public List<RailwayProductAgentHostBean> getHostBean() {
        return hostBean;
    }

    public void setHostBean(List<RailwayProductAgentHostBean> hostBean) {
        this.hostBean = hostBean;
    }

    public RailwayProductAgentBean getAgentBean() {
        return agentBean;
    }

    public void setAgentBean(RailwayProductAgentBean agentBean) {
        this.agentBean = agentBean;
    }

    @Override
    public String toString() {
        return "RailwayProductAgent{" +
                "hostBean=" + hostBean +
                ", agentBean=" + agentBean +
                '}';
    }
}
