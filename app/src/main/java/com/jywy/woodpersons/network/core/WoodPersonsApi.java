package com.jywy.woodpersons.network.core;

import com.jywy.woodpersons.network.entity.ExChangeRateRsp;
import com.jywy.woodpersons.network.entity.HomeGuanZhuRsp;
import com.jywy.woodpersons.network.entity.HomeUnSoldMarketRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsPlaceRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsRsp;
import com.jywy.woodpersons.network.entity.UnSoldMarketListRsp;
import com.jywy.woodpersons.network.entity.UnSoldMarketRsp;
import com.jywy.woodpersons.network.entity.WoodBuyInfoRsp;
import com.jywy.woodpersons.network.entity.WoodBuyRsp;
import com.jywy.woodpersons.soft_update.AppVersion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by 高 on 2017/3/21.
 */

public interface WoodPersonsApi {


    //    http://dev.sxuav.com/index.php/Mcrapp/Index/arrivelist_lie?portid=1&userid=8
    //portid=1表示满洲里口岸，userid=8需要替换成登录用户的id
    //铁路到货 满洲里 按列显示
    @Multipart
    @POST("Index/arrivelist_lie")
    Call<RailwayGoodsRsp> getRailwayData(@Part("portid") int portid, @Part("userid") int userid);


    //铁路到货 满洲里 按位置显示
    @Multipart
    @POST("Index/arrivelist_pos")
    Call<RailwayGoodsPlaceRsp> getRailwayPlace(@Part("portid") int portid, @Part("userid") int userid);


    // 列车到货列表
    //    http://dev.sxuav.com/index.php/Mcrapp/Index/arrivelistdetail?trainsign=1&portid=1&train=03&traindate=2017-02-21&userid=8
    //    第一个参数固定为1，第二个参数表示口岸，第三个参数指第几列，第4个参数日期，第五个参数是登录用户id
    @Multipart
    @POST("Index/arrivelistdetail")
    Call<RailwayGoodsListRsp> getRailwayGoodsList(@Part("trainsign") int trainsign, @Part("portid") int portid, @Part("train") String train, @Part("traindate") String traindate, @Part("userid") int userid);

// trainsign=0&displaysign=0&portid=1&spotpositionid=3
    @Multipart
    @POST("Index/arrivelistdetail")
    Call<RailwayGoodsListRsp> getRailwayGoodsListPlace(@Part("trainsign") int trainsign, @Part("displaysign") int displaysign, @Part("portid") String portid, @Part("spotpositionid") int spotpositionid, @Part("userid") int userid);

    //    车皮详情页
//    http://dev.sxuav.com/index.php/Mcrapp/Index/productdetail?cdkey=1504813544417022111050918453212641&userid=8
//    第一个参数(cdkey)是货物的id，第二个是登录用户id
    @Multipart
    @POST("Index/productdetail")
    Call<RailwayGoodsInfoRsp> getRailwayGoodsInfo(@Part("cdkey") String cdkey, @Part("userid") int userid);


    //    汇率
//    http://dev.sxuav.com/index.php/Mcrapp/Index/exchangerate
    @GET("Index/exchangerate")
    Call<ExChangeRateRsp> getExChangeRate();


    //首页未售市场展示
    // http://dev.sxuav.com/index.php/Mcrapp/Index/marketdisp
    @GET("Index/marketdisp")
    Call<HomeUnSoldMarketRsp> getHomeUnSoldMaret();


    //未售市场按钮数据
    @Multipart
    @POST("Index/market")
    Call<UnSoldMarketRsp> getUnSoldMarket(@Part("portid") int portid,
                                          @Part("kindid") int kindid,
                                          @Part("stuffid") int stuffid,
                                          @Part("productlen") int productlen,
                                          @Part("pagenum") int pagenum,
                                          @Part("userid") int userid,
                                          @Part("wide") int wide,
                                          @Part("wideMax") int wideMax,
                                          @Part("thinckness") int thinckness,
                                          @Part("thincknessMax") int thincknessMax,
                                          @Part("diamterlen") int diamterlen,
                                          @Part("diamterlenMax") int diamterlenMax);
//
//    货种kindid
//            树种stuffid
//    长度productlen
//            口岸portid
//    当前页num
////    用户ID:userid
//    　　 最小值　　　 最大值
//    径级  diamterlen   diamterlenMax
//    厚度  thinckness    thincknessMax
//    宽度  wide  wideMax


    //    http://dev.sxuav.com/index.php/Mcrapp/Index/getbaseinfo
    @Multipart
    @POST("Index/getbaseinfo")
    Call<UnSoldMarketListRsp> getUnSoldMarketListTab(@Part("userid") int userid);

    @GET("App/appVersion")
    Call<AppVersion> getAppVersion();

    //求购列表
    //请求参数：kindid,stuffid,productlen,portid,pagenum
    @Multipart
    @POST("Index/buylist")
    Call<WoodBuyRsp> getWoodBuyMarket(@Part("portid") int portid,
                                      @Part("kindid") int kindid,
                                      @Part("stuffid") int stuffid,
                                      @Part("productlen") int productlen,
                                      @Part("pagenum") int pagenum,
                                      @Part("userid") int userid,
                                      @Part("wide") int wide,
                                      @Part("wideMax") int wideMax,
                                      @Part("thinckness") int thinckness,
                                      @Part("thincknessMax") int thincknessMax,
                                      @Part("diamterlen") int diamterlen,
                                      @Part("diamterlenMax") int diamterlenMax);


    //    求购明细  index.php/Mcrapp/Index/buydetail
//    请求参数: serid,buyid
    @Multipart
    @POST("Index/buydetail")
    Call<WoodBuyInfoRsp> getWoodBuyInfo(@Part("buyid") int buyid, @Part("userid") int userid);

//    首页24小时到货量，关注人数，点击次数
//    index.php/Mcrapp/Index/dailycaption

    @GET("Index/dailycaption")
    Call<HomeGuanZhuRsp> getHomeGuanZhu();
//

}
