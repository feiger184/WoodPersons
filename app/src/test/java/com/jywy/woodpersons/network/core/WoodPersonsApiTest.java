package com.jywy.woodpersons.network.core;

import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsRsp;

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

}