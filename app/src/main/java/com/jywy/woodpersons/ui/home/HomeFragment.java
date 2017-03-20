package com.jywy.woodpersons.ui.home;

import android.graphics.drawable.Drawable;
import android.icu.util.IndianCalendar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseFragment;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.ui.home.railway.RailwayActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by 高 on 2017/3/20.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_viewPager)
    ViewPager viewpager;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    private ActivityUtils activityUtils;
    private List<ImageView> images;
    private HomeViewPagerAdapter viewPagerAdapter;

    private int[] imageID = new int[]{
            R.drawable.meitu1,
            R.drawable.meitu2,
            R.drawable.meitu3,
    };

    private int OldPosition = 0;
    private int currentPosition;
    private ScheduledExecutorService scheduled;



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
        viewPagerAdapter = new HomeViewPagerAdapter(images);
        viewpager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewpager);

        viewPagerAdapter.setListener(new HomeViewPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemListener() {
                activityUtils.showToast("点击了图片");
            }
        });


        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
  }

            @Override
            public void onPageSelected(int position) {
                OldPosition = position;
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        //开启一个单个后台线程
        scheduled = Executors.newSingleThreadScheduledExecutor();

        //给线程添加一个定时的调度任务
        //任务，时间（延迟多少时间后执行任务），时间（按照这个时间周期性重复执行任务）, TimeUnit.SECONDS
        scheduled.scheduleWithFixedDelay(new ImageTask(), 2, 2, TimeUnit.SECONDS);
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



    public class ImageTask implements Runnable {

        @Override
        public void run() {
            //用取余的方式来确认currentItem

            currentPosition = (currentPosition + 1) % imageID.length;
            handler.sendEmptyMessage(0);//就是为了调动下handler，为了更新UI
        }
    }


    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //更新viewpager当前显示的pager

            if (currentPosition ==0) {
                viewpager.setCurrentItem(currentPosition, false);
            } else {
                viewpager.setCurrentItem(currentPosition);
            }
        }
    };


}
