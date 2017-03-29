package com.jywy.woodpersons.ui.home.railway;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;

import butterknife.ButterKnife;


public class RailwayGoodsInfoActivity extends BaseActivity {
//
//    @BindView(R.id.tv_time) //时间
//            TextView tvTime;
//    @BindView(R.id.tv_railway_number)//车皮号
//            TextView tvRailwayNumber;
//    @BindView(R.id.tv_target_port) //口岸
//            TextView tvTargetPort;
//    @BindView(R.id.tv_railway_type)//车类型
//            TextView tvRailwayType;
//    @BindView(R.id.tv_company) //代理地址
//            TextView tvCompany;
//    @BindView(R.id.tv_current_position)//当前位置
//            TextView tvCurrentPosition;
//    @BindView(R.id.tv_update_time)//更新时间
//            TextView tvUpdateTime;
//    @BindView(R.id.tv_wood_guige)//木材规格
//            TextView tv_wood_guige;
//
//    @BindView(R.id.btn_company)//公司地址
//            RelativeLayout btnCompany;
//
//    @BindView(R.id.tv_zuoji)//座机电话
//            RecyclerView ls_zuoji;
//    @BindView(R.id.tv_phone)//手机电话
//            RecyclerView ls_phone;
//
//
//    @BindView(R.id.layout_phone_number)
//    LinearLayout layoutPhoneNumber;
//
//    @BindView(R.id.btn_infomation)
//    RelativeLayout btnInfomation;
//
//    @BindView(R.id.layout_info)
//    LinearLayout layoutInfo;

//
//    private Call<RailwayGoodsInfoRsp> call;
    private ActivityUtils activityUtils;
//    private ProgressDialogFragment dialogFragment;
//    private RailwayGoodsInfo railwayGoodsInfo;
//    private ZuoJiAdapter mAdapter;

