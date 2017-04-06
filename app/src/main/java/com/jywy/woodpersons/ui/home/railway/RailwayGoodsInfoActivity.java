package com.jywy.woodpersons.ui.home.railway;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.ProgressDialogFragment;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfo;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;
import com.jywy.woodpersons.network.entity.RailwayProductAgentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;


public class RailwayGoodsInfoActivity extends BaseActivity {

    @BindView(R.id.tv_time) //时间
            TextView tvTime;

    @BindView(R.id.list_info_guige)//规格
            ListView listViewInfo;
    @BindView(R.id.tv_info_train_number)//车皮号
            TextView tvRailwayNumber;
    @BindView(R.id.tv_info_portname) //口岸
            TextView tvTargetPort;
    @BindView(R.id.tv_railway_type)//车类型
            TextView tvRailwayType;
    @BindView(R.id.tv_info_current_position)//当前位置
            TextView tvCurrentPosition;
    @BindView(R.id.tv_info_reweight)//额参考重量
            TextView tvInfoReweight;
    @BindView(R.id.tv_info_update_time)//更新时间
            TextView tvUpdateTime;
    @BindView(R.id.tv_info_company)//公司地址
            TextView tvCompany;
    @BindView(R.id.layout_company)//公司按钮
            RelativeLayout btnCompany;
    @BindView(R.id.layout_phone_number)
    LinearLayout layoutPhoneNumber;

    @BindView(R.id.recycler_zuoji)//座机电话
            RecyclerView ls_zuoji;
    @BindView(R.id.recycler_phone)//手机电话
            RecyclerView ls_phone;


    @BindView(R.id.btn_infomation)
    RelativeLayout btnInfomation;

    @BindView(R.id.layout_info)
    LinearLayout layoutInfo;

    //
    private Call<RailwayGoodsInfoRsp> call;
    private ActivityUtils activityUtils;
    private ProgressDialogFragment dialogFragment;
    private RailwayGoodsInfo railwayGoodsInfo;
    private ZuoJiAdapter mAdapter;

    private static final String CD_KEY = "CD_KEY";
    private String currentPhone;
    private static final int CALL_PHONE_REQUEST = 1;

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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                railwayGoodsInfo = (RailwayGoodsInfo) msg.obj;
                setTextToTextView();
            }
        }
    };


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

        initDrawable();
    }

    private void initDrawable() {
        TextView bottom_shop = ButterKnife.findById(this, R.id.bottom_shop);
        TextView bottom_phone = ButterKnife.findById(this, R.id.bottom_phone);
        TextView bottom_message = ButterKnife.findById(this, R.id.bottom_message);

        Drawable dw_bottom_shop = getResources().getDrawable(R.drawable.show_bottom_shop);
        Drawable dw_bottom_phone = getResources().getDrawable(R.drawable.show_bottom_phone);
        Drawable dw_bottom_message = getResources().getDrawable(R.drawable.show_bottom_message);

        dw_bottom_shop.setBounds(0, 0, 60, 60);
        bottom_shop.setCompoundDrawables(null, dw_bottom_shop, null, null);//只放上边
        dw_bottom_phone.setBounds(0, 0, 45, 60);
        bottom_phone.setCompoundDrawables(null, dw_bottom_phone, null, null);//只放上边
        dw_bottom_message.setBounds(0, 0, 60, 60);
        bottom_message.setCompoundDrawables(null, dw_bottom_message, null, null);//只放上边
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
                    RailwayGoodsInfo goodsInf = body.getRailwayGoodsInfo();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = goodsInf;
                    handler.sendMessage(message);

                }
            }

            @Override
            public void onFailure(Call<RailwayGoodsInfoRsp> call, Throwable t) {
                hidePrb();
                showMsg(t.getMessage());

            }
        });

    }


    private void setTextToTextView() {
        RailwayGoodsInfoAdapter railwayGoodsInfoAdapter = new RailwayGoodsInfoAdapter();
        listViewInfo.setAdapter(railwayGoodsInfoAdapter);
        railwayGoodsInfoAdapter.reset(railwayGoodsInfo.getProductSpec());

        tvRailwayNumber.setText(railwayGoodsInfo.getProductInfo().getCarNum());
        tvRailwayType.setText(railwayGoodsInfo.getProductInfo().getTrainType());
        tvTargetPort.setText(railwayGoodsInfo.getProductInfo().getPortName());
        tvCurrentPosition.setText(railwayGoodsInfo.getProductInfo().getSpotPosition());
        tvUpdateTime.setText(railwayGoodsInfo.getProductInfo().getUpdateTime());
        tvTime.setText(railwayGoodsInfo.getProductInfo().getSalechgedTime());
        tvCompany.setText(railwayGoodsInfo.getProductInfo().getAgentName());

        if (railwayGoodsInfo.getProductAgent() != null) {
            List<RailwayProductAgentBean> agentBean = railwayGoodsInfo.getProductAgent().getAgentBean();

            if (agentBean.size() > 0) {
                List<String> zuiJiList = new ArrayList<>();
                for (int i = 0; i < agentBean.size(); i++) {
                    RailwayProductAgentBean railwayProductAgentBean = agentBean.get(i);
                    String agentPhone = railwayProductAgentBean.getAgentPhone();
                    zuiJiList.add(agentPhone);
                }
                //设置布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                ls_zuoji.setLayoutManager(linearLayoutManager);
                //设置适配器
                mAdapter = new ZuoJiAdapter(this, zuiJiList);

                mAdapter.setOnItemClickLitener(new ZuoJiAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(String phone) {
                        currentPhone = phone;
                        testCall();//检测权限
                    }
                });
                ls_zuoji.setAdapter(mAdapter);
            }
        }
    }


    /*
   * 检测权限
   * */
    private void testCall() {

        //1.检测权限
        int i = ContextCompat.checkSelfPermission(this, CALL_PHONE);

        if (i != PackageManager.PERMISSION_GRANTED) {

            //清单里面授权被拒绝 向用户申请
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{CALL_PHONE}, CALL_PHONE_REQUEST);

        } else {
            callPhone();
        }
    }

    //打电话
    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + currentPhone));
        startActivity(intent);


    }

    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == CALL_PHONE_REQUEST) {
            //判断权限是否授权了

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户真正同意了授权
                callPhone();
            } else {
                //用户给拒绝
                Toast.makeText(this, "权限拒绝了", Toast.LENGTH_SHORT).show();

                //用户彻底拒绝权限后
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, CALL_PHONE)) {

                    showDialog();
                }

            }
        }
    }

    public void showDialog() {
        new AlertDialog.Builder(this)
                .setMessage("权限被彻底拒绝，请到设置里面打开，才能使用此功能")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //跳转到本应用的设置页面 开权限
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", RailwayGoodsInfoActivity.this.getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
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
