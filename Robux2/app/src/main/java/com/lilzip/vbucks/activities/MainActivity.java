package com.lilzip.vbucks.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.lilzip.vbucks.R;
import com.lilzip.vbucks.utils.SharedPreferenceValue;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnStartWorking;
    private TextView tvUserAgrement;
    private CheckBox cbUserAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        btnStartWorking = findViewById(R.id.btnStartWorking);
        tvUserAgrement = findViewById(R.id.tvUserAgrement);
        cbUserAgreement = findViewById(R.id.cbUserAgreement);
        tvUserAgrement.setPaintFlags(tvUserAgrement.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnStartWorking.setOnClickListener(this);
        tvUserAgrement.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartWorking:
                if (cbUserAgreement.isChecked()) {
                    startWorkingClicked();
                } else {
                    Toast.makeText(this, "To continue you must read and agree to the user agreement", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvUserAgrement:
                startAgreementActivity();
                break;
        }
    }

    private void startAgreementActivity() {
        startActivity(new Intent(MainActivity.this, AgreementActivity.class));
    }

    private void startWorkingClicked() {
        SharedPreferenceValue.setLoggedInUser(MainActivity.this, 110011);
        startActivity(new Intent(MainActivity.this, MenuActivity.class));
        finish();
    }
}
