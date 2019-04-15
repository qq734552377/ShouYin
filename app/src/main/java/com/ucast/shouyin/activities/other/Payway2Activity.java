package com.ucast.shouyin.activities.other;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ucast.shouyin.MainActivity;
import com.ucast.shouyin.R;
import com.ucast.shouyin.activities.base.EmployeActivity;
import com.ucast.shouyin.num_view.NumberView;
import com.ucast.shouyin.tools.MyDialog;

public class Payway2Activity extends AppCompatActivity implements NumberView.OnConfirmClickedListener{
    public final static String CAMERAKEY = "camera_type";
    public final static String RESULT = "result";
    private int pay_type;
    private NumberView numberView;
    private TextView pay_type_tv;

    private EditText edTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payway2);
        initToolbar(R.id.toolbar,R.id.toolbar_title,getTitleName());
        initViews();
    }

    private void initViews() {
        numberView  = findViewById(R.id.mynumberview);
        numberView.setOnClickedConfirmListener(this);

        pay_type_tv = findViewById(R.id.pay_type_tv);
        pay_type_tv.setText(getTitleName());

        edTemp = new EditText(this);
        numberView.setEditer(edTemp);

    }

    public int getTitleName(){
        int nameID = R.id.pay_saomazhifu;
        Intent i = getIntent();
        pay_type = i.getIntExtra(getString(R.string.pay_type),R.id.pay_saomazhifu);
        switch (pay_type){
            case R.id.pay_chuzhika:
                nameID = R.string.chuzhika;
                break;
            case R.id.pay_saomazhifu:
                nameID = R.string.saomazhifu;
                break;
            case R.id.pay_yinhangka:
                nameID = R.string.yinhangka;
                break;
            case R.id.pay_huiyuanka:
                nameID = R.string.huiyuan;
                break;
            case R.id.pay_xianjin:
                nameID = R.string.xianjin;
                break;
        }
        return nameID;
    }

    public Toolbar initToolbar(int id, int titleId, int titleString) {
        Toolbar toolbar = (Toolbar) findViewById(id);
//        toolbar.setTitle("");
        TextView textView = (TextView) findViewById(titleId);
        textView.setText(titleString);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return toolbar;
    }

    @Override
    public void onConfirmClicked(NumberView view) {
        startUcastCamera(0);
    }

    public void startUcastCamera(int type){
        Intent intent = new Intent();
        intent.setClassName("com.ucast.jnidiaoyong_print","com.ucast.jnidiaoyongdemo.erweima.view.mysaomiao.CaptureActivity");
        intent.putExtra(CAMERAKEY, type);
        startActivityForResult(intent, type);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        String r = "";
        switch (resultCode) {
            case 0://侧面摄像头
                r = data.getStringExtra(RESULT);
                break;
            case 1://前面摄像头
                r = data.getStringExtra(RESULT);
                break;
            default:
                break;
        }
        MyDialog.showToast(this,r);
        Intent i = new Intent(this,PaywayResultActivity.class);
        i.putExtra(getString(R.string.scan_result),r);
        startActivity(i);
    }
}
