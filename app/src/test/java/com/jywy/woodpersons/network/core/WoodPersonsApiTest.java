package com.jywy.woodpersons.network.core;

import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;
import com.jywy.woodpersons.network.entity.UnSoldMarketListRsp;
import com.jywy.woodpersons.network.entity.UnSoldMarketRsp;

import org.junit.Test;

import retrofit2.Call;

import static org.junit.Assert.*;

/**
 * Created by 高 on 2017/3/21.
 */
public class WoodPersonsApiTest {

    @Test
    public void getRailwayData() throws Exception {

    }

    @Test
    public void getRailwayGoodsList() throws Exception {
        Call<RailwayGoodsListRsp> railwayGoodsList = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsList(1, 1, "03", "2017-02-21", 8);
        railwayGoodsList.execute();
        assertTrue(true);

    }

    @Test
    public void getRailwayGoodsInfo() throws Exception {
        Call<RailwayGoodsInfoRsp> railwayGoodsList = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsInfo("1334704115117021920190140133199381", 8);
        railwayGoodsList.execute();
        assertTrue(true);

    }

    @Test
    public void getUnSoldMarket() throws Exception {
        Call<UnSoldMarketRsp> unSoldMarket = WoodPersonsClient.getInstance().getWoodPersonsApi().getUnSoldMarket(0, 0, 0, 0, 0, 8);
        unSoldMarket.execute();
        assertTrue(true);

    }

    @Test
    public void getUnSoldMarketListTab() throws Exception {
        Call<UnSoldMarketListRsp> sdfas = WoodPersonsClient.getInstance().getWoodPersonsApi().getUnSoldMarketListTab(8);
        sdfas.execute();
        assertTrue(true);

    }

}