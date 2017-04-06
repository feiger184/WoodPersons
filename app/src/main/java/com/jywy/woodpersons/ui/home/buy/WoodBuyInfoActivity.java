package com.jywy.woodpersons.ui.home.buy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.ProgressDialogFragment;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.WoodBuyInfo;
import com.jywy.woodpersons.network.entity.WoodBuyInfoRsp;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;
import static com.jywy.woodpersons.R.id.tv_buy_info_weight;

public class WoodBuyInfoActivity extends BaseActivity {
    private static final String BUY_ID = "BUY_ID";
    private static final int CALL_PHONE_REQUEST = 1;
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
    private String contactPhone;
    private String currentPhone;
    private ProgressDialogFragment dialogFragment;
    private Call<WoodBuyInfoRsp> buyInfoRspCall;

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
        //Toolbar
        new ToolbarWrapper(this).setCustomTitle(R.string.wood_find_info);
        // 取出传递的数据
        String buyId = getIntent().getStringExtra(BUY_ID);
        getWoodBuyInfo(buyId);

    }

    private void getWoodBuyInfo(String buyId) {
        showPrb();
        buyInfoRspCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getWoodBuyInfo(Integer.valueOf(buyId), UserPrefs.getInstance().getUserid());
        buyInfoRspCall.enqueue(new Callback<WoodBuyInfoRsp>() {
            @Override
            public void onResponse(Call<WoodBuyInfoRsp> call, Response<WoodBuyInfoRsp> response) {
                hidePrb();
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
                hidePrb();
                showMsg("失败了" + t.getMessage());
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
        contactPhone = woodBuyInfo.getContactPhone();

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

    //联系人电话
    @OnClick(R.id.tv_buy_info_phone_number)
    public void callphonecontact() {
        currentPhone = contactPhone;
        testCall();
    }
    //联系人电话
    @OnClick(R.id.tv_buy_info_send_message)
    public void sendSmss() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"+ contactPhone));
        startActivity(intent);
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
                        Uri uri = Uri.fromParts("package", WoodBuyInfoActivity.this.getPackageName(), null);
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
        buyInfoRspCall.cancel();
        buyInfoRspCall = null;
    }
}
