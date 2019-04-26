package com.ucast.shouyin.sql.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ucast.shouyin.tools.Config;

/**
 * Created by pj on 2019/4/24.
 */
public class MyDbOpenHelper extends SQLiteOpenHelper {
    public static String ALLPAYOBJTABLENAME = "allpayobj";
    public static String PAYNUMBERTABLENAME = "paynumber";

    public static String id = "id";
    public static String allpayobjPayMsg = "pay_msg";
    public static String createTime = "create_time";
    public static String createTimeOnlyDay = "create_time_only_day";
    public static String orderById = id + " desc";

    public static String payNumber = "pay_number";

    private SQLiteDatabase db;

    public MyDbOpenHelper(Context context) {
        super(context, Config.DBNAME, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ALLPAYOBJTABLENAME + " ("
                + id +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + allpayobjPayMsg + " TEXT,"
                + createTime + " VARCHAR(50),"
                + createTimeOnlyDay +" VARCHAR(50))");

        db.execSQL("CREATE TABLE " + PAYNUMBERTABLENAME + " ("
                + id +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + payNumber + " INTEGER,"
                + createTimeOnlyDay + " VARCHAR(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
