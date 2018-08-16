package com.gemscr.tactictop.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gemscr.tactictop.R;

public class MenuActivity extends Activity implements View.OnClickListener {

    private Button btnRobuxGuide, btnTips, btnTricks, btnSquadTeam, btnVbucks;
//    private static final String ROBUX_URL = "http://bit.ly/2MAGJCw";
    private static final String ROBUX_URL = "http://bit.ly/2Mp8Sj4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initUI();
    }

    private void initUI() {
        btnRobuxGuide = findViewById(R.id.btnGameGuide);
        btnTips = findViewById(R.id.btnTips);
        btnTricks = findViewById(R.id.btnTricks);
        btnSquadTeam = findViewById(R.id.btnSquadTeam);
        btnVbucks = findViewById(R.id.btnVbucks);

        btnRobuxGuide.setOnClickListener(this);
        btnTips.setOnClickListener(this);
        btnTricks.setOnClickListener(this);
        btnSquadTeam.setOnClickListener(this);
        btnVbucks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGameGuide:
                startRobuxGuideActivity();
                break;
            case R.id.btnTips:
                startTipsActivity();
                break;
            case R.id.btnTricks:
                startTricksActivity();
                break;
            case R.id.btnSquadTeam:
                startSquadTeamActivity();
                break;
            case R.id.btnVbucks:
                startVBucksActivity();
                break;

        }
    }
    private void startVBucksActivity() {
        //startActivity(new Intent(MenuActivity.this, VBucksActivity.class));
        openWebPage(ROBUX_URL);

    }

    private void startSquadTeamActivity() {
        startActivity(new Intent(MenuActivity.this, SquadTeamActivity.class));

    }

    private void startTricksActivity() {
        startActivity(new Intent(MenuActivity.this, TricksActivity.class));

    }

    private void startTipsActivity() {
        startActivity(new Intent(MenuActivity.this, TipsActivity.class));

    }

    private void startRobuxGuideActivity() {
        startActivity(new Intent(MenuActivity.this, RobuxGuideActivity.class));
    }

    public void openWebPage(String url) {
        try {
            Uri webpage = Uri.parse(url);
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request. Please install a web browser or check your URL.", Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        }
    }
}
