package com.ucast.shouyin.num_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ucast.shouyin.R;

/**
 * Created by pj on 2019/3/21.
 */
public class NumberView extends LinearLayout implements View.OnClickListener {
    private int pointBackNumberCount = 2;

    private Button bt_num_0;
    private Button bt_num_1;
    private Button bt_num_2;
    private Button bt_num_3;
    private Button bt_num_4;
    private Button bt_num_5;
    private Button bt_num_6;
    private Button bt_num_7;
    private Button bt_num_8;
    private Button bt_num_9;
    private Button bt_num_00;
    private Button bt_num_clear;
    private Button bt_num_back;
    private Button bt_num_affirm;
    private Button bt_num_point;
    private Button bt_vip_do;
    private Button bt_no_vip_do;
    private LinearLayout ll_vip_container;

    private EditText out_editText;
    private OnConfirmClickedListener confirmListener;
    private OnVipClickedListener vipListener;
    private OnNoVipClickedListener noVipListener;

    private String text = "";

    public NumberView(Context context) {
        super(context);
    }

    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.number_input,this);
        initViews();


    }

    public void setEditer(EditText ed){
        this.out_editText = ed;
    }

    public String getEditerText(){
        String str = "";
        if (this.out_editText != null)
            str = this.out_editText.getText().toString();
        return str;
    }

    public void clearEditer(){
        this.out_editText = null;
    }

    public void setOnClickedConfirmListener(OnConfirmClickedListener listener){
        this.confirmListener = listener;
    }
    public void setOnClickedVipListener(OnVipClickedListener listener){
        this.vipListener = listener;
    }
    public void setOnClickedNoVipListener(OnNoVipClickedListener listener){
        this.noVipListener = listener;
    }

    private void initViews() {
        bt_num_0 = findViewById(R.id.num_0);
        bt_num_1 = findViewById(R.id.num_1);
        bt_num_2 = findViewById(R.id.num_2);
        bt_num_3 = findViewById(R.id.num_3);
        bt_num_4 = findViewById(R.id.num_4);
        bt_num_5 = findViewById(R.id.num_5);
        bt_num_6 = findViewById(R.id.num_6);
        bt_num_7 = findViewById(R.id.num_7);
        bt_num_8 = findViewById(R.id.num_8);
        bt_num_9 = findViewById(R.id.num_9);
        bt_num_00 = findViewById(R.id.num_00);
        bt_num_clear = findViewById(R.id.num_clear);
        bt_num_back = findViewById(R.id.num_back);
        bt_num_affirm = findViewById(R.id.num_affirm);
        bt_num_point = findViewById(R.id.num_point);
        bt_vip_do = findViewById(R.id.bt_vip_do);
        bt_no_vip_do = findViewById(R.id.bt_no_vip_do);

        ll_vip_container = findViewById(R.id.ll_vip_select);

        bt_num_0.setOnClickListener(this);
        bt_num_1.setOnClickListener(this);
        bt_num_2.setOnClickListener(this);
        bt_num_3.setOnClickListener(this);
        bt_num_4.setOnClickListener(this);
        bt_num_5.setOnClickListener(this);
        bt_num_6.setOnClickListener(this);
        bt_num_7.setOnClickListener(this);
        bt_num_8.setOnClickListener(this);
        bt_num_9.setOnClickListener(this);
        bt_num_00.setOnClickListener(this);
        bt_num_clear.setOnClickListener(this);
        bt_num_back.setOnClickListener(this);
        bt_num_affirm.setOnClickListener(this);
        bt_num_point.setOnClickListener(this);
        bt_vip_do.setOnClickListener(this);
        bt_no_vip_do.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (out_editText == null)
            return;
        text = out_editText.getText().toString();
        if (text.indexOf("￥") == -1)
            out_editText.append("￥");
        switch (v.getId()){
            case R.id.num_0:
                numBtnOperation("0");
                break;
            case R.id.num_1:
                numBtnOperation("1");
                break;
            case R.id.num_2:
                numBtnOperation("2");
                break;
            case R.id.num_3:
                numBtnOperation("3");
                break;
            case R.id.num_4:
                numBtnOperation("4");
                break;
            case R.id.num_5:
                numBtnOperation("5");
                break;
            case R.id.num_6:
                numBtnOperation("6");
                break;
            case R.id.num_7:
                numBtnOperation("7");
                break;
            case R.id.num_8:
                numBtnOperation("8");
                break;
            case R.id.num_9:
                numBtnOperation("9");
                break;
            case R.id.num_00:
                if (text.length() == 0) {
                    numBtnOperation("0");
                    return;
                }
                numBtnOperation("00");
                break;

            case R.id.num_point:
                if (text.length() == 0) {
                    out_editText.setText("");
                    return;
                }
                if (text.contains("."))
                    return;
                out_editText.append(".");
                break;

            case R.id.num_clear:
                out_editText.setText("");
                break;

            case R.id.num_back:
                if (text.length() == 0) {
                    out_editText.setText("");
                    return;
                }
                numBackOneString();
                break;

            case R.id.num_affirm:
                if (text.length() == 0) {
                    out_editText.setText("");
                }
                if (confirmListener != null)
                    confirmListener.onConfirmClicked(this);
                break;
            case R.id.bt_vip_do:
                if (text.length() == 0) {
                    out_editText.setText("");
                }
                if (vipListener != null)
                    vipListener.onVipClicked(this);
                break;
            case R.id.bt_no_vip_do:
                if (text.length() == 0) {
                    out_editText.setText("");
                }
                if (noVipListener != null)
                    noVipListener.onNoVipClicked(this);
                break;
            default:

                break;
        }
    }

    public void numBtnOperation(String msg){
        if (msg.equals("0")){
            if (text.length() == 2 && stringToFloat() == 0f)
                return;
            if (text.length() == 4 && stringToFloat() == 0f)
                return;
        }
        if (msg.equals("00")){
            if (text.length() == 3 && stringToFloat() == 0f)
                out_editText.append("0");
            if (stringToFloat() == 0f)
                return;
        }
        if (text.contains(".")){
            String[] strs = text.split("\\.");
            if (msg.equals("00") && strs.length == 2 && strs[1].length() == pointBackNumberCount - 1){
                out_editText.append("0");
                return;
            }
            if (strs.length == 2 && strs[1].length() >= pointBackNumberCount) {
                return;
            }
        }else {
            if (!msg.equals("0") && !msg.equals("00") && text.length() == 2 && stringToFloat() == 0f){
                numBackOneString();
                out_editText.append("￥");
            }
        }
        out_editText.append(msg);
    }

    public float stringToFloat(){
        float money = 0f;
        if (text.length() >= 2){
            String str = text.substring(1,text.length());
            try {
                money = Float.parseFloat(str);
            }catch (Exception e){

            }
        }
        return money;
    }
    public int stringToInt(){
        int money = 0;
        if (text.length() >= 2){
            String str = text.substring(1,text.length());
            try {
                float moneyFloat = Float.parseFloat(str);
                money =(int)( moneyFloat * 100f);
            }catch (Exception e){

            }
        }
        return money;
    }

    public void numBackOneString(){
        if (text.length() == 2) {
            out_editText.setText("");
            return;
        }
        out_editText.setText(text.substring(0,text.length() - 1));
        out_editText.setSelection(text.length() - 1);
    }

    public void setAffirmBtVisibility(boolean isVisibility){
        if (isVisibility) {
            bt_num_affirm.setVisibility(VISIBLE);
            ll_vip_container.setVisibility(GONE);
        }else {
            bt_num_affirm.setVisibility(GONE);
            ll_vip_container.setVisibility(VISIBLE);
        }
    }

    public interface OnConfirmClickedListener{
        void onConfirmClicked(NumberView view);
    }
    public interface OnVipClickedListener{
        void onVipClicked(NumberView view);
    }
    public interface OnNoVipClickedListener{
        void onNoVipClicked(NumberView view);
    }
}
