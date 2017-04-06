package com.jywy.woodpersons.ui.home.railway;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoods;
import com.jywy.woodpersons.network.entity.RailwayGoodsPlace;
import com.jywy.woodpersons.network.entity.RailwayGoodsPlaceRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsRsp;


import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RailwayActivitySecond extends BaseActivity {
    private static final String PORT_ID = "PORT_ID";
    @BindViews({R.id.line_show, R.id.place_show})
    List<TextView> mTvOrderList;
    @BindView(R.id.list_goods)
    ListView mGoodsListView;
    @BindView(R.id.layout_left)
    View layoutLeft;
    @BindView(R.id.layout_right)
    View layoutRight;

    @BindView(R.id.standard_toolbar)
    Toolbar toolbar;

    @BindView(R.id.standard_toolbar_title)
    TextView toolbarText;
    private int currentPositon = 0;
    private ActivityUtils activityUtils;
    private PtrWrapper ptrWrapper;
    private RailwayActivitySecondAdapter railwayListAdapter;
    private long mLastRefreshTime;
    private RailwayPlaceAdapter railwayPlaceAdapter;
    private List<RailwayGoods> railwayGoodsesData;
    private Call<RailwayGoodsRsp> rspCall;
    private Call<RailwayGoodsPlaceRsp> placeRspCall;

    private int portid;
    private List<RailwayGoodsPlace> railwayGoodsPlaces;

    // 因为需要传递数据，为了规范我们传递的数据内容，所以我们在此页面对外提供一个跳转的方法
    public static Intent getStartIntent(Context context, int portid) {

        Intent intent = new Intent(context, RailwayActivitySecond.class);
        intent.putExtra(PORT_ID, portid);
        return intent;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                railwayGoodsesData = (List<RailwayGoods>) msg.obj;
            }

            if (msg.what == 2) {
                railwayGoodsPlaces = (List<RailwayGoodsPlace>) msg.obj;

            }

        }
    };

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_second;
    }

    @Override
    protected void initView() {
        portid = getIntent().getIntExtra(PORT_ID, 1);
        //设置toolbar
        new ToolbarWrapper(this).setShowBack(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarText.setText("到货列表——满洲里");
        activityUtils = new ActivityUtils(this);
        mTvOrderList.get(0).setActivated(true);


        // 刷新加载
        ptrWrapper = new PtrWrapper(this, false) {
            @Override
            protected void onRefresh() {
                // 要进行网络请求获取数据
                if (currentPositon == 0) {
                    getRailwayListDatas(true);
                } else {
                    getRailwayPlaceDatas(true);
                }
            }

            @Override
            protected void onLoadMore() {

            }
        };

        // 处理ListView:设置适配器
        railwayListAdapter = new RailwayActivitySecondAdapter();
        railwayPlaceAdapter = new RailwayPlaceAdapter();
        // 自动刷新
        ptrWrapper.postRefreshDelayed(50);
    }


    @OnItemClick(R.id.list_goods)
    public void goodsItemClick(int position) {
        if (rspCall != null || placeRspCall != null) {
            rspCall.cancel();
        }
        if (currentPositon == 0) {
            // 跳转到详情页
            String trainDate = railwayGoodsesData.get(position).getTrainDate();
            String trainNum = railwayGoodsesData.get(position).getTrainNum();
            Intent intent = RailwayTrainListActivity.getStartIntent(RailwayActivitySecond.this, portid, trainNum, trainDate, 1);
            startActivity(intent);
        }
        if (currentPositon == 1) {

            String displaySign = railwayGoodsPlaces.get(position).getDisplaySign();
            String spotPositionId = railwayGoodsPlaces.get(position).getSpotPositionId();
            String positionName = railwayGoodsPlaces.get(position).getPositionName();
            Intent startIntent = RailwayTrainListPlaceActivity.getStartIntent(RailwayActivitySecond.this, portid, displaySign, spotPositionId,positionName);
            startActivity(startIntent);

        }

    }

    //选择排列方式
    @OnClick({R.id.line_show, R.id.place_show})
    public void chooseGoods(View view) {

        // 如果当前已经是此项，就不触发
        if (view.isActivated()) return;

        // 如果在刷新，不去执行
        if (ptrWrapper.isRefreshing()) return;

        // 二个都不是活动状态
        for (TextView sortView : mTvOrderList) {
            sortView.setActivated(false);
        }
        // 选择的某项设置为Activated
        view.setActivated(true);
        switch (view.getId()) {
            case R.id.line_show:
                currentPositon = 0;
                mTvOrderList.get(0).setTextColor(getResources().getColor(R.color.unsold_tab_textcolor));
                layoutLeft.setBackgroundColor(getResources().getColor(R.color.unsold_tab_textcolor));
                mTvOrderList.get(1).setTextColor(getResources().getColor(R.color.railway_top_tab_textcolor));
                layoutRight.setBackgroundColor(getResources().getColor(R.color.white));
                getRailwayListDatas(true);
                break;
            case R.id.place_show:
                currentPositon = 1;
                mTvOrderList.get(0).setTextColor(getResources().getColor(R.color.railway_top_tab_textcolor));
                layoutLeft.setBackgroundColor(getResources().getColor(R.color.white));
                mTvOrderList.get(1).setTextColor(getResources().getColor(R.color.unsold_tab_textcolor));
                layoutRight.setBackgroundColor(getResources().getColor(R.color.unsold_tab_textcolor));

                getRailwayPlaceDatas(true);
                break;

            default:
                throw new UnsupportedOperationException();
        }

        // 简单解决切换过快的问题
        long time = 2000 + mLastRefreshTime - System.currentTimeMillis();
        time = time < 0 ? 0 : time;
        ptrWrapper.postRefreshDelayed(time);
    }

    // 网络请求获取按列排列数据
    private void getRailwayListDatas(final boolean isRefresh) {

        if (isRefresh) {
            mLastRefreshTime = System.currentTimeMillis();
        }
        if (rspCall != null) {
            rspCall.cancel();
        }
        // 请求
        rspCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayData(portid, UserPrefs.getInstance().getUserid());
        rspCall.enqueue(new Callback<RailwayGoodsRsp>() {
            @Override
            public void onResponse(Call<RailwayGoodsRsp> call, Response<RailwayGoodsRsp> response) {
                if (response.isSuccessful()) {
                    RailwayGoodsRsp body = response.body();
                    List<RailwayGoods> railwayGoodsesListData = body.getRailwayGoodsesData();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = railwayGoodsesListData;
                    handler.sendMessage(message);

                    if (isRefresh) {
                        railwayListAdapter.clear();
                        mGoodsListView.setAdapter(railwayListAdapter);
                        railwayListAdapter.reset(railwayGoodsesListData);
                        railwayListAdapter.notifyDataSetChanged();
                    }

                }
                ptrWrapper.stopRefresh();

            }

            @Override
            public void onFailure(Call<RailwayGoodsRsp> call, Throwable t) {
                ptrWrapper.stopRefresh();
                activityUtils.showToast(t.getMessage());
            }
        });
    }

    // 网络请求获取位置排列数据
    private void getRailwayPlaceDatas(boolean isRefresh) {

        if (isRefresh) {
            mLastRefreshTime = System.currentTimeMillis();
        }
        if (placeRspCall != null) {
            placeRspCall.cancel();
        }
        placeRspCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayPlace(portid, UserPrefs.getInstance().getUserid());
        placeRspCall.enqueue(new Callback<RailwayGoodsPlaceRsp>() {
            @Override
            public void onResponse(Call<RailwayGoodsPlaceRsp> call, Response<RailwayGoodsPlaceRsp> response) {
                if (response.isSuccessful()) {
                    RailwayGoodsPlaceRsp body = response.body();
                    List<RailwayGoodsPlace> railwayGoodsPlaces = body.getRailwayGoodsPlaces();
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = railwayGoodsPlaces;
                    handler.sendMessage(message);
                    railwayPlaceAdapter.clear();
                    mGoodsListView.setAdapter(railwayPlaceAdapter);
                    railwayPlaceAdapter.reset(railwayGoodsPlaces);
                    railwayPlaceAdapter.notifyDataSetChanged();
                }

                ptrWrapper.stopRefresh();
            }

            @Override
            public void onFailure(Call<RailwayGoodsPlaceRsp> call, Throwable t) {
                ptrWrapper.stopRefresh();
                activityUtils.showToast(t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rspCall.cancel();
        rspCall = null;
        if (placeRspCall != null) {
            placeRspCall.cancel();
            placeRspCall = null;
        }
    }

}
