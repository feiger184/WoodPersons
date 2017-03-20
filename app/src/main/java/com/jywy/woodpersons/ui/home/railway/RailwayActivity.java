package com.jywy.woodpersons.ui.home.railway;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.commons.ActivityUtils;

import butterknife.BindView;

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
        tvNzh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUtils.startActivity(RailwayActivitySecond.class);
            }
        });
    }
}


