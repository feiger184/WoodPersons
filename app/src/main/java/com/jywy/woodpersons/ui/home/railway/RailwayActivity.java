package com.jywy.woodpersons.ui.home.railway;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        //设置标题
        new ToolbarWrapper(this)
                .setShowBack(true)
                .setCustomTitle(R.string.Railway_goods);

        activityUtils = new ActivityUtils(this);

        initDrawable();
    }

    private void initDrawable() {
        TextView tv_text_nzh = ButterKnife.findById(this, R.id.tv_text_nzh);
        TextView tv_text_erenhot = ButterKnife.findById(this, R.id.tv_text_erenhot);
        TextView tv_text_suifenhe = ButterKnife.findById(this, R.id.tv_text_suifenhe);

        Drawable dw_text_nzh=getResources().getDrawable(R.drawable.nzh);
        Drawable dw_text_erenhot=getResources().getDrawable(R.drawable.erenhot);
        Drawable dw_text_suifenhe=getResources().getDrawable(R.drawable.suifenhe);

        dw_text_nzh.setBounds(0, 0, 180, 180);
        tv_text_nzh.setCompoundDrawables(null, dw_text_nzh, null, null);//只放上边
        dw_text_erenhot.setBounds(0, 0, 180, 180);
        tv_text_erenhot.setCompoundDrawables(null, dw_text_erenhot, null, null);//只放上边
        dw_text_suifenhe.setBounds(0, 0, 180, 180);
        tv_text_suifenhe.setCompoundDrawables(null, dw_text_suifenhe, null, null);//只放上边
    }

    @OnClick({R.id.tv_text_nzh, R.id.tv_text_erenhot, R.id.tv_text_suifenhe})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_text_nzh:
                activityUtils.startActivity(RailwayActivitySecond.class);
                break;
            case R.id.tv_text_erenhot:
                activityUtils.showToast("待实现。。。");

                break;

            case R.id.tv_text_suifenhe:
                activityUtils.showToast("待实现。。。");
                break;
        }
    }
}


