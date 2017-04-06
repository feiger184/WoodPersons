package com.jywy.woodpersons.ui.user.login;

import android.icu.text.BreakIterator;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jywy.woodpersons.MainActivity;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.commons.MD5Utils;
import com.jywy.woodpersons.commons.RegexUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.UserLoginRsp;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 高 on 2017/4/5.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_login)
    EditText etLogin;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_loginIn)
    Button btnLoginIn;
    private String password;
    private String etUserPhone;
    private ActivityUtils activityUtils;


    @Override
    protected int getContentViewLayout() {
        return R.layout.login;
    }

    @Override
    protected void initView() {
        activityUtils = new ActivityUtils(this);
    }

    private boolean isvalidate() {
        // EditText 的输入监听，监听文本的变化
        etUserPhone = etLogin.getText().toString();
        password = etPassword.getText().toString();

        if (StringUtils.isEmpty(etUserPhone)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!RegexUtils.isPhoneNumberValid(etUserPhone)) {
            Toast.makeText(this, "手机号有误", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (StringUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick(R.id.btn_loginIn)
    public void loginin() {
        if (isvalidate()) {
            btnLoginIn.setEnabled(true);
            String encode = MD5Utils.encode(password);
            getLoginInData(encode);
        }
    }

    private void getLoginInData(String encode) {
        Call<UserLoginRsp> loginInfoCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getLoginInfo(etUserPhone, encode);
        loginInfoCall.enqueue(new Callback<UserLoginRsp>() {
            @Override
            public void onResponse(Call<UserLoginRsp> call, Response<UserLoginRsp> response) {
                if (response.isSuccessful()) {

                    UserLoginRsp body = response.body();
                    String userId = body.getUserLoginEntity().getUserId();
                    //保存登陆状态
                    UserPrefs.getInstance().setUserid(Integer.valueOf(userId));
                    activityUtils.showToast("登录成功");
                    activityUtils.startActivity(MainActivity.class);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<UserLoginRsp> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "登录失败了，账号 或密码错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
