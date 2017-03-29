package com.jywy.woodpersons.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.jywy.woodpersons.MainActivity;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.commons.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 高 on 2017/3/20.
 */

public abstract class BaseActivity extends TransitionActivity {

    private Unbinder unbinder;
    private ActivityUtils activityUtils;
//    private Window mWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();
        setContentView(getContentViewLayout());
        unbinder = ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);

//        //用于网络监测
//        if(!NetUtils.isConnected(BaseActivity.this)){
//            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
//            builder.setIcon(R.mipmap.ic_launcher);
//            builder.setTitle("提示");
//            builder.setMessage("当前没有可用网络，是否进入网络设置界面");
//            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    NetUtils.openSetting(BaseActivity.this);
//                }
//            });
//            builder.setPositiveButton("取消",null);
//            builder.create().show();
//        }
        initView();
    }

//    private void init() {
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mWindow = getWindow();
//            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            mWindow.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            mWindow.setStatusBarColor(Color.TRANSPARENT);
//            mWindow.setNavigationBarColor(Color.TRANSPARENT);
//        }
//    }
    //右上角按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_home) {
            activityUtils.startActivity(MainActivity.class);

        }
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return false;
    }

    protected abstract void initView();

    //让子类告诉我们布局
    @LayoutRes
    protected abstract int getContentViewLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        WoodPersonsClient.getInstance().CancelByTag(getClass().getSimpleName());
        unbinder.unbind();
        unbinder = null;
    }
}
