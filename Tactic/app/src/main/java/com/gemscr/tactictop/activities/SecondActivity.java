package com.gemscr.tactictop.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.gemscr.tactictop.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAds();
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        showAds();
        startActivity(new Intent(SecondActivity.this, MasterActivity.class));
        finish();
    }

    private static final String ADMOB_APP_ID = "ca-app-pub-6610497664170714~7106826802";
    private static final String INTERESTITIAL_ADS_UNIT_ID = "ca-app-pub-6610497664170714/3917486871";
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onStart() {
        super.onStart();
        loadAdsReq();

    }

    private void loadAdsReq() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(INTERESTITIAL_ADS_UNIT_ID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        initHandler();

    }

    private void showAds() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
    Handler handler = null;

    void initHandler() {
        handler = new Handler();
        handler.postDelayed(adsRunnable, 20000);

    }

    Runnable adsRunnable = new Runnable() {
        @Override
        public void run() {
            showAds();
            if (handler!=null){
                handler.postDelayed(adsRunnable, 20000);
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler!=null){
            handler.removeCallbacks(adsRunnable);
        }
    }
}
