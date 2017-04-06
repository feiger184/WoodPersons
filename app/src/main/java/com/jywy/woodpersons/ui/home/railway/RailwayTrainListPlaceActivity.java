package com.jywy.woodpersons.ui.home.railway;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jywy.woodpersons.R.id.train;

public class RailwayTrainListPlaceActivity extends BaseActivity {

    private static final String PORT_ID = "PORT_ID";
    private static final String DISPLAYSIGN = "DISPLAYSIGN";
    private static final String SPOTPOSITIONID = "SPOTPOSITIONID";
    private static final String POSITIONNAME = "POSITIONNAME";
    @BindView(R.id.list_place_goods)
    ListView listpPlaceGoods;
    @BindView(R.id.standard_toolbar)
    Toolbar toolbar;
    @BindView(R.id.standard_toolbar_title)
    TextView toolbarText;


    private ActivityUtils activityUtils;
    private PtrWrapper ptrWrapper;
    private int portid;
    private int displaysign;
    private int spotPositionId;
    private String positionName;
    private Call<RailwayGoodsListRsp> placeListCall;
    private RailwayTrainListPlaceAdapter trainListAdapter;
    private List<RailwayGoodsListRsp.DataBean> dataX;

    // 因为需要传递数据，为了规范我们传递的数据内容，所以我们在此页面对外提供一个跳转的方法
    public static Intent getStartIntent(Context context, int portid, String displaySign, String spotPositionId, String positionName) {
        Intent intent = new Intent(context, RailwayTrainListPlaceActivity.class);
        intent.putExtra(PORT_ID, portid);
        intent.putExtra(DISPLAYSIGN, displaySign);
        intent.putExtra(SPOTPOSITIONID, spotPositionId);
        intent.putExtra(POSITIONNAME, positionName);
        return intent;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dataX = (List<RailwayGoodsListRsp.DataBean>) msg.obj;
            }
        }
    };

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_train_list_place;
    }

    @Override
    protected void initView() {

        new ToolbarWrapper(this)
                .setShowBack(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        activityUtils = new ActivityUtils(this);

        // 取出传递的数据
        portid = getIntent().getIntExtra(PORT_ID, 1);
        displaysign = Integer.valueOf(getIntent().getStringExtra(DISPLAYSIGN));
        spotPositionId = Integer.valueOf(getIntent().getStringExtra(SPOTPOSITIONID));
        positionName = getIntent().getStringExtra(POSITIONNAME);
        toolbarText.setText(positionName+ "—到货列表");
        // 刷新加载
        ptrWrapper = new PtrWrapper(this, false) {
            @Override
            protected void onRefresh() {
                // 要进行网络请求获取数据
                getRailwayPlaceListData(true);
            }
            @Override
            protected void onLoadMore() {

            }
        };

        // 处理ListView:设置适配器
        trainListAdapter = new RailwayTrainListPlaceAdapter();
        // 自动刷新
        ptrWrapper.postRefreshDelayed(50);


    }

    private void getRailwayPlaceListData(final boolean isRefresh) {

        placeListCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsListPlace(0, displaysign, portid, spotPositionId, UserPrefs.getInstance().getUserid());
        placeListCall.enqueue(new Callback<RailwayGoodsListRsp>() {
            @Override
            public void onResponse(Call<RailwayGoodsListRsp> call, Response<RailwayGoodsListRsp> response) {
                if (response.isSuccessful()) {
                    RailwayGoodsListRsp body = response.body();
                    List<RailwayGoodsListRsp.DataBean> dataX = body.getDataX();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = dataX;
                    handler.sendMessage(message);

                    if (isRefresh) {
                        listpPlaceGoods.setAdapter(trainListAdapter);
                        trainListAdapter.reset(dataX);
                    }
                }
                ptrWrapper.stopRefresh();
            }

            @Override
            public void onFailure(Call<RailwayGoodsListRsp> call, Throwable t) {
                ptrWrapper.stopRefresh();
                activityUtils.showToast(t.getMessage());
            }
        });
    }

    @OnItemClick(R.id.list_place_goods)
    public void jumpToInfo(int position) {
        String cdkey = dataX.get(position).getCdkey();
        Intent intent = RailwayGoodsInfoActivity.getStartIntent(RailwayTrainListPlaceActivity.this, cdkey);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        placeListCall.cancel();
        placeListCall = null;
    }
}
