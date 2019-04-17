package com.ucast.shouyin.num_view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucast.shouyin.R;

import java.util.ArrayList;

/**
 * Created by pj on 2019/4/16.
 */
public class MyInputPasswordDialog extends Dialog implements View.OnClickListener {

    private OnInputCompleteListener listener;

    private String tishiMsg= "";
    private String moneyNumber = "";

    int[] edtIds = new int[6];
    ArrayList<TextView> passwordTvs;

    private TextView tishi_msg_tv;
    private TextView moeny_number_tv;

    private Button num_1;
    private Button num_2;
    private Button num_3;
    private Button num_4;
    private Button num_5;
    private Button num_6;
    private Button num_7;
    private Button num_8;
    private Button num_9;
    private Button num_0;
    private ImageView num_back;

    private int currentPasswordInoutIndex = 0;

    public MyInputPasswordDialog(Context context) {
        super(context);
    }

    public MyInputPasswordDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //提前设置一些样式
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        setContentView(R.layout.input_password_dialog);
        Display display = window.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = display.getWidth();
        lp.height = display.getHeight() * 4 / 5;
        window.setAttributes(lp);

        initViews();
    }

    private void initViews() {
        passwordTvs = new ArrayList<>();
        edtIds[0] = R.id.dialog_input_et_1;
        edtIds[1] = R.id.dialog_input_et_2;
        edtIds[2] = R.id.dialog_input_et_3;
        edtIds[3] = R.id.dialog_input_et_4;
        edtIds[4] = R.id.dialog_input_et_5;
        edtIds[5] = R.id.dialog_input_et_6;
        for (int i = 0; i < edtIds.length; i++) {
            TextView etItem = findViewById(edtIds[i]);
            passwordTvs.add(etItem);
        }

        tishi_msg_tv = findViewById(R.id.tishi_msg_tv);
        tishi_msg_tv.setText(tishiMsg);

        moeny_number_tv = findViewById(R.id.moeny_number_tv);
        moeny_number_tv.setText(moneyNumber);

        num_0 = findViewById(R.id.num_0);
        num_1 = findViewById(R.id.num_1);
        num_2 = findViewById(R.id.num_2);
        num_3 = findViewById(R.id.num_3);
        num_4 = findViewById(R.id.num_4);
        num_5 = findViewById(R.id.num_5);
        num_6 = findViewById(R.id.num_6);
        num_7 = findViewById(R.id.num_7);
        num_8 = findViewById(R.id.num_8);
        num_9 = findViewById(R.id.num_9);
        num_back = findViewById(R.id.num_back);

        num_0.setOnClickListener(this);
        num_1.setOnClickListener(this);
        num_2.setOnClickListener(this);
        num_3.setOnClickListener(this);
        num_4.setOnClickListener(this);
        num_5.setOnClickListener(this);
        num_6.setOnClickListener(this);
        num_7.setOnClickListener(this);
        num_8.setOnClickListener(this);
        num_9.setOnClickListener(this);
        num_back.setOnClickListener(this);

    }

    public void setInputCompleteListener(OnInputCompleteListener inputCompleteListener){
        this.listener = inputCompleteListener;
    }
    public void setTishiMsg(String msg){
        this.tishiMsg = msg;
        if (tishi_msg_tv != null)
            tishi_msg_tv.setText(msg);
    }
    public void setMoneyNumber(String moneyNumber){
        this.moneyNumber = moneyNumber;
        if (moeny_number_tv != null)
            moeny_number_tv.setText(moneyNumber);
    }

    @Override
    public void onClick(View v) {
        int type = v.getId();
        if (currentPasswordInoutIndex >= passwordTvs.size())
            return;
        TextView item = passwordTvs.get(currentPasswordInoutIndex);
        String itemStr = "";
        if (v instanceof Button)
            itemStr = ((Button) v).getText().toString().trim();
        if (item == null)
            return;
        switch (type){
            case R.id.num_0:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_1:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_2:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_3:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_4:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_5:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_6:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_7:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_8:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_9:
                item.setText(itemStr);currentPasswordInoutIndex ++;
                break;
            case R.id.num_back:
                if (currentPasswordInoutIndex > 0){
                    TextView lastItem = passwordTvs.get(currentPasswordInoutIndex - 1);
                    lastItem.setText(itemStr);
                    currentPasswordInoutIndex --;
                }
                break;
        }

        if (currentPasswordInoutIndex == 6){
            if (listener != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < passwordTvs.size(); i++) {
                    sb.append(passwordTvs.get(i).getText().toString().trim());
                }
                listener.onInputComplete(this, sb.toString());
                initAllThing();
            }
        }

    }


    public void initAllThing(){
        for (int i = 0; i < passwordTvs.size(); i++) {
            passwordTvs.get(i).setText("");
        }
        currentPasswordInoutIndex = 0;
    }


    public interface OnInputCompleteListener {
        void onInputComplete(Dialog dialog, String password);
    }
}
