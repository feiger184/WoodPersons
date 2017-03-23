package com.jywy.woodpersons.ui.home;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseFragment;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.ui.home.railway.RailwayActivity;

import butterknife.OnClick;
/**
 * Created by 高 on 2017/3/20.
 */

public class HomeFragment extends BaseFragment {


    private ActivityUtils activityUtils;

    @Override
    protected int geContentViewLayout() {
        return R.layout.index_test;
    }

    /*
        * 初始化视图
        * */
    protected void initView() {
        activityUtils = new ActivityUtils(this);

    }

    //8个按钮的点击事件
    @OnClick(R.id.tv_Railway_goods)
    public void onClick() {
                activityUtils.startActivity(RailwayActivity.class);
        }
    }

