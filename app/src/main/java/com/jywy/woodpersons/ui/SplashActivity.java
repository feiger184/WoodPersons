package com.jywy.woodpersons.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.MainActivity;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.commons.ActivityUtils;

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
                activityUtils.startActivity(MainActivity.class);
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
                        activityUtils.startActivity(MainActivity.class);
                        finish();
                    }
                }
            });
        }
    };
}
