package com.ucast.shouyin;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ucast.shouyin.activities.base.EmployeActivity;
import com.ucast.shouyin.activities.other.PaywayActivity;
import com.ucast.shouyin.num_view.MyTitleView;
import com.ucast.shouyin.num_view.NumberView;
import com.ucast.shouyin.tools.MyDialog;
import com.ucast.shouyin.tools.MyTools;

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
    }

    private void initViews() {
        titleView = findViewById(R.id.mytitle);
        numberView = findViewById(R.id.mynumberview);
        numberView.setAffirmBtVisibility(false);
        moneyEdittext = findViewById(R.id.money);

        moneyEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    numberView.setEditer(moneyEdittext);
                    MyTools.hideInputManager(MainActivity.this,v);
                }else{
                    numberView.clearEditer();
                }
            }
        });
        moneyEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTools.hideInputManager(MainActivity.this,v);
            }
        });

        numberView.setOnClickedVipListener(new NumberView.OnVipClickedListener() {
            @Override
            public void onVipClicked(NumberView view) {
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
