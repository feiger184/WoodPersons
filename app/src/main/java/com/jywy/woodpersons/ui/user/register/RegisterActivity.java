package com.jywy.woodpersons.ui.user.register;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jywy.woodpersons.MainActivity;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.commons.CodeUtils;
import com.jywy.woodpersons.commons.RegexUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.UserRegisterCodeRsp;
import com.jywy.woodpersons.network.entity.UserRegisterSuccessRsp;

import org.apache.commons.lang3.StringUtils;


import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cn.jpush.android.d.f;


public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_register_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_register_invalicode)
    EditText etRegisterInvalicode;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.btn_send_code)
    Button btnSendCode;
    @BindView(R.id.img_yanzheng)
    ImageView imgYanzheng;
    @BindView(R.id.btn_registerButton)
    Button btnRegister;
    private Thread thread = null;
    private boolean tag = true;
    private int i = 60;
    private String tuPianCode;
    private boolean changed = false;
    private ProgressDialog dialog;
    private String phoneNumber;
    /**
     * 客户端输入的验证码
     */
    private String valicationCode;
    /**
     * 服务器端获取的验证码
     */
    private static String serverValicationCode;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                serverValicationCode = (String) msg.obj;
                btnRegister.setEnabled(true);
            }

            if (msg.what == 2) {
                int userId = (int) msg.obj;
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                changed = true;
            }
        }
    };

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        imgYanzheng.setImageBitmap(bitmap);
        tuPianCode = CodeUtils.getInstance().getTuPianCode();
    }

    @OnClick(R.id.img_yanzheng)
    public void getcodw() {
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        imgYanzheng.setImageBitmap(bitmap);
        tuPianCode = CodeUtils.getInstance().getTuPianCode();
    }

    @Override
    protected void initView() {
    }

    private boolean isvalidate() {
        // EditText 的输入监听，监听文本的变化
        String phonenumber = etPhoneNumber.getText().toString().trim();
        String invalicode = etRegisterInvalicode.getText().toString().trim();
        if (StringUtils.isEmpty(phonenumber)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!RegexUtils.isPhoneNumberValid(phonenumber)) {
            Toast.makeText(this, "手机号有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!invalicode.equals(tuPianCode)) {
            Toast.makeText(this, "图片验证信息输入有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick({R.id.btn_send_code, R.id.btn_registerButton})
    public void sendCode(View view) {
        switch (view.getId()) {
            case R.id.btn_send_code:
                if (!isvalidate()) break;
                btnSendCode.setText("获取验证码");
                btnSendCode.setClickable(true);
                getValidateCode();
                changeBtnGetCode();
                getValidateCodeHttp();
                break;
            case R.id.btn_registerButton:
                register();
                if (changed) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;
        }


    }

    //注册前判断
    private void register() {
        // 1.首先判断输入的值是否有效
        // 2.然后判断输入的验证码是否有效（防止没有点击获取验证码自己填的错误验证码)
        // 3.最后注册
        if (isValid()) {

            if (valicationCode.equals(serverValicationCode)) {
                getRegisterData();
            } else {
                Toast.makeText(this, "输入的验证码不正确!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void getRegisterData() {
        showProgress();
        Call<UserRegisterSuccessRsp> registerUserId = WoodPersonsClient.getInstance().getWoodPersonsApi().getRegisterUserId(phoneNumber);
        registerUserId.enqueue(new Callback<UserRegisterSuccessRsp>() {
            @Override
            public void onResponse(Call<UserRegisterSuccessRsp> call, Response<UserRegisterSuccessRsp> response) {
                hideProgress();
                if (response.isSuccessful()) {
                    UserRegisterSuccessRsp body = response.body();

                    int userId = body.getUserBean().getUserId();

                    //保存用户userid
                    UserPrefs.getInstance().setUserid(userId);

                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = userId;
                    handler.sendMessage(message);
                    Log.e("111111111111111", "onResponse: " + userId);
                }
            }

            @Override
            public void onFailure(Call<UserRegisterSuccessRsp> call, Throwable t) {
                hideProgress();
                Toast.makeText(RegisterActivity.this, "注册失败了" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 说明：注册之前判断数据是否为空
     */
    public boolean isValid() {
        phoneNumber = etPhoneNumber.getText().toString().trim();
        valicationCode = etRegisterCode.getText().toString().trim();
        if (phoneNumber.equals("")) {
            Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (valicationCode.equals("")) {
            Toast.makeText(this, "验证码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!serverValicationCode.equals(valicationCode)) {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (passWord.equals("")) {
//            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
//            return false;
//        } else if (passWord.length() < 6) {
//            Toast.makeText(this, "密码至少6位!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }


    public void changeBtnGetCode() {
        thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (i > 0) {
                        i--;
                        if (RegisterActivity.this == null) {
                            break;
                        }
                        RegisterActivity.this
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnSendCode.setText("获取验证码("
                                                + i + ")");
                                        btnSendCode.setClickable(false);
                                    }
                                });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    tag = false;
                }
                i = 60;
                tag = true;
                if (RegisterActivity.this != null) {
                    RegisterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnSendCode.setText("获取验证码");
                            btnSendCode.setClickable(true);
                        }
                    });
                }
            }

            ;
        };
        thread.start();
    }


    public boolean getValidateCode() {
        String phone = etPhoneNumber.getText().toString().trim();
        if (phone.equals("")) {
            Toast.makeText(this, "请输入用户名或手机号!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            phoneNumber = phone;
            return true;
        }
    }

    /**
     * 说明：获取验证码
     */
    public void getValidateCodeHttp() {
        // 获取服务器数据
        Call<UserRegisterCodeRsp> registerCode = WoodPersonsClient.getInstance().getWoodPersonsApi().getRegisterCode(phoneNumber);
        registerCode.enqueue(new Callback<UserRegisterCodeRsp>() {
            @Override
            public void onResponse(Call<UserRegisterCodeRsp> call, Response<UserRegisterCodeRsp> response) {
                if (response.isSuccessful()) {
                    UserRegisterCodeRsp body = response.body();
                    String validCode = body.getValidCode();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = validCode;
                    handler.sendMessage(message);

                }

            }

            @Override
            public void onFailure(Call<UserRegisterCodeRsp> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "请求失败了", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void showProgress() {
        dialog = ProgressDialog.show(this, "注册", "亲，正在注册中，请稍后~");

    }

    public void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
