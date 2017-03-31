package com.jywy.woodpersons.ui.home.buy;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.WoodBuyEntity;
import com.jywy.woodpersons.network.entity.WoodBuyRsp;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WoodBuy extends BaseActivity {
    @BindViews({R.id.tv_buy_place, R.id.tv_buy_goods, R.id.tv_buy_tree, R.id.tv_buy_more})
    TextView[] textViews;
    @BindView(R.id.list_wood_buy)
    ListView listWoodBuyGoods;

    @BindView(R.id.tv_buy_error_text)
    TextView tvErrorText;

    private int portId;//区域
    private int kindid;//货种
    private int stuffid;//树种
    private int productlen;//长度
    private int pagenum;//当前页

    private ActivityUtils activityUtils;
    private PtrWrapper ptrWrapper;
    private WoodBuyAdapter woodBuyAdapter;
    private Call<WoodBuyRsp> woodBuyEntityCall;

    private List<WoodBuyEntity> woodBuyMarkets;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                woodBuyMarkets = (List<WoodBuyEntity>) msg.obj;

            }
        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_wood_buy;
    }

    @Override
    protected void initView() {
        activityUtils = new ActivityUtils(this);
        //Toolbar
        new ToolbarWrapper(this).setCustomTitle(R.string.wood_find);

        ptrWrapper = new PtrWrapper(this, true) {
            @Override
            protected void onRefresh() {
                LoadData(true);
            }

            @Override
            protected void onLoadMore() {
                LoadData(false);
            }
        };

        //listview适配器
        woodBuyAdapter = new WoodBuyAdapter();

        // 自动刷新
        ptrWrapper.postRefreshDelayed(50);
    }

    //加载listview数据
    private void LoadData(final boolean isRefresh) {

        if (woodBuyEntityCall != null) {
            woodBuyEntityCall.cancel();
        }
        woodBuyEntityCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getWoodBuyMarket(portId, kindid, stuffid, productlen, pagenum, 8);
        woodBuyEntityCall.enqueue(new Callback<WoodBuyRsp>() {
            @Override
            public void onResponse(Call<WoodBuyRsp> call, Response<WoodBuyRsp> response) {

                if (response.isSuccessful()) {
                    WoodBuyRsp body = response.body();
                    List<WoodBuyEntity> woodBuyMarkets = body.getWoodBuyMarkets();
                    String pageCount = body.getPageEntity().getPageCount();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = woodBuyMarkets;
                    handler.sendMessage(message);

                    if (isRefresh) {
                        pagenum = 0;
                        woodBuyAdapter.clear();
                        listWoodBuyGoods.setAdapter(woodBuyAdapter);
                        woodBuyAdapter.reset(woodBuyMarkets);
                    } else {
                        if (pagenum <= Integer.valueOf(pageCount)) {
                            listWoodBuyGoods.setAdapter(woodBuyAdapter);
                            woodBuyAdapter.addAll(woodBuyMarkets);
                            pagenum++;
                        }
                    }

                }
                ptrWrapper.stopRefresh();
            }

            @Override
            public void onFailure(Call<WoodBuyRsp> call, Throwable t) {
                ptrWrapper.stopRefresh();
                activityUtils.showToast("请求失败了" + t.getMessage());
            }
        });
    }

    @OnItemClick(R.id.list_wood_buy)
    public void JumpToInfo(int position) {
        WoodBuyEntity woodBuyEntity = woodBuyMarkets.get(position);
        String buyId = woodBuyEntity.getBuyId();
        Intent startIntent = WoodBuyInfoActivity.getStartIntent(WoodBuy.this, buyId);
        startActivity(startIntent);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        woodBuyEntityCall.cancel();
        woodBuyEntityCall = null;
    }

}