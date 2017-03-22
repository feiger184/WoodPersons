package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.ResponseEntity;

/**
 * Created by 高 on 2017/3/22.
 */

public class RailwayGoodsInfoRsp extends ResponseEntity {


    /*{
        "status": {
        "succeed": 1
    },
        "data": {
        "product": {
            "portid": "1",
                    "trainid": "199355",
                    "productid": "321265",
                    "productrefwight": null,
                    "carnum": "54456629",
                    "goodtype": "1",
                    "portname": "满洲里",
                    "agent": "满洲里东晨进出口贸易有限公司",
                    "agentid": "523",
                    "contactphone": "18748370111",
                    "phone_back": null,
                    "marketsign": "0",
                    "rsstype": null,
                    "salestatusname": "待售",
                    "spotpositionid": "2",
                    "spotportid": "1",
                    "dumpportid": "1",
                    "toleranceid": null,
                    "timbertypeid": null,
                    "newoldid": null,
                    "blueid": null,
                    "wormid": null,
                    "decayid": null,
                    "fromid": null,
                    "slashid": null,
                    "ringid": null,
                    "oilid": null,
                    "blackid": null,
                    "knobid": null,
                    "climbid": null,
                    "deviceid": null,
                    "dryid": null,
                    "contact": null,
                    "tolerancename": null,
                    "refnum": null,
                    "trainweight": "",
                    "refcapacity": null,
                    "dry": null,
                    "newold": "",
                    "knob": "",
                    "blue": "",
                    "worm": "",
                    "decay": "",
                    "slash": null,
                    "black": null,
                    "climb": "",
                    "oil": null,
                    "device": "",
                    "ring": null,
                    "timbertype": "",
                    "content": null,
                    "collectid": "0",
                    "username": null,
                    "userid": null,
                    "fromname": null,
                    "spotposition": "满市西货场 顺位65",
                    "updatetime": "02-21 14:25",
                    "dumpposition": null,
                    "salechgedtime": "02-21 11:05",
                    "viewnum": "31",
                    "price": "面议",
                    "timberposname": "",
                    "traintype": "平车"
        },
        "spec": [
        {
            "wide": null,
                "thinckness": null,
                "kindid": "5",
                "kindname": "板",
                "stuffid": "1",
                "stuffname": "樟子松",
                "productlen": "4",
                "lenname": "4米",
                "lenid": "4",
                "diameterlen": null,
                "timberid": null,
                "timbername": null,
                "refnum": null,
                "spec": null
        }
        ],
        "agent": [
        [
        {
            "hostphone": "187****0111"
        }
        ]
        ]
    }
    }*/
    @SerializedName("data")
    private RailwayGoodsInfo goodsInfo;

    public RailwayGoodsInfo getRailwayGoodsInfo() {
        return goodsInfo;
    }


}
