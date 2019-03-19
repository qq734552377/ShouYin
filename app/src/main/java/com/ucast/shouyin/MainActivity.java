package com.ucast.shouyin;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
