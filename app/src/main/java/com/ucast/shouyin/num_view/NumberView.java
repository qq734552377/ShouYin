package com.ucast.shouyin.num_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ucast.shouyin.R;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by pj on 2019/3/21.
 */
public class NumberView extends LinearLayout implements View.OnClickListener {

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
    private Button bt_num_empty;

    private EditText out_editText;
    private OnConfirmClickedListener listener;

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

    public void clearEditer(){
        this.out_editText = null;
    }

    public void setOnClickedConfirmListener(OnConfirmClickedListener clickedConfirmListener){
        this.listener = clickedConfirmListener;
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
        bt_num_empty = findViewById(R.id.num_empty);

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
        bt_num_empty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (out_editText == null)
            return;
        switch (v.getId()){
            case R.id.num_0:
                out_editText.append("0");
                break;
            case R.id.num_1:
                out_editText.append("1");
                break;
            case R.id.num_2:
                out_editText.append("2");
                break;
            case R.id.num_3:
                out_editText.append("3");
                break;
            case R.id.num_4:
                out_editText.append("4");
                break;
            case R.id.num_5:
                out_editText.append("5");
                break;
            case R.id.num_6:
                out_editText.append("6");
                break;
            case R.id.num_7:
                out_editText.append("7");
                break;
            case R.id.num_8:
                out_editText.append("8");
                break;
            case R.id.num_9:
                out_editText.append("9");
                break;
            case R.id.num_00:
                out_editText.append("00");
                break;
            case R.id.num_clear:
                out_editText.setText("");
                break;
            case R.id.num_back:
                String text = out_editText.getText().toString();
                if (text.length() ==0)
                    return;
                out_editText.setText(text.substring(0,text.length() - 1));
                out_editText.setSelection(text.length() - 1);
                break;
            case R.id.num_affirm:
                if (listener != null)
                    listener.onConfirmClicked(this);
                break;
            case R.id.num_empty:

                break;

            default:

                break;
        }
    }


    public interface OnConfirmClickedListener{
        void onConfirmClicked(NumberView view);
    }
}
