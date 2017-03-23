package com.jywy.woodpersons.ui.home.railway;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.ProgressDialogFragment;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfo;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RailwayGoodsInfoActivity extends BaseActivity {

    private static final String CD_KEY = "CD_KEY";

    @BindView(R.id.tv_time) //时间
            TextView tvTime;
    @BindView(R.id.tv_railway_number)//车皮号
            TextView tvRailwayNumber;
    @BindView(R.id.tv_target_port) //口岸
            TextView tvTargetPort;
    @BindView(R.id.tv_railway_type)//车类型
            TextView tvRailwayType;
    @BindView(R.id.tv_company) //代理地址
            TextView tvCompany;
    @BindView(R.id.tv_current_position)//当前位置
            TextView tvCurrentPosition;
    @BindView(R.id.tv_update_time)//更新时间
            TextView tvUpdateTime;
    @BindView(R.id.tv_wood_guige)//木材规格
            TextView tv_wood_guige;

    @BindView(R.id.btn_company)
    RelativeLayout btnCompany;

    @BindView(R.id.layout_phone_number)
    LinearLayout layoutPhoneNumber;

    @BindView(R.id.btn_infomation)
    RelativeLayout btnInfomation;

    @BindView(R.id.layout_info)
    LinearLayout layoutInfo;


    private RailwayGoodsInfo railwayGoodsInfo;
    private Call<RailwayGoodsInfoRsp> call;
    private ActivityUtils activityUtils;
    private ProgressDialogFragment dialogFragment;


    // 因为需要传递数据，为了规范我们传递的数据内容，所以我们在此页面对外提供一个跳转的方法
    public static Intent getStartIntent(Context context, String cdkey) {

        Intent intent = new Intent(context, RailwayGoodsInfoActivity.class);
        intent.putExtra(CD_KEY, cdkey);
        return intent;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            railwayGoodsInfo = (RailwayGoodsInfo) msg.obj;


            tvRailwayNumber.setText(railwayGoodsInfo.getProductInfo().getCarNum());
            tvRailwayType.setText(railwayGoodsInfo.getProductInfo().getTrainType());
            tvTargetPort.setText(railwayGoodsInfo.getProductInfo().getPortName());
            tvCurrentPosition.setText(railwayGoodsInfo.getProductInfo().getSpotPosition());
            tvUpdateTime.setText(railwayGoodsInfo.getProductInfo().getUpdateTime());
            tvTime.setText(railwayGoodsInfo.getProductInfo().getSalechgedTime());

            String stuffName = railwayGoodsInfo.getProductSpec().get(0).getStuffName();
            String lenName = railwayGoodsInfo.getProductSpec().get(0).getLenName();
            String kindId = railwayGoodsInfo.getProductSpec().get(0).getKindId();
            String kindName = railwayGoodsInfo.getProductSpec().get(0).getKindName();
            Object timbername = railwayGoodsInfo.getProductSpec().get(0).getTimbername();
            Object diameterlen = railwayGoodsInfo.getProductSpec().get(0).getDiameterlen();
            Object spec = railwayGoodsInfo.getProductSpec().get(0).getSpec();
            if (kindId.equals("1")) {
                tv_wood_guige.setText(stuffName + "   " + lenName + "    " + kindName + "      " + diameterlen + "     " + timbername);
            } else {
                tv_wood_guige.setText(stuffName + "   " + lenName + "    " + kindName + "      " +spec);

            }
        }
    };

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_list_show;
    }


    @Override
    protected void initView() {
        new ToolbarWrapper(this)
                .setCustomTitle(R.string.toolbar_text)
                .setShowBack(true);
        activityUtils = new ActivityUtils(this);

        // 取出传递的数据
        String cdKey = getIntent().getStringExtra(CD_KEY);

        getGoodsInfo(cdKey);
        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutPhoneNumber.getVisibility() == View.VISIBLE) {
                    layoutPhoneNumber.setVisibility(View.GONE);
                } else {
                    layoutPhoneNumber.setVisibility(View.VISIBLE);
                }
            }
        });


        btnInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutInfo.getVisibility() == View.VISIBLE) {
                    layoutInfo.setVisibility(View.GONE);
                } else {
                    layoutInfo.setVisibility(View.VISIBLE);

                }

            }
        });

    }


    private void getGoodsInfo(String cdKey) {

        showPrb();
        call = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsInfo(cdKey, 8);
        call.enqueue(new Callback<RailwayGoodsInfoRsp>() {
            @Override
            public void onResponse(Call<RailwayGoodsInfoRsp> call, Response<RailwayGoodsInfoRsp> response) {
                if (response.isSuccessful()) {
                    hidePrb();
                    RailwayGoodsInfoRsp body = response.body();
                    RailwayGoodsInfo goodsInfo = body.getRailwayGoodsInfo();

                    Message msg = handler.obtainMessage();
                    msg.obj = goodsInfo;
                    handler.sendMessage(msg);

                }
            }

            @Override
            public void onFailure(Call<RailwayGoodsInfoRsp> call, Throwable t) {
                hidePrb();
                showMsg(t.getMessage());
            }
        });

    }



    public void showPrb() {
        if (dialogFragment == null) {
            dialogFragment = new ProgressDialogFragment();
        }
        if (dialogFragment.isVisible()) return;
        dialogFragment.show(getSupportFragmentManager(), "progress dialog fragment");
    }

    public void hidePrb() {
        dialogFragment.dismiss();
    }

    public void showMsg(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        call.cancel();
        call = null;
    }
}
