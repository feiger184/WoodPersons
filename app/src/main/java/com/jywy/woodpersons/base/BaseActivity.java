package com.jywy.woodpersons.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jywy.woodpersons.MyApp;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.commons.NetUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

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
        MyApp app = (MyApp) getApplication();
        //将每一个Activity都加入一个集合,用于程序退出
        app.addActivity(this);
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



   /* //基类中提供一个请求的方法
    protected Call enqueue(final ApiInterface apiInterface) {
        UICallback uiCallback=new UICallback() {
            @Override
            public void onBusinessResponse(boolean isSucces, ResponseEntity responseEntity) {
                BaseActivity.this.onBusinessResponse(apiInterface.getPath(), isSucces, responseEntity);
            }
        };
        return EShopClient.getInstance().enqueue(apiInterface, uiCallback, getClass().getSimpleName());
    }
    protected abstract void onBusinessResponse(String path,boolean isSucces, ResponseEntity responseEntity);
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EShopClient.getInstance().CancelByTag(getClass().getSimpleName());
        unbinder.unbind();
        unbinder = null;
    }
}
