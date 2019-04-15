package com.ucast.shouyin.activities.other;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ucast.shouyin.R;
import com.ucast.shouyin.num_view.MyTitleView;

public class PaywayActivity extends AppCompatActivity {
    private MyTitleView titleView;

    private TextView showOldMoneyNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payway);
        initViews();

    }

    private void initViews() {
        titleView = findViewById(R.id.mytitle);
        titleView.setNavSrc(R.drawable.left);
        titleView.setNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showOldMoneyNumber = findViewById(R.id.show_old_money_number);
        showOldMoneyNumber.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
