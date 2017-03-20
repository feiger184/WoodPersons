package com.jywy.woodpersons.ui.home.railway;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;

import butterknife.BindView;

public class RailwayActivitySecond extends BaseActivity {

    private LineFragment lineFragment;
    private PlaceFragment placeFragment;

    private RadioButton lineshow;
    private RadioButton placeshow;


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_second;
    }

    @Override
    protected void initView() {
        //初始化控件
        initTextview();
        //fragment碎片
        initfragment();
    }


    @BindView(R.id.radio_show)
    RadioGroup radioGroup;

    private void initfragment() {
        lineFragment = new LineFragment();
        placeFragment = new PlaceFragment();

        //1、获取FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        //2、开启事务
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                switch (checkedId) {
                    case R.id.line_show:
                        if (lineFragment == null) {
                            lineFragment = new LineFragment();
                        }
                        lineshow.setTextColor(getResources().getColor(R.color.railway_top_tab_color));
                        placeshow.setTextColor(getResources().getColor(R.color.railway_top_tab_color_2));
                        //3、替换布局
                        fragmentTransaction.replace(R.id.content, lineFragment);
                        //4、提交事务
                        fragmentTransaction.commit();
                        break;
                    case R.id.place_show:
                        if (placeFragment == null) {
                            placeFragment = new PlaceFragment();
                        }
                        placeshow.setTextColor(getResources().getColor(R.color.railway_top_tab_color));
                        lineshow.setTextColor(getResources().getColor(R.color.railway_top_tab_color_2));
                        fragmentTransaction.replace(R.id.content, placeFragment);
                        fragmentTransaction.commit();
                        break;
                }
            }
        });
    }

    private void initTextview() {
        placeshow = (RadioButton) findViewById(R.id.place_show);
        lineshow = (RadioButton) findViewById(R.id.line_show);
//
        //设置默认的fragment
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        lineFragment = new LineFragment();
        transaction.replace(R.id.content, lineFragment);
        transaction.commit();
    }
}
