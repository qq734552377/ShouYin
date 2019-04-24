package com.ucast.shouyin.activities.other;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ucast.shouyin.MainActivity;
import com.ucast.shouyin.R;
import com.ucast.shouyin.entities.OnePayObject;
import com.ucast.shouyin.entities.PayObjectSingleInsance;
import com.ucast.shouyin.entities.PayType;
import com.ucast.shouyin.entities.PayedMoneyWithType;
import com.ucast.shouyin.exception.ExceptionApplication;
import com.ucast.shouyin.num_view.MyBigTishiDialog;
import com.ucast.shouyin.num_view.MyTitleView;
import com.ucast.shouyin.sql.entity.OnePayedOkObj;
import com.ucast.shouyin.sql.manager.MyDbHelper;
import com.ucast.shouyin.tools.MyDialog;
import com.ucast.shouyin.tools.MyTools;

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
    private PayType payType;

    private MyBigTishiDialog bigTishiDialog;
    private OnePayObject onePayObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payway_result);
        getMsg();
        initViews();
        MyTools.hiddenBottom(this);
    }

    private void getMsg() {
        Intent i = getIntent();

        Bundle bundle = i.getExtras();
        if (payType == null)
            payType = (PayType) bundle.getSerializable(getString(R.string.pay_type));
        if (payType != null && payType == PayType.SAOMAZHIFU)
            scanString = i.getStringExtra(getString(R.string.scan_result));
        if (onePayObject == null)
            onePayObject = PayObjectSingleInsance.getInstance().getOnePayObject();
    }

    private void initViews() {
        titleView = findViewById(R.id.mytitle);
        titleView.setNavVisibility(false);

        tv_payTypeName = findViewById(R.id.paytype_show_tv);
        tv_payID = findViewById(R.id.paynumber_show_tv);
        tv_payshouyinyuan = findViewById(R.id.shouyinyuan_show_tv);
        tv_paymoneyNumber = findViewById(R.id.money_number_show_tv);
        tv_shifuMsg = findViewById(R.id.shifu_show_tv);
        tv_shifuMsg.setTypeface(Typeface.create(ExceptionApplication.simsunTypeface,Typeface.BOLD));

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
                Intent i = new Intent(PaywayResultActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
//                bigTishiDialog.show();

            }
        });

        ll_showPaySucess.setVisibility(View.GONE);
        ll_showPayFail.setVisibility(View.GONE);
        tv_zhaoLinMsg.setVisibility(View.GONE);
        returnFirstPage.setVisibility(View.GONE);

        if (payType == PayType.XIANJIN){
            ll_showPaySucess.setVisibility(View.VISIBLE);
            float currentPaymoney = onePayObject.getCurrentWillPayMoney();
            float willPaymoney = onePayObject.getWillPayMoney();

            if (currentPaymoney > willPaymoney){//找零
                tv_zhaoLinMsg.setVisibility(View.VISIBLE);
                tv_zhaoLinMsg.setText("请收现金" + getShowString(currentPaymoney) +"  找零" + getShowString(currentPaymoney - willPaymoney));
                returnFirstPage.setVisibility(View.VISIBLE);

                tv_payTypeName.setText(onePayObject.getAllPayedType() + payType.getName());
                tv_payID.setText(onePayObject.getPayID());
                tv_payshouyinyuan.setText(onePayObject.getShouyinYuan().getName());
                tv_paymoneyNumber.setText(getShowString(currentPaymoney));

                PayedMoneyWithType payedMoneyWithType = new PayedMoneyWithType(payType,currentPaymoney);
                onePayObject.addOnePayedMoneyWithType(payedMoneyWithType);

                tv_shifuMsg.setText(getShowString(onePayObject.getAllPayedMoneyFloat()) + "\n" + onePayObject.getShowMoneyString());

            }else if (currentPaymoney == willPaymoney){//结清
                tv_zhaoLinMsg.setVisibility(View.GONE);
                returnFirstPage.setVisibility(View.VISIBLE);

                tv_payTypeName.setText(onePayObject.getAllPayedType() + payType.getName());
                tv_payID.setText(onePayObject.getPayID());
                tv_payshouyinyuan.setText(onePayObject.getShouyinYuan().getName());
                tv_paymoneyNumber.setText(getShowString(currentPaymoney));


                PayedMoneyWithType payedMoneyWithType = new PayedMoneyWithType(payType,currentPaymoney);
                onePayObject.addOnePayedMoneyWithType(payedMoneyWithType);

                tv_shifuMsg.setText(getShowString(onePayObject.getAllPayedMoneyFloat()) + "\n" + onePayObject.getShowMoneyString());

            }else{//未结清
                PayedMoneyWithType payedMoneyWithType = new PayedMoneyWithType(payType,currentPaymoney);
                onePayObject.addOnePayedMoneyWithType(payedMoneyWithType);
                Intent i = new Intent(PaywayResultActivity.this, PaywayActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }


        }


        if (onePayObject.isPayedOK()){
            OnePayedOkObj onePayedOkObj = new OnePayedOkObj();
            onePayedOkObj.setOnePayObjJsonStr(onePayObject);
            MyDbHelper.getInstance().insertOneAllPayObj(onePayedOkObj);
        }


    }

    @Override
    public void onBackPressed() {

    }

    public String getShowString(float money){
        return "￥" + MyTools.floatToLastTwoString(money);
    }
}
