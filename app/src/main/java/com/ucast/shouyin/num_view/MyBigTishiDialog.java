package com.ucast.shouyin.num_view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.ucast.shouyin.R;

/**
 * Created by pj on 2019/4/16.
 */
public class MyBigTishiDialog extends Dialog {
    private Context context;
    private TextView showMsg;
    private String showMsgStr;
    public MyBigTishiDialog(Context context) {
        super(context);
    }

    public MyBigTishiDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.big_tishi_dialog);
        showMsg = findViewById(R.id.big_dialog_show_msg);
        if (showMsgStr != null)
            showMsg.setText(showMsgStr);
    }

    public void setMsg(String msg){
        this.showMsgStr = msg;
        if (showMsg != null)
            showMsg.setText(this.showMsgStr);
    }
}
