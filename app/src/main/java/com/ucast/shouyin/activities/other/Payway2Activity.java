package com.ucast.shouyin.activities.other;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ucast.shouyin.MainActivity;
import com.ucast.shouyin.R;
import com.ucast.shouyin.entities.OnePayObject;
import com.ucast.shouyin.entities.PayObjectSingleInsance;
import com.ucast.shouyin.entities.PayType;
import com.ucast.shouyin.num_view.MyInputPasswordDialog;
import com.ucast.shouyin.num_view.NumberView;
import com.ucast.shouyin.tools.MyDialog;
import com.ucast.shouyin.tools.MyTools;
import com.ucast.shouyin.tools.ToastUtil;

public class Payway2Activity extends AppCompatActivity implements NumberView.OnConfirmClickedListener{
    public final static String CAMERAKEY = "camera_type";
    public final static String RESULT = "result";
    private PayType payType;
    private OnePayObject onePayObject;
    private NumberView numberView;
    private TextView tv_payedMoney;
    private TextView tv_willPayMoney;

    private TextView tv_payType;
    private EditText currentWillPayMoneyNumber;

    private MyInputPasswordDialog inputPasswordDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payway2);
        initToolbar(R.id.toolbar,R.id.toolbar_title,getTitleName());
        initViews();
    }

    private void initViews() {
        if (onePayObject == null)
            onePayObject = PayObjectSingleInsance.getInstance().getOnePayObject();

        tv_payedMoney = findViewById(R.id.payed_money);
        tv_payedMoney.setText(onePayObject.getAllPayedMoney());

        tv_willPayMoney = findViewById(R.id.will_pay_money);
        tv_willPayMoney.setText(getShowString(onePayObject.getWillPayMoney()));

        numberView  = findViewById(R.id.mynumberview);
        numberView.setOnClickedConfirmListener(this);

        tv_payType = findViewById(R.id.pay_type_tv);
        tv_payType.setText(getTitleName());

        currentWillPayMoneyNumber = findViewById(R.id.pay_money_number);
        currentWillPayMoneyNumber.setText(getShowString(onePayObject.getCurrentWillPayMoney()));

        numberView.setEditer(currentWillPayMoneyNumber);
        currentWillPayMoneyNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        if (inputPasswordDialog == null)
            inputPasswordDialog = MyDialog.createInputPasswordDialog(this, new MyInputPasswordDialog.OnInputCompleteListener() {
                @Override
                public void onInputComplete(Dialog dialog, String password) {
                    MyDialog.showToast(Payway2Activity.this,password);
                    dialog.dismiss();
                }
            });
    }

    public int getTitleName(){
        int nameID = R.string.saomazhifu;
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (payType == null)
            payType = (PayType) bundle.getSerializable(getString(R.string.pay_type));
        if (payType != null)
            nameID = payType.getNameID();
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
        if (payType == PayType.SAOMAZHIFU) {
            String str = view.getEditerText();
            float currentWillPayMoney = MyTools.stringToFloat(str);
            if (currentWillPayMoney == 0f){
                ToastUtil.showToast(Payway2Activity.this,"请输入大于0的金额");
                return;
            }
            onePayObject.setCurrentWillPayMoney(currentWillPayMoney);

            startUcastCamera(0);

            return;
        }
//        inputPasswordDialog.setTishiMsg("输入储值卡密码");
//        inputPasswordDialog.setMoneyNumber("$10.00");
//        inputPasswordDialog.show();
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
    public String getShowString(float money){
        return "￥" + MyTools.floatToLastTwoString(money);
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
        Intent i = new Intent(this, PaywayResultActivity.class);
        i.putExtra(getString(R.string.scan_result), r);
        startActivity(i);
    }
}
