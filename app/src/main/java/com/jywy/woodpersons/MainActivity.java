package com.jywy.woodpersons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;

import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.ui.home.HomeFragment;
import com.jywy.woodpersons.ui.me.MeFragment;
import com.jywy.woodpersons.ui.publish.PublishedFragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindViews({R.id.tv_home, R.id.tv_published, R.id.tv_me})
    TextView[] textviews;

    private boolean isExit = false;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        //刚进来默认选择首页
        textviews[0].setSelected(true);
        // 進入系統默認為movie
        fragmentManager =  getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.layout_container,new HomeFragment());
        transaction.commit();

    }


    @OnClick({R.id.tv_home, R.id.tv_published, R.id.tv_me})
    public void onClick(TextView view) {
        for (int i = 0; i < textviews.length; i++) {
            textviews[i].setSelected(false);
        }
        switch (view.getId()) {

        }
        //设置选择效果
        view.setSelected(true);

        fragmentManager =  getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.tv_home:
                transaction.replace(R.id.layout_container,new HomeFragment());
                break;
            case R.id.tv_published:
                transaction.replace(R.id.layout_container,new PublishedFragment());
                break;
            case R.id.tv_me:
                transaction.replace(R.id.layout_container,new MeFragment());
                break;
        }

        // 不要忘记提交
        transaction.commit();
    }

    /*
    * 2秒内双击返回键 退出程序
    * */
    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再摁一次退出程序", Toast.LENGTH_SHORT).show();
//            viewpager.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    isExit = false;
//                }
//            }, 2000);
        } else {
            finish();
        }

    }


}
