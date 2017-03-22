package com.jywy.woodpersons.ui.home.railway;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfo;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RailwayGoodsInfoActivity extends BaseActivity {

    private static final String CD_KEY = "CD_KEY";
//
//    @BindView(R.id.tv_share)
//    TextView tvShare;
//    @BindView(R.id.tv_attention)
//    TextView tvAttention;
//    @BindView(R.id.tv_pinformation)
//    TextView tvPinformation;
//    @BindView(R.id.tv_time) //时间
//            TextView tvTime;
//    @BindView(R.id.tv_railway_number)//车皮号
//            TextView tvRailwayNumber;
//    @BindView(R.id.tv_reweight)
//    TextView tvReweight;
//    @BindView(R.id.tv_target_port) //口岸
//            TextView tvTargetPort;
//    @BindView(R.id.tv_railway_type)//车类型
//            TextView tvRailwayType;
//    @BindView(R.id.two)
//    TextView two;
//    @BindView(R.id.tv_company) //代理地址
//            TextView tvCompany;
//    @BindView(R.id.tv_owner_name)
//    TextView tvOwnerName;
//    @BindView(R.id.iv_pulldown)
//    ImageView ivPulldown;
//    @BindView(R.id.tv_refer_number)
//    TextView tvReferNumber;
//    @BindView(R.id.tv_current_position)//当前位置
//            TextView tvCurrentPosition;
//    @BindView(R.id.tv_update_time)//更新时间
//            TextView tvUpdateTime;
//    @BindView(R.id.tv_tolerance)
//    TextView tvTolerance;
//    @BindView(R.id.tv_drying)
//    TextView tvDrying;
//    @BindView(R.id.tv_decayed)
//    TextView tvDecayed;
//    @BindView(R.id.tv_ord_new)
//    TextView tvOrdNew;
//    @BindView(R.id.tv_oblique_fissure)
//    TextView tvObliqueFissure;
//    @BindView(R.id.tv_oil_pumping)
//    TextView tvOilPumping;
//    @BindView(R.id.tv_based_location)
//    TextView tvBasedLocation;
//    @BindView(R.id.tv_processing_equipment)
//    TextView tvProcessingEquipment;
//    @BindView(R.id.tv_general_cargo)
//    TextView tvGeneralCargo;
//    @BindView(R.id.tv_bark)
//    TextView tvBark;
//    @BindView(R.id.tv_wormhole)
//    TextView tvWormhole;
//    @BindView(R.id.tv_blue_stain)
//    TextView tvBlueStain;
//    @BindView(R.id.tv_ring_shake)
//    TextView tvRingShake;
//    @BindView(R.id.tv_stammer)
//    TextView tvStammer;
//    @BindView(R.id.tv_black_heart)
//    TextView tvBlackHeart;
//    @BindView(R.id.tv_producing_area)
//    TextView tvProducingArea;
//    @BindView(R.id.edit_text)
//    EditText editText;
//    @BindView(R.id.bt_commit)
//    Button btCommit;
//    @BindView(R.id.cb_liuyan)
//    CheckBox cbLiuyan;
//    @BindView(R.id.activity_railway_list_show)
//    RelativeLayout activityRailwayListShow;

    private RailwayGoodsInfo railwayGoodsInfo;
    private Call<RailwayGoodsInfoRsp> call;


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
        call = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsInfo(cdKey, 8);
        call.enqueue(goodInfoCallBack);
        Log.e("+================ ","initView:"+ railwayGoodsInfo.getProductInfo().getCarNum());
//        tvRailwayNumber.setText(railwayGoodsInfo.getProductInfo().getCarNum());
//        tvRailwayType.setText(railwayGoodsInfo.getProductInfo().getTrainType());
//        tvTargetPort.setText(railwayGoodsInfo.getProductInfo().getPortName());
//        tvCurrentPosition.setText(railwayGoodsInfo.getProductInfo().getSpotPosition());
//        tvUpdateTime.setText(railwayGoodsInfo.getProductInfo().getUpdateTime());
////        tvCompany.setText(railwayGoodsInfo.getProductSpec().get(0).);
//        tvTime.setText(railwayGoodsInfo.getProductInfo().getSalechgedTime());

    }

    Callback<RailwayGoodsInfoRsp> goodInfoCallBack = new Callback<RailwayGoodsInfoRsp>() {
        @Override
        public void onResponse(Call<RailwayGoodsInfoRsp> call, Response<RailwayGoodsInfoRsp> response) {
            if (response.isSuccessful()) {
                RailwayGoodsInfoRsp body = response.body();
                    railwayGoodsInfo = body.getRailwayGoodsInfo();
            }
        }

        @Override
        public void onFailure(Call<RailwayGoodsInfoRsp> call, Throwable t) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        call.cancel();
        call = null;
    }
}
