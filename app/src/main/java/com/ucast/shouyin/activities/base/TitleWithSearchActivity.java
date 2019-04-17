package com.ucast.shouyin.activities.base;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ucast.shouyin.MainActivity;
import com.ucast.shouyin.R;
import com.ucast.shouyin.num_view.NumberView;
import com.ucast.shouyin.tools.MyDialog;
import com.ucast.shouyin.tools.MyTools;

public class TitleWithSearchActivity extends AppCompatActivity {
    ImageView back;
    LinearLayout ll_right_clicked;
    EditText editSearch;
    NumberView numberView;

    public final static String CAMERAKEY = "camera_type";
    public final static String RESULT = "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_with_search);

        editSearch = findViewById(R.id.search_et);
        back = findViewById(R.id.search_back);
        ll_right_clicked = findViewById(R.id.right_clicked_ll);

        numberView = findViewById(R.id.keybord);

        editSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    numberView.setEditer(editSearch);
//                    MyTools.hideInputManager(TitleWithSearchActivity.this,v);
                }else{
                    numberView.clearEditer();
                }
            }
        });

        editSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyTools.hideInputManager(TitleWithSearchActivity.this,v);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_right_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUcastCamera(0);
            }
        });


    }

    public void startUcastCamera(int type){
        try {
            Intent intent = new Intent();
            intent.setClassName("com.ucast.jnidiaoyong_print","com.ucast.jnidiaoyongdemo.erweima.view.mysaomiao.CaptureActivity");
            intent.putExtra(CAMERAKEY, type);
            startActivityForResult(intent, type);
        }catch (Exception e){
            MyDialog.showToast(this,"请安装墨盒管理App！");
        }
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

    }

}
