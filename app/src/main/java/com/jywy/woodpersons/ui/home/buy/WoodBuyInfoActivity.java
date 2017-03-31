package com.jywy.woodpersons.ui.home.buy;

import android.content.Context;
import android.content.Intent;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.ui.home.railway.RailwayGoodsInfoActivity;

public class WoodBuyInfoActivity extends BaseActivity {


    private static final String BUY_ID = "BUY_ID";

    // 因为需要传递数据，为了规范我们传递的数据内容，所以我们在此页面对外提供一个跳转的方法
    public static Intent getStartIntent(Context context, String buyID) {

        Intent intent = new Intent(context, WoodBuyInfoActivity.class);
        intent.putExtra(BUY_ID, buyID);
        return intent;
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_wood_buy_info;
    }

    @Override
    protected void initView() {

    }
}
