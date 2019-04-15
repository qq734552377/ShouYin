package com.ucast.shouyin.activities.other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ucast.shouyin.R;
import com.ucast.shouyin.num_view.MyTitleView;
import com.ucast.shouyin.num_view.NumberView;

public class PaywayResultActivity extends AppCompatActivity {
    private MyTitleView titleView;
    String scanString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payway_result);
        getMsg();
        initViews();
    }

    private void getMsg() {
        Intent i = getIntent();
        scanString = i.getStringExtra(getString(R.string.scan_result));
    }

    private void initViews() {
        titleView = findViewById(R.id.mytitle);
        titleView.setNavVisibility(false);
    }
}
