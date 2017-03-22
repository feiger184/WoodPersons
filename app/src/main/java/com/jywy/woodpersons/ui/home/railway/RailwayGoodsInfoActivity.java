package com.jywy.woodpersons.ui.home.railway;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class RailwayGoodsInfoActivity extends BaseActivity {

    private static final String CD_KEY = "CD_KEY";


    // 因为需要传递数据，为了规范我们传递的数据内容，所以我们在此页面对外提供一个跳转的方法
    public static Intent getStartIntent(Context context, String cdkey) {

        Intent intent = new Intent(context, RailwayGoodsInfoActivity.class);
        intent.putExtra(CD_KEY, cdkey);
        return intent;
    }


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_goods_info;
    }


    @Override
    protected void initView() {

        // 取出传递的数据
        String cdKey = getIntent().getStringExtra(CD_KEY);
        Call<RailwayGoodsInfoRsp> call = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsInfo(cdKey, 8);
        call.enqueue(goodInfoCallBack);

    }

    Callback<RailwayGoodsInfoRsp> goodInfoCallBack = new Callback<RailwayGoodsInfoRsp>() {
        @Override
        public void onResponse(Call<RailwayGoodsInfoRsp> call, Response<RailwayGoodsInfoRsp> response) {
            if (response.isSuccessful()) {


                RailwayGoodsInfoRsp body = response.body();
                String carNum = body.getRailwayGoodsInfo().getProductInfo().getCarNum();
                Log.e("weeeeeeeeeeeee", "onResponse: " + carNum);
            }

        }

        @Override
        public void onFailure(Call<RailwayGoodsInfoRsp> call, Throwable t) {

        }
    };


}
