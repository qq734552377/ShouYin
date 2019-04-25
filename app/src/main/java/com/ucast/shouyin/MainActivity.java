package com.ucast.shouyin;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ucast.shouyin.activities.base.EmployeActivity;
import com.ucast.shouyin.activities.other.PaywayActivity;
import com.ucast.shouyin.entities.EmployeeSingleInstance;
import com.ucast.shouyin.entities.OnePayObject;
import com.ucast.shouyin.entities.PayObjectSingleInsance;
import com.ucast.shouyin.num_view.MyTitleView;
import com.ucast.shouyin.num_view.NumberView;
import com.ucast.shouyin.tools.Config;
import com.ucast.shouyin.tools.MyDialog;
import com.ucast.shouyin.tools.MyTools;
import com.ucast.shouyin.tools.ToastUtil;
import com.ucast.shouyin.yl.HttpReauestUrl;
import com.ucast.shouyin.yl.YinlianToken;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private MyTitleView titleView;
    private NumberView numberView;

    private EditText moneyEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initToolbar(R.id.toolbar,R.id.toolbar_title,R.string.base);
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this, "需要写sd卡", 1, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            EasyPermissions.requestPermissions(this, "需要读取sd卡", 1, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.INTERNET)) {
            EasyPermissions.requestPermissions(this, "需要访问网络", 1, Manifest.permission.INTERNET);
        }
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.RECEIVE_BOOT_COMPLETED)) {
            EasyPermissions.requestPermissions(this, "需要访问网络", 1, Manifest.permission.RECEIVE_BOOT_COMPLETED);
        }

        initViews();
        MyTools.hiddenBottom(this);
    }

    private void initViews() {
        try {
            YinlianToken.mainTest();
        }catch (Exception e){}

//        String r = YinlianToken.testSHA256_STRING("e");
        HttpReauestUrl.getToken(this,false);
        titleView = findViewById(R.id.mytitle);
        numberView = findViewById(R.id.mynumberview);
        numberView.setAffirmBtVisibility(false);
        moneyEdittext = findViewById(R.id.money);

//        moneyEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus){
//                    numberView.setEditer(moneyEdittext);
//                    MyTools.hideInputManager(MainActivity.this,v);
//                }else{
//                    numberView.clearEditer();
//                }
//            }
//        });
        numberView.setEditer(moneyEdittext);
        moneyEdittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        numberView.setOnClickedVipListener(new NumberView.OnVipClickedListener() {
            @Override
            public void onVipClicked(NumberView view) {
                String str = view.getEditerText();
                if (MyTools.stringToFloat(str) == 0f){
                    ToastUtil.showToast(MainActivity.this,"请输入大于0的金额");
                    return;
                }
//                OnePayObject onePayObject = new OnePayObject();
//                onePayObject.setVipPay(true);
//
//
//                PayObjectSingleInsance.getInstance().setOnePayObject(onePayObject);
//                Intent i  = new Intent(MainActivity.this, PaywayActivity.class);
//                startActivity(i);
            }
        });

        numberView.setOnClickedNoVipListener(new NumberView.OnNoVipClickedListener() {
            @Override
            public void onNoVipClicked(NumberView view) {
                String str = view.getEditerText();
                float totalMoney = MyTools.stringToFloat(str);
                if (totalMoney == 0f){
                    ToastUtil.showToast(MainActivity.this,"请输入大于0的金额");
                    return;
                }
                OnePayObject onePayObject = new OnePayObject();
                onePayObject.setTotalPayMoney(totalMoney);
                onePayObject.setPayedMoney(0f);
                onePayObject.setWillPayMoney(totalMoney);
                onePayObject.setCurrentWillPayMoney(totalMoney);
                onePayObject.setVipPay(false);
                onePayObject.setDiscount(0f);
                onePayObject.setPayID(MyTools.getOnePayID());
                onePayObject.setShouyinYuan(EmployeeSingleInstance.getInstance().getOneEmployee());
                onePayObject.setPayedOK(false);
                view.setEditerText(getShowString(totalMoney));

                PayObjectSingleInsance.getInstance().setOnePayObject(onePayObject);
                Intent i  = new Intent(MainActivity.this, PaywayActivity.class);
                startActivity(i);
            }
        });

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
                MyDialog.showToast(MainActivity.this,"看见了");
                startActivity(new Intent(MainActivity.this,EmployeActivity.class));
            }
        });
        return toolbar;
    }
    public String getShowString(float money){
        return "￥" + MyTools.floatToLastTwoString(money);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onBackPressed() {

    }

}
