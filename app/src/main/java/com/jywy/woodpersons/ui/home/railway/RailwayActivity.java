package com.jywy.woodpersons.ui.home.railway;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.commons.ActivityUtils;


import butterknife.BindView;
import butterknife.OnClick;


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
        activityUtils = new ActivityUtils(this);

    }

    @OnClick({R.id.tv_text_nzh, R.id.tv_text_erenhot, R.id.tv_text_suifenhe})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_text_nzh:
                Log.e("==================", "onclick: " );
                activityUtils.startActivity(RailwayActivitySecond.class);

                break;

            case R.id.tv_text_erenhot:
                break;

            case R.id.tv_text_suifenhe:
                break;
        }
    }
}


