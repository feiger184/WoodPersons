package com.jywy.woodpersons.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.jywy.woodpersons.MyApp;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 高 on 2017/3/20.
 */

public abstract class BaseActivity extends TransitionActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        unbinder = ButterKnife.bind(this);

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
