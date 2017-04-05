package com.jywy.woodpersons;

import android.app.Notification;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.soft_update.AppVersion;
import com.jywy.woodpersons.soft_update.UnpdateMessage;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @BindViews({R.id.tv_home, R.id.tv_message, R.id.tv_published, R.id.tv_me})
    TextView[] textviews;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    UnpdateMessage mUnpdateMessage;

    private Unbinder unbinder;
    private ActivityUtils activityUtils;
    private Window mWindow;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                int mServiceVersion = (int) msg.obj;
                //版本升级
                unpdate(mServiceVersion);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Fresco.initialize(MainActivity.this);
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);

        initView();

    }

    private void init() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWindow = getWindow();
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            mWindow.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mWindow.setStatusBarColor(Color.TRANSPARENT);
            mWindow.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    private void initView() {
        //设置自定义通知栏样式1
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
        //指定状态栏的小图标
        builder.statusBarDrawable = R.drawable.egrt;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要

        JPushInterface.setPushNotificationBuilder(1, builder);

        //刚进来默认选择首页
        textviews[0].setSelected(true);
        // 進入系統默認為movie
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.layout_container, new HomeFragment());
        transaction.commit();
        getVersionData();

    }

    private void getVersionData() {
        final Call<AppVersion> appVersionCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getAppVersion();
        appVersionCall.enqueue(new Callback<AppVersion>() {
            @Override
            public void onResponse(Call<AppVersion> call, Response<AppVersion> response) {
                if (response.isSuccessful()) {
                    AppVersion body = response.body();
                    int serverVersion = body.getServerVersion();
                    String updateUrl = body.getUpdateUrl();
                    Message message = mHandler.obtainMessage();
                    message.what = 1;
                    message.obj = serverVersion;
                    mHandler.sendMessage(message);

                }
            }

            @Override
            public void onFailure(Call<AppVersion> call, Throwable t) {
                activityUtils.showToast("请求失败了" + t.getMessage());
            }
        });
    }

    //APP版本升级
    private void unpdate(int serviceVersion) {


        int localityAPK = getAppCode();//当前的PK信息
        if (serviceVersion > localityAPK) {
            mUnpdateMessage = new UnpdateMessage(this);
            mUnpdateMessage.checkUpdateInfo();
        } else {
            Toast.makeText(this, "欢迎使用", Toast.LENGTH_LONG).show();
        }
    }

    //获取软件版本号（code）
    private int getAppCode() {
        int versionCode = -1;
        try {
            String pkName = this.getPackageName();
            versionCode = this.getPackageManager().getPackageInfo(pkName, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    //获取软件版本名称
    private String getAppInfo() {
        String pkName = this.getPackageName();
        try {
            String versionName = this.getPackageManager().getPackageInfo(pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @OnClick({R.id.tv_home, R.id.tv_message, R.id.tv_published, R.id.tv_me})
    public void onClick(TextView view) {
        for (int i = 0; i < textviews.length; i++) {
            textviews[i].setSelected(false);
        }
        switch (view.getId()) {

        }
        //设置选择效果
        view.setSelected(true);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.tv_home:
                transaction.replace(R.id.layout_container, new HomeFragment());
                break;
            case R.id.tv_message:
                transaction.replace(R.id.layout_container, new MessageFragment());
                break;
            case R.id.tv_published:
                transaction.replace(R.id.layout_container, new PublishedFragment());
                break;
            case R.id.tv_me:
                transaction.replace(R.id.layout_container, new MeFragment());
                break;
        }

        // 不要忘记提交
        transaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        unbinder = null;
    }

}
