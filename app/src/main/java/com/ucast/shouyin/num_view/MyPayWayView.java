package com.ucast.shouyin.num_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ucast.shouyin.R;
import com.ucast.shouyin.activities.other.Payway2Activity;
import com.ucast.shouyin.entities.PayObjectSingleInsance;
import com.ucast.shouyin.entities.PayType;

/**
 * Created by pj on 2019/4/15.
 */
public class MyPayWayView extends LinearLayout implements View.OnClickListener {
    private LinearLayout ll_chuzhika;
    private LinearLayout ll_saomazhifu;
    private LinearLayout ll_yinhangka;
    private LinearLayout ll_huiyuan;
    private LinearLayout ll_xianjin;
    private LinearLayout ll_tianchong;

    public MyPayWayView(Context context) {
        super(context);
    }

    public MyPayWayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_select_payway,this);
        initViews();
        setShowVip(PayObjectSingleInsance.getInstance().getOnePayObject().isVipPay());
    }

    private void initViews() {
        ll_chuzhika = findViewById(R.id.chuzhika);
        ll_saomazhifu = findViewById(R.id.saomazhifu);
        ll_yinhangka = findViewById(R.id.yinhanka);
        ll_huiyuan = findViewById(R.id.huiyuanka);
        ll_xianjin = findViewById(R.id.xianjin);
        ll_tianchong = findViewById(R.id.item_tianchong);


        ll_chuzhika.setOnClickListener(this);
        ll_saomazhifu.setOnClickListener(this);
        ll_yinhangka.setOnClickListener(this);
        ll_huiyuan.setOnClickListener(this);
        ll_xianjin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this.getContext(), Payway2Activity.class);
        String key = this.getContext().getString(R.string.pay_type);
        PayType payType = null;
        switch (v.getId()){
            case R.id.chuzhika:
                payType = PayType.CHUZHIKA;
                break;
            case R.id.saomazhifu:
                payType = PayType.SAOMAZHIFU;
                break;
            case R.id.yinhanka:
                payType = PayType.YINHANGKA;
                break;
            case R.id.huiyuanka:
                payType = PayType.HUIYUANKA;
                break;
            case R.id.xianjin:
                payType = PayType.XIANJIN;
                break;
            default:
                payType = PayType.SAOMAZHIFU;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, payType);
        i.putExtras(bundle);
        this.getContext().startActivity(i);
    }


    public void setShowVip(boolean isShowVip){
        if (isShowVip){
            ll_huiyuan.setVisibility(VISIBLE);
            ll_tianchong.setVisibility(GONE);
        }else{
            ll_huiyuan.setVisibility(GONE);
            ll_tianchong.setVisibility(VISIBLE);
        }
    }
}