    private static final String CD_KEY = "CD_KEY";
    private static final String TAG = "RailwayGoodsInfo";


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
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            if (msg.what == 1) {
//                railwayGoodsInfo = (RailwayGoodsInfo) msg.obj;
//
//                setTextToTextView();
//            }
//        }
//    };


    @Override
    protected void initView() {
        new ToolbarWrapper(this)
                .setCustomTitle(R.string.toolbar_text)
                .setShowBack(true);
        activityUtils = new ActivityUtils(this);

        // 取出传递的数据
        String cdKey = getIntent().getStringExtra(CD_KEY);

//        getGoodsInfo(cdKey);
//        btnCompany.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (layoutPhoneNumber.getVisibility() == View.VISIBLE) {
//                    layoutPhoneNumber.setVisibility(View.GONE);
//                } else {
//                    layoutPhoneNumber.setVisibility(View.VISIBLE);
//                }
//            }
//        });

//
//        btnInfomation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (layoutInfo.getVisibility() == View.VISIBLE) {
//                    layoutInfo.setVisibility(View.GONE);
//                } else {
//                    layoutInfo.setVisibility(View.VISIBLE);
//
//                }
//            }
//        });

        initDrawable();
    }

    private void initDrawable() {
        TextView bottom_shop = ButterKnife.findById(this,R.id.bottom_shop);
        TextView bottom_phone = ButterKnife.findById(this, R.id.bottom_phone);
        TextView bottom_message = ButterKnife.findById(this, R.id.bottom_message);

        Drawable dw_bottom_shop=getResources().getDrawable(R.drawable.show_bottom_shop);
        Drawable dw_bottom_phone=getResources().getDrawable(R.drawable.show_bottom_phone);
        Drawable dw_bottom_message=getResources().getDrawable(R.drawable.show_bottom_message);

        dw_bottom_shop.setBounds(0, 0, 80, 80);
        bottom_shop.setCompoundDrawables(null, dw_bottom_shop, null, null);//只放上边
        dw_bottom_phone.setBounds(0, 0, 80, 80);
        bottom_phone.setCompoundDrawables(null, dw_bottom_phone, null, null);//只放上边
        dw_bottom_message.setBounds(0, 0, 80, 80);
        bottom_message.setCompoundDrawables(null, dw_bottom_message, null, null);//只放上边
    }


    private void getGoodsInfo(String cdKey) {
//
//        showPrb();
//        call = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsInfo(cdKey, 8);
//        call.enqueue(new Callback<RailwayGoodsInfoRsp>() {
//            @Override
//            public void onResponse(Call<RailwayGoodsInfoRsp> call, Response<RailwayGoodsInfoRsp> response) {
//                if (response.isSuccessful()) {
//                    hidePrb();
//                    RailwayGoodsInfoRsp body = response.body();
//                    RailwayGoodsInfo goodsInf = body.getRailwayGoodsInfo();
//                    Message message = handler.obtainMessage();
//                    message.what = 1;
//                    message.obj = goodsInf;
//                    handler.sendMessage(message);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RailwayGoodsInfoRsp> call, Throwable t) {
//                hidePrb();
//                showMsg(t.getMessage());
//
//            }
//        });

    }


    private void setTextToTextView() {

////        tvRailwayNumber.setText(railwayGoodsInfo.getProductInfo().getCarNum());
////        tvRailwayType.setText(railwayGoodsInfo.getProductInfo().getTrainType());
////        tvTargetPort.setText(railwayGoodsInfo.getProductInfo().getPortName());
////        tvCurrentPosition.setText(railwayGoodsInfo.getProductInfo().getSpotPosition());
////        tvUpdateTime.setText(railwayGoodsInfo.getProductInfo().getUpdateTime());
////        tvTime.setText(railwayGoodsInfo.getProductInfo().getSalechgedTime());
////        tvCompany.setText(railwayGoodsInfo.getProductInfo().getAgentName());
//
////        String stuffName = railwayGoodsInfo.getProductSpec().get(0).getStuffName();
////
////        String lenName = railwayGoodsInfo.getProductSpec().get(0).getLenName();
////        String kindId = railwayGoodsInfo.getProductSpec().get(0).getKindId();
////        String kindName = railwayGoodsInfo.getProductSpec().get(0).getKindName();
////        Object timbername = railwayGoodsInfo.getProductSpec().get(0).getTimbername();
////
////        Object diameterlen = railwayGoodsInfo.getProductSpec().get(0).getDiameterlen();
////        Object spec = railwayGoodsInfo.getProductSpec().get(0).getSpec();
//
////        if (kindId.equals("1")) {
////            tv_wood_guige.setText(stuffName + "   " + lenName + "    " + kindName + "      " + diameterlen + "     " + timbername);
////        } else {
////            tv_wood_guige.setText(stuffName + "   " + lenName + "    " + kindName + "      " + spec);
////        }
////
////        if (railwayGoodsInfo.getProductAgent() != null) {
////
////
////            List<RailwayProductAgentBean> agentBean = railwayGoodsInfo.getProductAgent().getAgentBean();
////
////            if (agentBean.size() > 0) {
////
////                List<String> zuiJiList = new ArrayList<>();
////                for (int i = 0; i < agentBean.size(); i++) {
////                    RailwayProductAgentBean railwayProductAgentBean = agentBean.get(i);
////                    String agentPhone = railwayProductAgentBean.getAgentPhone();
////                    Log.e(TAG, "setTextToTextView:" + agentPhone);
////                    zuiJiList.add(agentPhone);
////                }
////                //设置布局管理器
////                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
////                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
////                ls_zuoji.setLayoutManager(linearLayoutManager);
////                //设置适配器
////                mAdapter = new ZuoJiAdapter(this, zuiJiList);
////
////                mAdapter.setOnItemClickLitener(new ZuoJiAdapter.OnItemClickLitener() {
////                    @Override
////                    public void onItemClick(String phone) {
////                        Log.e(TAG, "onItemClick: " + phone);
////                        Intent intent = new Intent();
////                        intent.setAction(Intent.ACTION_CALL);
////                        intent.setData(Uri.parse("tel:" + phone));
////                        startActivity(intent);
////                    }
////                });
////                ls_zuoji.setAdapter(mAdapter);
////
////            }
////        }
//
//
//    }
//
//
//    public void showPrb() {
//        if (dialogFragment == null) {
//            dialogFragment = new ProgressDialogFragment();
//        }
//        if (dialogFragment.isVisible()) return;
//        dialogFragment.show(getSupportFragmentManager(), "progress dialog fragment");
//    }
//
//    public void hidePrb() {
//        dialogFragment.dismiss();
//    }
//
//    public void showMsg(String msg) {
//        activityUtils.showToast(msg);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        call.cancel();
//        call = null;
    }
}
