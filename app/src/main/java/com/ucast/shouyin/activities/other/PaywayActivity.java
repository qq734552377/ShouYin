package com.ucast.shouyin.activities.other;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ucast.shouyin.R;
import com.ucast.shouyin.entities.OnePayObject;
import com.ucast.shouyin.entities.PayObjectSingleInsance;
import com.ucast.shouyin.num_view.MyTitleView;
import com.ucast.shouyin.tools.MyTools;

public class PaywayActivity extends AppCompatActivity {
    private MyTitleView titleView;

    private TextView tv_showOldMoneyNumber;
    private TextView tv_showMoneyNumber;
    private TextView tv_payedMoneyNumber;
    private TextView tv_willPayMoneyNumber;

    private OnePayObject onePayObject;
    private boolean isNavShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payway);
        initData();
        onePayObject = PayObjectSingleInsance.getInstance().getOnePayObject();
        initViews();
        MyTools.hiddenBottom(this);
    }

    private void initData() {
        Intent i = getIntent();
        isNavShow = i.getBooleanExtra(getString(R.string.payway_actvity_nav_show),true);
    }

    private void initViews() {
        titleView = findViewById(R.id.mytitle);
        titleView.setNavVisibility(isNavShow);
        titleView.setNavSrc(R.drawable.left);
        titleView.setNavClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_showOldMoneyNumber = findViewById(R.id.show_old_money_number);
        tv_showOldMoneyNumber.setText(getShowString(onePayObject.getTotalPayMoney()));
        tv_showOldMoneyNumber.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_showOldMoneyNumber.setVisibility(onePayObject.isVipPay()? View.VISIBLE : View.GONE);

        tv_showMoneyNumber = findViewById(R.id.money);
        tv_showMoneyNumber.setText(getShowString(onePayObject.getTotalPayMoney() - onePayObject.getDiscount()));

        tv_payedMoneyNumber = findViewById(R.id.payed_money);
        tv_payedMoneyNumber.setText(onePayObject.getAllPayedMoney());

        tv_willPayMoneyNumber = findViewById(R.id.will_pay_money);
        tv_willPayMoneyNumber.setText(getShowString(onePayObject.getWillPayMoney()));


    }
    @Override
    public void onBackPressed() {

    }


    public String getShowString(float money){
        return "￥" + MyTools.floatToLastTwoString(money);
    }
}
