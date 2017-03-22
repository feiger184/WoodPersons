package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.core.RequestParam;

/**
 * Created by 高 on 2017/3/21.
 */

public class RailwayGoodsListReq extends RequestParam {


    @SerializedName("trainsign") //固定为1
    private int trainSign;

    @SerializedName("portid")  //表示口岸
    private String portId;

    @SerializedName("train")//指第几列  03
    private String train;


    @SerializedName("traindate")//日期  2017-02-21
    private String trainDate;


    @SerializedName("userid")//登录用户id
    private int userId;



}
