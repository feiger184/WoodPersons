package com.jywy.woodpersons.ui.home.railway;


import android.widget.ListView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsList;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RailwayTrainListActivity extends BaseActivity {

    @BindView(R.id.list_train_goods)
    ListView trainListView;

    private ActivityUtils activityUtils;
    private PtrWrapper ptrWrapper;
    private RailwayTrainListAdapter trainListAdapter;
    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_train_list;
    }

    @Override
    protected void initView() {
        activityUtils = new ActivityUtils(this);


        // 刷新加载
        ptrWrapper = new PtrWrapper(this, false) {
            @Override
            protected void onRefresh() {
                // 要进行网络请求获取数据
                searchTrain(true);
            }

            @Override
            protected void onLoadMore() {

            }

        };

        // 处理ListView:设置适配器
          trainListAdapter = new RailwayTrainListAdapter();
        // 自动刷新
        ptrWrapper.postRefreshDelayed(50);

    }


    // 网络请求获取数据
    private void searchTrain(final boolean isRefresh) {
        Call<RailwayGoodsListRsp> trainCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsList(1, 1, "03", "2017-02-21", 8);
        trainCall.enqueue(new Callback<RailwayGoodsListRsp>() {
            @Override
            public void onResponse(Call<RailwayGoodsListRsp> call, Response<RailwayGoodsListRsp> response) {

                if (response.isSuccessful()) {
                    RailwayGoodsListRsp body = response.body();
                    List<RailwayGoodsList> railwayGoodsListsData = body.getRailwayGoodsListsData();

                    if (isRefresh) {
                        trainListView.setAdapter(trainListAdapter);
                        trainListAdapter.reset(railwayGoodsListsData);
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

}
