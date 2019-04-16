package com.ucast.shouyin.activities.other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ucast.shouyin.MainActivity;
import com.ucast.shouyin.R;
import com.ucast.shouyin.num_view.MyBigTishiDialog;
import com.ucast.shouyin.num_view.MyTitleView;
import com.ucast.shouyin.num_view.NumberView;
import com.ucast.shouyin.tools.MyDialog;

public class PaywayResultActivity extends AppCompatActivity {
    private MyTitleView titleView;
    private TextView tv_payTypeName;
    private TextView tv_payID;
    private TextView tv_payshouyinyuan;
    private TextView tv_paymoneyNumber;
    private TextView tv_shifuMsg;
    private TextView tv_zhaoLinMsg;
    private TextView tv_payFailMsg;

    private Button returnFirstPage;

    private LinearLayout ll_showPaySucess;
    private LinearLayout ll_showPayFail;
    String scanString;

    private MyBigTishiDialog bigTishiDialog;

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

        tv_payTypeName = findViewById(R.id.paytype_show_tv);
        tv_payID = findViewById(R.id.paynumber_show_tv);
        tv_payshouyinyuan = findViewById(R.id.shouyinyuan_show_tv);
        tv_paymoneyNumber = findViewById(R.id.money_number_show_tv);
        tv_shifuMsg = findViewById(R.id.shifu_show_tv);
        tv_zhaoLinMsg = findViewById(R.id.zhaolin_show_tv);
        tv_payFailMsg = findViewById(R.id.payfail_result_show_tv);

        ll_showPaySucess = findViewById(R.id.pay_success_show);
        ll_showPayFail = findViewById(R.id.pay_fail_show);

        if (bigTishiDialog == null) {
            bigTishiDialog = MyDialog.createBigTishiDialog(this, "正在支付中，\n请稍后...");
        }

        returnFirstPage = findViewById(R.id.return_first_page);
        returnFirstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(PaywayResultActivity.this, MainActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
                bigTishiDialog.show();

            }
        });

        ll_showPaySucess.setVisibility(View.GONE);
        ll_showPayFail.setVisibility(View.GONE);
        returnFirstPage.setVisibility(View.VISIBLE);




    }
}
