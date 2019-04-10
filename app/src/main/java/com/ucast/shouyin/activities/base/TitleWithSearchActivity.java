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
import android.widget.TextView;

import com.ucast.shouyin.MainActivity;
import com.ucast.shouyin.R;
import com.ucast.shouyin.num_view.NumberView;
import com.ucast.shouyin.tools.MyDialog;
import com.ucast.shouyin.tools.MyTools;

public class TitleWithSearchActivity extends AppCompatActivity {
    ImageView back;
    ImageView scan;
    EditText editSearch;
    NumberView numberView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_with_search);
//
//        editSearch = findViewById(R.id.search_et);
//        back = findViewById(R.id.search_back);
//        scan = findViewById(R.id.search_scan);
//
//
//        numberView = findViewById(R.id.keybord);
//
//        editSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus){
//                    numberView.setEditer(editSearch);
//                    MyTools.hideInputManager(TitleWithSearchActivity.this,v);
//                }else{
//                    numberView.clearEditer();
//                }
//            }
//        });
//
//        editSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyTools.hideInputManager(TitleWithSearchActivity.this,v);
//            }
//        });




    }


}
