package com.jywy.woodpersons.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.MainActivity;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
import com.jywy.woodpersons.ui.user.register.RegisterActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;
    private TextView textView;
    private int timeStart = 5;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activityUtils = new ActivityUtils(this);
        textView = (TextView) findViewById(R.id.tv_skip);

        timer = new Timer();
        timer.schedule(task, 1000, 1000);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();

                // 判断一下用户是不是登录过
                SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
                if (preferences!=null){
                    if (preferences.getInt("key_userid",0)== UserPrefs.getInstance().getUserid()){
                        activityUtils.startActivity(MainActivity.class);
                        finish();
                    }
                }
                activityUtils.startActivity(RegisterActivity.class);
                finish();
            }
        });
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    timeStart--;
                    textView.setText("跳过("+ timeStart + ")");
                    if (timeStart == 0) {
                        timer.cancel();

                        // 判断一下用户是不是登录过
                        SharedPreferences preferences = getSharedPreferences("user_info", MODE_PRIVATE);
                        if (preferences!=null){
                            if (preferences.getInt("key_userid",0)== UserPrefs.getInstance().getUserid()){
                                activityUtils.startActivity(MainActivity.class);
                                finish();
                            }
                        }
                        activityUtils.startActivity(RegisterActivity.class);
                        finish();
                    }
                }
            });
        }
    };
}
