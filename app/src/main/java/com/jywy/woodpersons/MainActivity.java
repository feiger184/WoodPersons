package com.jywy.woodpersons;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.ui.home.HomeFragment;
import com.jywy.woodpersons.ui.me.MeFragment;
import com.jywy.woodpersons.ui.message.MessageFragment;
import com.jywy.woodpersons.ui.publish.PublishedFragment;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;



public class MainActivity extends BaseActivity {

    @BindViews({R.id.tv_home, R.id.tv_message,R.id.tv_published, R.id.tv_me})
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
        //设置自定义通知栏样式1
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
        //指定状态栏的小图标
        builder.statusBarDrawable = R.drawable.egrt;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要

        JPushInterface.setPushNotificationBuilder(0, builder);

        //刚进来默认选择首页
        textviews[0].setSelected(true);
        // 進入系統默認為movie
        fragmentManager =  getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.layout_container,new HomeFragment());
        transaction.commit();
    }




    @OnClick({R.id.tv_home,R.id.tv_message, R.id.tv_published, R.id.tv_me})
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
            case R.id.tv_message:
                transaction.replace(R.id.layout_container,new MessageFragment());
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
