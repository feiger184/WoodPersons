package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 高 on 2017/3/22.
 */

public class RailwayGoodsInfo {


    public RailwayProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(RailwayProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public List<RailwayProductSpec> getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(List<RailwayProductSpec> productSpec) {
        this.productSpec = productSpec;
    }

    @SerializedName("product")
    private RailwayProductInfo productInfo;

    @SerializedName("spec")  //1条
    private List<RailwayProductSpec> productSpec;


    private List<List<AgentBean>> agent;

    public List<List<AgentBean>> getAgent() {
        return agent;
    }

    public void setAgent(List<List<AgentBean>> agent) {
        this.agent = agent;
    }

    public static class AgentBean {
        /**
         * hostphone : 187****0111
         */

        @SerializedName("hostphone")
        private String hostPhone;

        public String getHostPhone() {
            return hostPhone;
        }

        public void setHostPhone(String hostPhone) {
            this.hostPhone = hostPhone;
        }
    }
}
