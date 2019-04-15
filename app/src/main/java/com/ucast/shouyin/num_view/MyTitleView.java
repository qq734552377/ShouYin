package com.ucast.shouyin.num_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ucast.shouyin.R;

/**
 * Created by pj on 2019/4/15.
 */
public class MyTitleView extends LinearLayout {
    private Context context;
    private ImageView nav;

    private TextView shouyintai;
    private TextView account;

    public MyTitleView(Context context) {
        super(context);
    }

    public MyTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar,this);
        initViews();

    }

    private void initViews() {
        nav = findViewById(R.id.nav);

        shouyintai = findViewById(R.id.shouyintai);
        account = findViewById(R.id.account);
    }

    public void setShouYinTaiText(String msg){
        shouyintai.setText(msg);
    }
    public void setAccountText(String msg){
        account.setText(msg);
    }
    public void setNavSrc(int srcId){
        nav.setImageResource(srcId);
    }
    public void setNavVisibility(boolean navVisibility){
        if (navVisibility){
            nav.setVisibility(VISIBLE);
        }else{
            nav.setVisibility(GONE);
        }
    }
    public void setNavClickListener(OnClickListener listener){
        nav.setOnClickListener(listener);
    }
}
