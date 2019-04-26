package com.ucast.shouyin.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ucast.shouyin.sql.manager.MyDbHelper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pj on 2019/1/28.
 */
public class MyTools {
    public static final String LOGIN_ID ="login_id";
    public static final String PASSWORD ="password";
    public static final String EMP_NAME ="emp_name";
    public static final String COMPANY_NAME ="company_name";
    public static final String GROUP_ID ="group_id";
    public static final String ROLE ="role";
    public static final String EMP_PHONENUMBER ="emp_phonenumber";
    public static final String EMP_EMIAL ="emp_emial";
    public static final String CREATE_DATE ="create_date";
    public static final String WORK_STATE ="work_state";
    public static final String OVERTIME_ID ="overtime_id";


    public static String encode(byte[] bstr) {
        return Base64.encodeToString(bstr, Base64.NO_WRAP);
    }


    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        try {
            return Base64.decode(str, Base64.NO_WRAP);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date stringToDate(String s) {
        Date time = null;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            time = sd.parse(s);
        } catch (java.text.ParseException e) {
            System.out.println("输入的日期格式有误！");
            e.printStackTrace();
        }
        return time;
    }


    public static long getIntToMillis(String str) {
        String str_date = str + " " + "00:00:00";
        Date date = stringToDate(str_date);
        return date.getTime();
    }

    public static String millisToDateString(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date;
        Date curDate = new Date(time);
        date = formatter.format(curDate);
        return date;
    }


    public static String millisToDateStringNoSpace(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String date;
        Date curDate = new Date(time);
        date = formatter.format(curDate);
        return date;
    }
    public static String millisToYinLianDateString(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String date;
        Date curDate = new Date(time);
        date = formatter.format(curDate);
        return date;
    }
    public static String millisToPayIdDateString(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
        String date;
        Date curDate = new Date(time);
        date = formatter.format(curDate);
        return date;
    }
    public static String millisToDateStringOnlyYMD(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        Date curDate = new Date(time);
        date = formatter.format(curDate);
        return date;
    }

    public static String getCreateTime(){
        return millisToDateString(System.currentTimeMillis());
    }

    public static String getCreateTimeOnlyDay(){
        return millisToDateStringOnlyYMD(System.currentTimeMillis());
    }

    /** Get the STB MacAddress*/
    public static String getMacAddress(){
        try {
            return loadFileAsString("/sys/class/net/eth0/address") .toUpperCase().substring(0, 17).replace(':','-');
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String loadFileAsString(String filePath) throws java.io.IOException{
        if (! new File(filePath).exists())
            return "";
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024]; int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    public static void writeToFile(String path , String data){
        try{
            File f = new File(path);
            FileOutputStream fout = new FileOutputStream(f , true);
            BufferedOutputStream buff = new BufferedOutputStream(fout);
            buff.write((data + "\r\n").getBytes());
            buff.flush();
            buff.close();
        }catch (Exception e){
            System.out.print(e.toString());
        }
    }

    public static void hiddenBottom(Activity activity){
        if (!Config.isHiddenBottom)
            return;
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    //将屏幕旋转锁定
    public static int setRoat(Context context) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
        //得到是否开启
        int flag = Settings.System.getInt(context.getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0);
        return flag;
    }

    public static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void writeSimpleLog(String log){
        writeToFile(Config.LOGPATH,log);
    }

    /**
     *  将指定byte数组以16进制的形式返回
     * */
    public static String printHexString(byte[] b) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            if(b[i] == 0x00){
                r.append("00 ");
                continue;
            }else if(b[i] == 0xFF){
                r.append("FF ");
                continue;
            }
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r.append(hex.toUpperCase() + " ");
        }
        return r.toString();
    }

    public static byte getSumJiaoYan(byte[] res){
        int sum = 0;
        for (int i = 0; i < res.length; i++) {
            sum += res[i];
        }
        return (byte) (sum & 0xFF);
    }

    public static byte[] getSendData(byte[] res){
        byte jiaoYan = getSumJiaoYan(res);
        byte[] dest = new byte[res.length + 1];
        System.arraycopy(res,0,dest,0,res.length);
        dest[dest.length - 1] = jiaoYan;
        return dest;
    }


    public static float stringToFloat(String text){
        float money = 0f;
        if (text.length() >= 2){
            String str = text.substring(1,text.length());
            try {
                money = Float.parseFloat(str);
            }catch (Exception e){

            }
        }
        return money;
    }

    public static String floatToLastTwoString(float one){
        String result = String.format("%.2f",one);
        return result;
    }

    public static int stringToInt(String text){
        int money = 0;
        if (text.length() >= 2){
            String str = text.substring(1,text.length());
            try {
                float moneyFloat = Float.parseFloat(str);
                money =(int)( moneyFloat * 100f);
            }catch (Exception e){

            }
        }
        return money;
    }

    public static String getOnePayID(){
        String id = Config.DEVICE_ID + millisToPayIdDateString(System.currentTimeMillis()) + MyDbHelper.getInstance().getPayIdNumber();
        return id;
    }

    /**
     * 隐藏输入软键盘
     * @param context
     * @param view
     */
    public static void hideInputManager(Context context,View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view !=null && imm != null){
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);  //强制隐藏
        }
    }
}
