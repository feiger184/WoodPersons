package com.jywy.woodpersons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.ui.home.HomeFragment;
import com.jywy.woodpersons.ui.me.MeFragment;
import com.jywy.woodpersons.ui.publish.PublishedFragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.tag;
import static com.jywy.woodpersons.R.id.main_viewPager;

public class MainActivity extends BaseActivity {

    @BindViews({R.id.tv_home, R.id.tv_published, R.id.tv_me})
    TextView[] textviews;
    @BindView(R.id.main_viewPager)
    ViewPager viewpager;

    private boolean isExit = false;
    private HomeFragment mHomeFragment;
    private PublishedFragment publishedFragment;
    private MeFragment meFragment;

    private Fragment mCurrentFragment;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        //刚进来默认选择首页
        textviews[0].setSelected(true);
        viewpager.setAdapter(mainViewPagerAdapter);
        textviews[0].setSelected(true);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (TextView textView : textviews) {
                    textView.setSelected(false);
                }
                // 设置选择效果
                textviews[position].setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //ViewPager适配器
    private FragmentStatePagerAdapter mainViewPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

        private MeFragment meFragment;

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mHomeFragment == null) {
                        mHomeFragment = new HomeFragment();
                    }
                    return mHomeFragment;

                case 1:
                    if (publishedFragment == null) {
                        publishedFragment = new PublishedFragment();
                    }
                    return publishedFragment;

                case 2:
                    if (meFragment == null) {
                        meFragment = new MeFragment();
                    }
                    return meFragment;
                default:
                    throw new UnsupportedOperationException("unsupport");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    @OnClick({R.id.tv_home, R.id.tv_published, R.id.tv_me})
    public void onClick(TextView view) {
        for (int i = 0; i < textviews.length; i++) {
            textviews[i].setSelected(false);
            textviews[i].setTag(i);
        }

        //设置选择效果
        view.setSelected(true);
        //参数false代表瞬间切换，而不是平滑过渡
        viewpager.setCurrentItem((Integer) view.getTag(), false);
        //设置一下toolbar的title
    }

    /*
    * 2秒内双击返回键 退出程序
    * */
    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再摁一次退出程序", Toast.LENGTH_SHORT).show();
            viewpager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }

    }


}
