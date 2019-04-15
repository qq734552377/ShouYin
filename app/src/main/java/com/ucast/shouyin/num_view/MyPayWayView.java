package com.ucast.shouyin.num_view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ucast.shouyin.R;
import com.ucast.shouyin.activities.other.Payway2Activity;

/**
 * Created by pj on 2019/4/15.
 */
public class MyPayWayView extends LinearLayout implements View.OnClickListener {
    private LinearLayout ll_chuzhika;
    private LinearLayout ll_saomazhifu;
    private LinearLayout ll_yinhangka;
    private LinearLayout ll_huiyuan;
    private LinearLayout ll_xianjin;

    public MyPayWayView(Context context) {
        super(context);
    }

    public MyPayWayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_select_payway,this);
        initViews();
    }

    private void initViews() {
        ll_chuzhika = findViewById(R.id.chuzhika);
        ll_saomazhifu = findViewById(R.id.saomazhifu);
        ll_yinhangka = findViewById(R.id.yinhanka);
        ll_huiyuan = findViewById(R.id.huiyuanka);
        ll_xianjin = findViewById(R.id.xianjin);


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
        switch (v.getId()){
            case R.id.chuzhika:
                i.putExtra(key,R.id.pay_chuzhika);
                this.getContext().startActivity(i);
                break;
            case R.id.saomazhifu:
                i.putExtra(key,R.id.pay_saomazhifu);
                this.getContext().startActivity(i);
                break;
            case R.id.yinhanka:
                i.putExtra(key,R.id.pay_yinhangka);
                this.getContext().startActivity(i);
                break;
            case R.id.huiyuanka:
                i.putExtra(key,R.id.pay_huiyuanka);
                this.getContext().startActivity(i);
                break;
            case R.id.xianjin:
                i.putExtra(key,R.id.pay_xianjin);
                this.getContext().startActivity(i);
                break;
            default:
                break;
        }
    }
}
