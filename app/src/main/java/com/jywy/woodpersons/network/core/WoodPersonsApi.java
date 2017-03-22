package com.jywy.woodpersons.network.core;

import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsRsp;

import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 高 on 2017/3/21.
 */

public interface WoodPersonsApi {


    //    http://dev.sxuav.com/index.php/Mcrapp/Index/arrivelist_lie?portid=1&userid=8
//    portid=1表示满洲里口岸，userid=8需要替换成登录用户的id
    //铁路到货 满洲里
    @Multipart
    @POST("arrivelist_lie")
    Call<RailwayGoodsRsp> getRailwayData(@Part("portid") int portid, @Part("userid") int userid);


    // 列车到货列表
    @Multipart
    @POST("arrivelistdetail")
    Call<RailwayGoodsListRsp> getRailwayGoodsList(@Part("trainsign") int trainsign, @Part("portid") int portid, @Part("train") String train, @Part("traindate") String traindate, @Part("userid") int userid);
//    第一个参数固定为1，第二个参数表示口岸，第三个参数指第几列，第4个参数日期，第五个参数是登录用户id

//
//    @Multipart
//    @POST("arrivelistdetail")
//    车皮详情页
//    http://dev.sxuav.com/index.php/Mcrapp/Index/productdetail?cdkey=1504813544417022111050918453212641&userid=8
//    第一个参数(cdkey)是货物的id，第二个是登录用户id
}