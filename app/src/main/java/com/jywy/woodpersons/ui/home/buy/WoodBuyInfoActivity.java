package com.jywy.woodpersons.ui.home.buy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.WoodBuyInfo;
import com.jywy.woodpersons.network.entity.WoodBuyInfoRsp;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jywy.woodpersons.R.id.tv_buy_info_weight;

public class WoodBuyInfoActivity extends BaseActivity {


    private static final String BUY_ID = "BUY_ID";
    @BindView(R.id.tv_buy_info_guige)
    TextView tvBuyInfoGuige;
    @BindView(R.id.tv_buy_info_time)
    TextView tvBuyInfoTime;
    @BindView(R.id.tv_buy_info_share)
    TextView tvBuyInfoShare;
    @BindView(R.id.tv_buy_info_tree)
    TextView tvBuyInfoTree;
    @BindView(R.id.tv_buy_info_length)
    TextView tvBuyInfoLength;
    @BindView(R.id.tv_buy_info_height)
    TextView tvBuyInfoHeight;
    @BindView(R.id.tv_buy_info_kind)
    TextView tvBuyInfoKind;
    @BindView(tv_buy_info_weight)
    TextView tvBuyInfoWeight;
    @BindView(R.id.tv_buy_info_width)
    TextView tvBuyInfoWidth;
    @BindView(R.id.tv_buy_info_price)
    TextView tvBuyInfoPrice;
    @BindView(R.id.tv_buy_info_portname)
    TextView tvBuyInfoPortname;
    @BindView(R.id.tv_buy_info_phone_number)
    TextView tvBuyInfoPhoneNumber;
    @BindView(R.id.tv_buy_info_send_message)
    TextView tvBuyInfoSendMessage;
    @BindView(R.id.layout_houdu)
    LinearLayout layoutHoudu;
    @BindView(R.id.tv_buy_info_jingji)
    TextView tvBuyInfoJingji;
    @BindView(R.id.layout_zhijing)
    LinearLayout layoutZhijing;
    @BindView(R.id.layout_kuandu)
    LinearLayout layoutKuandu;
    @BindView(R.id.tv_buy_info_caizhi)
    TextView tvBuyInfoCaizhi;
    @BindView(R.id.layout_caizhi)
    LinearLayout layoutCaizhi;


    private ActivityUtils activityUtils;
    private WoodBuyInfo woodBuyInfo;

    // 因为需要传递数据，为了规范我们传递的数据内容，所以我们在此页面对外提供一个跳转的方法
    public static Intent getStartIntent(Context context, String buyID) {

        Intent intent = new Intent(context, WoodBuyInfoActivity.class);
        intent.putExtra(BUY_ID, buyID);
        return intent;
    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                woodBuyInfo = (WoodBuyInfo) msg.obj;
                addDataToTextView();//将数据展示
            }
        }
    };

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_worry_buy_show;
    }

    @Override
    protected void initView() {
        activityUtils = new ActivityUtils(this);
        // 取出传递的数据
        String buyId = getIntent().getStringExtra(BUY_ID);
        getWoodBuyInfo(buyId);

    }

    private void getWoodBuyInfo(String buyId) {

        Call<WoodBuyInfoRsp> woodBuyInfo = WoodPersonsClient.getInstance().getWoodPersonsApi().getWoodBuyInfo(Integer.valueOf(buyId), UserPrefs.getInstance().getUserid());
        woodBuyInfo.enqueue(new Callback<WoodBuyInfoRsp>() {
            @Override
            public void onResponse(Call<WoodBuyInfoRsp> call, Response<WoodBuyInfoRsp> response) {

                if (response.isSuccessful()) {
                    WoodBuyInfoRsp body = response.body();
                    WoodBuyInfo woodBuyInfo = body.getWoodBuyInfo();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = woodBuyInfo;
                    handler.sendMessage(message);

                }
            }

            @Override
            public void onFailure(Call<WoodBuyInfoRsp> call, Throwable t) {
                activityUtils.showToast("失败了" + t.getMessage());
            }
        });
    }


    //将数据展示
    private void addDataToTextView() {
        String portName = woodBuyInfo.getPortName();
        String stuffName = woodBuyInfo.getStuffName();
        String lenName = woodBuyInfo.getLenName();
        String kindName = woodBuyInfo.getKindName();
        String publishTime = woodBuyInfo.getPublishTime();
        String refcaPacity = woodBuyInfo.getRefcaPacity();
        String diameterlen = woodBuyInfo.getDiameterlen();
        String wide = woodBuyInfo.getWide();
        String thinckNess = woodBuyInfo.getThinckNess();
        Object timberName = woodBuyInfo.getTimberName();

        String price = woodBuyInfo.getPrice();
        String contactPhone = woodBuyInfo.getContactPhone();

        tvBuyInfoGuige.setText(portName + "   " + stuffName + "   " + lenName + "   " + kindName);
        tvBuyInfoTime.setText(publishTime + "");
        tvBuyInfoTree.setText(stuffName + "");
        tvBuyInfoKind.setText(kindName + "");
        tvBuyInfoLength.setText(lenName + "");
        tvBuyInfoWeight.setText(refcaPacity+"");
        if (kindName.equals("原木")) {
            layoutKuandu.setVisibility(View.GONE);
            layoutHoudu.setVisibility(View.GONE);
            layoutZhijing.setVisibility(View.VISIBLE);
            layoutCaizhi.setVisibility(View.VISIBLE);
            tvBuyInfoCaizhi.setText(timberName+"");
            tvBuyInfoJingji.setText(diameterlen+"");
        } else {
            layoutKuandu.setVisibility(View.VISIBLE);
            layoutHoudu.setVisibility(View.VISIBLE);
            layoutZhijing.setVisibility(View.GONE);
            layoutCaizhi.setVisibility(View.GONE);
            tvBuyInfoHeight.setText(thinckNess+"");
            tvBuyInfoWidth.setText(wide + "");
        }
        tvBuyInfoPrice.setText(price+"");

        tvBuyInfoPortname.setText(portName + "");
        tvBuyInfoPhoneNumber.setText(contactPhone + "");



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
