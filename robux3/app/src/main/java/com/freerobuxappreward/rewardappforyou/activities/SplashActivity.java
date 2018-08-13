package com.freerobuxappreward.rewardappforyou.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.freerobuxappreward.rewardappforyou.R;
import com.freerobuxappreward.rewardappforyou.utils.SharedPreferenceValue;


public class SplashActivity extends Activity {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
                if (SharedPreferenceValue.getLoggedinUser(SplashActivity.this) != -1) {

                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                } else
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2500);
    }

}
