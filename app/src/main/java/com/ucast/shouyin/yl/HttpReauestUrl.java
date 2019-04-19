package com.ucast.shouyin.yl;

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

}
