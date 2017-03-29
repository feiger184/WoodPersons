package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

/**
 * Created by 高 on 2017/3/22.
 */

public class RailwayGoodsInfoRsp extends ResponseEntity {
  /*{

    "data": {
       "product": {
              }，
       "spec": [
               {

                }
              ],
    "agent": {
      "host": [
           {
               "hostphone": "137****3739"
             },
         {
        "hostphone": "137****3111"
      },
      {
        "hostphone": "138****2637"
      },
      {
        "hostphone": "138****5006"
      },
      {
        "hostphone": "150****9178"
      }
      ],
      "agent": [
      {
        "phone": "0470-6222099",
        "agentid": "385"
      }
      ]
    }
  }
  }*/
    @SerializedName("data")
    private RailwayGoodsInfo goodsInfo;

    public RailwayGoodsInfo getRailwayGoodsInfo() {
        return goodsInfo;
    }


}
