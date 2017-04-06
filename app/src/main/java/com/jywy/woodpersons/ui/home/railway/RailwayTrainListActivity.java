package com.jywy.woodpersons.ui.home.railway;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.jywy.woodpersons.MainActivity;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jywy.woodpersons.R.id.standard_toolbar;


public class RailwayTrainListActivity extends BaseActivity {

    @BindView(R.id.list_train_goods)
    ListView trainListView;

    @BindView(R.id.standard_toolbar)
    Toolbar toolbar;

    @BindView(R.id.standard_toolbar_title)
    TextView toolbarText;



    private ActivityUtils activityUtils;
    private PtrWrapper ptrWrapper;
    private RailwayTrainListAdapter trainListAdapter;

    private static final String TRAIN = "TRAIN";
    private static final String TIME_DATE = "TIME_DATE";
    private String timeDate;
    private String train;
    private List<RailwayGoodsListRsp.DataBean> dataX;
    private Call<RailwayGoodsListRsp> trainCall;


    // 因为需要传递数据，为了规范我们传递的数据内容，所以我们在此页面对外提供一个跳转的方法
    public static Intent getStartIntent(Context context, String train, String timedate) {

        Intent intent = new Intent(context, RailwayTrainListActivity.class);
        intent.putExtra(TRAIN, train);
        intent.putExtra(TIME_DATE, timedate);
        return intent;
    }
    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_train_list;
    }

    @Override
    protected void initView() {

        new ToolbarWrapper(this)
                .setShowBack(true);
        setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("");
        activityUtils = new ActivityUtils(this);
        // 取出传递的数据
        timeDate = getIntent().getStringExtra(TIME_DATE);
        train = getIntent().getStringExtra(TRAIN);
        toolbarText.setText("满洲里第"+train+"列到货列表");
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



    @OnItemClick(R.id.list_train_goods)
    public void GoToGoodsInfo(int position) {
        RailwayGoodsListRsp.DataBean dataBean = dataX.get(position);
        String cdkey = dataBean.getCdkey();
        Intent intent = RailwayGoodsInfoActivity.getStartIntent(RailwayTrainListActivity.this, cdkey);
        startActivity(intent);
    }


    // 网络请求获取数据
    private void searchTrain(final boolean isRefresh) {

        trainCall = WoodPersonsClient.getInstance().getWoodPersonsApi()
                .getRailwayGoodsList(1, 1, train, timeDate, 8);
        trainCall.enqueue(new Callback<RailwayGoodsListRsp>() {

            @Override
            public void onResponse(Call<RailwayGoodsListRsp> call, Response<RailwayGoodsListRsp> response) {

                if (response.isSuccessful()) {
                    RailwayGoodsListRsp body = response.body();
                    dataX = body.getDataX();

                    if (isRefresh) {
                        trainListView.setAdapter(trainListAdapter);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        trainCall.cancel();
        trainCall = null;
    }
}
