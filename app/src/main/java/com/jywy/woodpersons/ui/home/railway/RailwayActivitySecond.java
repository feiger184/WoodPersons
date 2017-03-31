package com.jywy.woodpersons.ui.home.railway;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoods;
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


    @BindViews({R.id.line_show, R.id.place_show})
    List<TextView> mTvOrderList;
    @BindView(R.id.list_goods)
    ListView mGoodsListView;

    @BindView(R.id.standard_toolbar)
    Toolbar toolbar;

    @BindView(R.id.standard_toolbar_title)
    TextView toolbarText;

    private ActivityUtils activityUtils;
    private PtrWrapper ptrWrapper;
    private RailwayActivitySecondAdapter railwayListAdapter;
    private long mLastRefreshTime;
    private RailwayPlaceAdapter railwayPlaceAdapter;
    private List<RailwayGoods> railwayGoodsesData;
    private Call<RailwayGoodsRsp> rspCall;


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_second;
    }

    @Override
    protected void initView() {
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
                searchGoods(true);
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
        rspCall.cancel();
        // 跳转到详情页
        String trainDate = railwayGoodsesData.get(position).getTrainDate();
        String trainNum = railwayGoodsesData.get(position).getTrainNum();

        Intent intent = RailwayTrainListActivity.getStartIntent(RailwayActivitySecond.this, trainNum, trainDate);
        startActivity(intent);

    }

    @OnClick({R.id.line_show, R.id.place_show})
    public void chooseGoodsOrder(View view) {

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
                searchGoods(true);
                break;
            case R.id.place_show:
                activityUtils.showToast("待实现。。。");
                break;

            default:
                throw new UnsupportedOperationException();
        }

        // 简单解决切换过快的问题
        long time = 2000 + mLastRefreshTime - System.currentTimeMillis();
        time = time < 0 ? 0 : time;
        ptrWrapper.postRefreshDelayed(time);
    }


    // 网络请求获取数据
    private void searchGoods(final boolean isRefresh) {
        if (isRefresh) {
            mLastRefreshTime = System.currentTimeMillis();

        }
        if (rspCall != null) {
            rspCall.cancel();
        }
        // 请求
        rspCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayData(1, 8);
        rspCall.enqueue(new Callback<RailwayGoodsRsp>() {
            @Override
            public void onResponse(Call<RailwayGoodsRsp> call, Response<RailwayGoodsRsp> response) {
                if (response.isSuccessful()) {
                    RailwayGoodsRsp body = response.body();
                    railwayGoodsesData = body.getRailwayGoodsesData();

                    if (isRefresh) {
                        railwayPlaceAdapter.clear();
                        mGoodsListView.setAdapter(railwayListAdapter);
                        railwayListAdapter.reset(railwayGoodsesData);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rspCall.cancel();
        rspCall = null;
    }

}
