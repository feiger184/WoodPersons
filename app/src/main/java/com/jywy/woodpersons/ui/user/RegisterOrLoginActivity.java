package com.jywy.woodpersons.ui.user;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jywy.woodpersons.MainActivity;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.ui.user.login.LoginActivity;
import com.jywy.woodpersons.ui.user.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterOrLoginActivity extends BaseActivity {


    @BindView(R.id.btn_Register)
    Button btnRegister;
    @BindView(R.id.btn_Login)
    Button btnLogin;
    private ActivityUtils activityUtils;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_register_or_login;
    }

    @Override
    protected void initView() {
        activityUtils = new ActivityUtils(this);

        // 判断一下用户是不是登录过
        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
        if (preferences!=null){
            if (preferences.getInt("key_userid",0)== UserPrefs.getInstance().getUserid()){
                activityUtils.startActivity(MainActivity.class);
                finish();
            }
        }
    }

    // 跳转
    @OnClick({R.id.btn_Register, R.id.btn_Login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Register:
                activityUtils.startActivity(RegisterActivity.class);
                finish();
                break;
            case R.id.btn_Login:
                activityUtils.startActivity(LoginActivity.class);
                finish();
                break;
        }
    }


}
