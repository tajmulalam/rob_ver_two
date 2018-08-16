package com.gemscr.tactictop.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gemscr.tactictop.R;
import com.gemscr.tactictop.utils.SharedPreferenceValue;


public class SplashActivity extends Activity {

    //    private ProgressBar spinner;
    private TextView tvVersionCode;
    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        spinner = (ProgressBar) findViewById(R.id.progressBar);
//        spinner.setVisibility(View.VISIBLE);
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
                if (SharedPreferenceValue.getLoggedinUser(SplashActivity.this) != -1) {

                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                } else
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2500);*/
        initUI();
    }

    private void initUI() {
        btnEnter = findViewById(R.id.btnEnter);
        tvVersionCode = findViewById(R.id.tvVersionCode);
        tvVersionCode.setText("Version code "+getVersionCode());
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MasterActivity.class));
                finish();
            }
        });
    }

    public String  getVersionCode() {
        String  v = "0.0";
        try {
            v = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return v;
    }

}
