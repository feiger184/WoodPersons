package com.jywy.woodpersons.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseFragment;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.ui.home.railway.RailwayActivity;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by 高 on 2017/3/20.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_viewPager)
    ViewPager viewPager;

    private ActivityUtils activityUtils;
    private List<ImageView> images;


    private int[] imageID = new int[]{
            R.drawable.meitu1,
            R.drawable.meitu2,
            R.drawable.meitu3,
    };
    private HomeViewPagerAdapter homeViewPagerAdapter;

    @Override
    protected int geContentViewLayout() {
        return R.layout.fragment_home;
    }

    /*
        * 初始化视图
        * */
    protected void initView() {
        activityUtils = new ActivityUtils(this);
        //显示图片的集合
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageID.length; i++) {
            ImageView imageview = new ImageView(getContext());
            imageview.setBackgroundResource(imageID[i]);
            images.add(imageview);
        }

        homeViewPagerAdapter = new HomeViewPagerAdapter();
        viewPager.setAdapter(homeViewPagerAdapter);
        homeViewPagerAdapter.setImageToAdapter(images);

        homeViewPagerAdapter.setListener(new HomeViewPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemListener() {
                activityUtils.showToast("点击了图片");
            }
        });

    }

    //8个按钮的点击事件
    @OnClick({R.id.tv_Railway_goods, R.id.tv_find_goods, R.id.tv_wood_info, R.id.tv_volume_calculate,
            R.id.tv_unsold_market, R.id.tv_wood_find, R.id.tv_nice_shops, R.id.tv_more_functions})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Railway_goods:
                activityUtils.startActivity(RailwayActivity.class);
                break;
            case R.id.tv_find_goods:
                activityUtils.showToast("tv_find_goods");
                break;
            case R.id.tv_wood_info:
                activityUtils.showToast("tv_wood_info");
                break;
            case R.id.tv_volume_calculate:
                activityUtils.showToast("tv_volume_calculate");

                break;
            case R.id.tv_unsold_market:
                activityUtils.showToast("tv_unsold_market");
                break;
            case R.id.tv_wood_find:
                activityUtils.showToast("tv_wood_find");
                break;
            case R.id.tv_nice_shops:
                activityUtils.showToast("tv_nice_shops");
                break;
            case R.id.tv_more_functions:
                activityUtils.showToast("tv_more_functions");
                break;
            default:
                break;
        }
    }

}
