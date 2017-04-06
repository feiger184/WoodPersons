package com.jywy.woodpersons.network.core;

import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;
import com.jywy.woodpersons.network.entity.UnSoldMarketListRsp;
import com.jywy.woodpersons.network.entity.UserRegisterCodeRsp;
import com.jywy.woodpersons.soft_update.AppVersion;

import org.junit.Test;

import retrofit2.Call;

import static org.junit.Assert.assertTrue;

/**
 * Created by 高 on 2017/3/21.
 */
public class WoodPersonsApiTest {

    @Test
    public void getRailwayData() throws Exception {

    }

    @Test
    public void getRailwayGoodsList() throws Exception {
        Call<RailwayGoodsListRsp> railwayGoodsList = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsList(1, 1, "03", "2017-03-28", 8);
        railwayGoodsList.execute();
        assertTrue(true);

    }

//    http://dev.sxuav.com/index.php/Home/Index/showproductdetail.html?cdkey=1864498551417022012572043713205382
    @Test
    public void getRailwayGoodsListPlace() throws Exception {
        Call<RailwayGoodsListRsp> railwayGoodsList = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsListPlace(0, 1, 1, 51, 8);
        railwayGoodsList.execute();
        assertTrue(true);

    }

//    http://dev.sxuav.com/index.php/Home/Index/showproductdetail.html?cdkey=1864498551417022012572043713205382
    @Test
    public void getRailwayGoodsInfo() throws Exception {
        Call<RailwayGoodsInfoRsp> railwayGoodsList = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsInfo("1864498551417022012572043713205382", 8);
        railwayGoodsList.execute();
        assertTrue(true);

    }



    @Test
    public void getUnSoldMarketListTab() throws Exception {
        Call<UnSoldMarketListRsp> sdfas = WoodPersonsClient.getInstance().getWoodPersonsApi().getUnSoldMarketListTab(8);
        sdfas.execute();
        assertTrue(true);

    }

    @Test
    public void getAppVersion() throws Exception {
        Call<AppVersion> sas = WoodPersonsClient.getInstance().getWoodPersonsApi().getAppVersion();
        sas.execute();
        assertTrue(true);

    }
    @Test
    public void getRegisterCode() throws Exception {
        Call<UserRegisterCodeRsp> registerCode = WoodPersonsClient.getInstance().getWoodPersonsApi().getRegisterCode("18435163690");
        registerCode.execute();
        assertTrue(true);

    }


}