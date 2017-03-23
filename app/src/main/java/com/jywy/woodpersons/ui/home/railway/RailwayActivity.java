package com.jywy.woodpersons.ui.home.railway;


import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfoRsp;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class RailwayActivity extends BaseActivity {

    @BindView(R.id.tv_text_nzh)
    TextView tvNzh;
    private ActivityUtils activityUtils;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway;
    }

    @Override
    protected void initView() {

        //设置标题
        new ToolbarWrapper(this)
                .setShowBack(true)
                .setCustomTitle(R.string.Railway_goods);

        activityUtils = new ActivityUtils(this);
    }

    @OnClick({R.id.tv_text_nzh, R.id.tv_text_erenhot, R.id.tv_text_suifenhe})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_text_nzh:
                activityUtils.startActivity(RailwayActivitySecond.class);
                break;
            case R.id.tv_text_erenhot:
                Call<RailwayGoodsInfoRsp> railwayGoodsInfo = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayGoodsInfo("1334704115117021920190140133199381", 8);
                try {
                    railwayGoodsInfo.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.tv_text_suifenhe:
                activityUtils.showToast("待实现。。。");
                break;
        }
    }
}


