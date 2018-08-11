package com.lilzip.robux2.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lilzip.robux2.R;

public class MenuActivity extends Activity implements View.OnClickListener {

    private Button btnGameGuide, btnTips, btnTricks, btnSquadTeam, btnVbucks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initUI();
    }

    private void initUI() {
        btnGameGuide = findViewById(R.id.btnGameGuide);
        btnTips = findViewById(R.id.btnTips);
        btnTricks = findViewById(R.id.btnTricks);
        btnSquadTeam = findViewById(R.id.btnSquadTeam);
        btnVbucks = findViewById(R.id.btnVbucks);

        btnGameGuide.setOnClickListener(this);
        btnTips.setOnClickListener(this);
        btnTricks.setOnClickListener(this);
        btnSquadTeam.setOnClickListener(this);
        btnVbucks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGameGuide:
                startGameGuideActivity();
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
        startActivity(new Intent(MenuActivity.this, VBucksActivity.class));

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

    private void startGameGuideActivity() {
        startActivity(new Intent(MenuActivity.this, GameGuideActivity.class));
    }
}
