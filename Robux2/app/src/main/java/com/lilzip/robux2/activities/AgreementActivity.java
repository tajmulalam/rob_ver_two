package com.lilzip.robux2.activities;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import com.lilzip.robux2.R;

public class AgreementActivity extends Activity {

    private TextView tvAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        tvAgreement = findViewById(R.id.tvAgreement);

    }
}
