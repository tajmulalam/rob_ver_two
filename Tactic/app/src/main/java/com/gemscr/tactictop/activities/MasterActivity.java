package com.gemscr.tactictop.activities;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.gemscr.tactictop.R;

public class MasterActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ImageButton ibtnContinue, ibtnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        MobileAds.initialize(this, ADMOB_APP_ID);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ibtnContinue = findViewById(R.id.ibtnContinue);
        ibtnRate = findViewById(R.id.ibtnRate);
        ibtnRate.setVisibility(View.GONE);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView tvVersionCode = (TextView) headerView.findViewById(R.id.tvVersionCode);
        tvVersionCode.setText("Version code " + getVersionCode());
        ibtnRate.setOnClickListener(this);
        ibtnContinue.setOnClickListener(this);
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
        handler.postDelayed(adsRunnable, 25000);

    }

    Runnable adsRunnable = new Runnable() {
        @Override
        public void run() {
            showAds();
            if (handler != null) {
                handler.postDelayed(adsRunnable, 25000);
            }
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public String getVersionCode() {
        String v = "0.0";
        try {
            v = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {

        }
        return v;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.master, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            showAds();
        }
        if (id == R.id.action_policy) {
            showAds();
            showPolicyActivity();
        }
        if (id == R.id.action_exit) {
            showExitDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        showAds();

    }

    private void showPolicyActivity() {
        startActivity(new Intent(MasterActivity.this, AgreementActivity.class));
        finish();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            showAds();
        } else if (id == R.id.nav_exit) {
            showExitDialog();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnRate:
                launchMarket();
                break;
            case R.id.ibtnContinue:
                startActivity(new Intent(MasterActivity.this, SecondActivity.class));
                finish();
                break;
        }
    }

    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }
}
