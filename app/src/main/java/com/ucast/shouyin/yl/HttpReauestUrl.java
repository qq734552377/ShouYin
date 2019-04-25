package com.ucast.shouyin.yl;

import android.content.Context;

import com.ucast.shouyin.R;
import com.ucast.shouyin.events.SaoMaZhiFuResultEvent;
import com.ucast.shouyin.events.StartSaoMaZhiFuEvent;
import com.ucast.shouyin.tools.MyTools;
import com.ucast.shouyin.tools.SavePasswd;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pj on 2019/4/18.
 */
public class HttpReauestUrl {
    public static String HOST = "http://58.247.0.18:29015";
//    public static String HOST = "https://api-mop.chinaums.com";


    public static String ACCESSTOKENURL = HOST + "/v1/token/access";
    public static String JIHUOURL = HOST + "/v2/poslink/transaction/activeterminal";
    public static String PAYURL = HOST + "/v2/poslink/transaction/pay";
    public static String REVERSEPAYURL = HOST + "/v2/poslink/transaction/reversepayment";
    public static String VOIDPAYURL = HOST + "/v2/poslink/transaction/voidpayment";
    public static String REFUNDPAYURL = HOST + "/v2/poslink/transaction/refund";
    public static String QUERYURL = HOST + "/v2/poslink/transaction/query";
    public static String QUERYREFUNDURL = HOST + "/v2/poslink/transaction/query-refund";


    public static void getToken(Context context,boolean isZhifu){
            try {
                RequestParams params = new RequestParams(ACCESSTOKENURL);
                params.addBodyParameter(context.getString(R.string.yl_appid),YinlianToken.appId);
                params.addBodyParameter(context.getString(R.string.yl_timestamp),YinlianToken.timestamp);
                params.addBodyParameter(context.getString(R.string.yl_nonce),YinlianToken.nonce);

                params.addBodyParameter(context.getString(R.string.yl_signature), YinlianToken.testSHA1_STRING(YinlianToken.str1_C));

                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        String r = result;

                        System.out.print(r);

                        if (isZhifu){
                            EventBus.getDefault().postSticky(new StartSaoMaZhiFuEvent());
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        System.out.print(ex.toString());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startZhifu(Context context,SaoMaZhiFuObj saoMaZhiFuObj){//money 单位为分
        try {
            RequestParams params = new RequestParams(PAYURL);
            params.addBodyParameter(context.getString(R.string.yl_merchantCode), SavePasswd.getInstace().get(context.getString(R.string.yl_merchantCode)));
            params.addBodyParameter(context.getString(R.string.yl_terminalCode), SavePasswd.getInstace().get(context.getString(R.string.yl_terminalCode)));
            params.addBodyParameter(context.getString(R.string.yl_transactionAmount),saoMaZhiFuObj.getMoney() + "");
            params.addBodyParameter(context.getString(R.string.yl_transactionCurrencyCode), "156");
            params.addBodyParameter(context.getString(R.string.yl_merchantOrderId), saoMaZhiFuObj.getPayId());
            params.addBodyParameter(context.getString(R.string.yl_merchantRemark), saoMaZhiFuObj.getRemark());
            params.addBodyParameter(context.getString(R.string.yl_payCode), context.getString(R.string.yl_payMode_CODESCAN));



            params.addHeader(context.getString(R.string.yl_authorization),SavePasswd.getInstace().get(SavePasswd.TOKEN));
            x.http().post(params, new Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {
                    String r = result;

                    System.out.print(r);

                    EventBus.getDefault().postSticky(new SaoMaZhiFuResultEvent(true));

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    System.out.print(ex.toString());
                    EventBus.getDefault().postSticky(new SaoMaZhiFuResultEvent(false));
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
