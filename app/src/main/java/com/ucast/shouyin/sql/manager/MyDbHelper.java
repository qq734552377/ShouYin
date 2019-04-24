package com.ucast.shouyin.sql.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ucast.shouyin.exception.ExceptionApplication;
import com.ucast.shouyin.sql.entity.OnePayedOkObj;

import java.util.ArrayList;

/**
 * Created by pj on 2019/4/24.
 */
public class MyDbHelper {
    private static MyDbHelper instance = null;
    private SQLiteDatabase db;
    private MyDbOpenHelper myDbOpenHelper;
    private MyDbHelper(){
        if (myDbOpenHelper == null)
            myDbOpenHelper = new MyDbOpenHelper(ExceptionApplication.getInstance());
        db = myDbOpenHelper.getWritableDatabase();
    }

    public static MyDbHelper getInstance(){
        if (instance == null){
            synchronized (MyDbHelper.class){
                if (instance == null){
                    instance = new MyDbHelper();
                }
            }
        }
        return instance;
    }

    public long insertOneAllPayObj(OnePayedOkObj one){
        ContentValues values=new ContentValues();
//        values.put(MyDbOpenHelper.id,one.getId());
        values.put(MyDbOpenHelper.allpayobjPayMsg,one.getPayMsg());
        values.put(MyDbOpenHelper.allpayobjCreateTime,one.getCreateTimeOnlyDay());
        return db.insert(MyDbOpenHelper.ALLPAYOBJTABLENAME,null,values);
    }

    public ArrayList<OnePayedOkObj> getAllAllPayObj(){
        ArrayList<OnePayedOkObj> alls = new ArrayList<>();
        Cursor c = db.query(MyDbOpenHelper.ALLPAYOBJTABLENAME,null,null,null,null,null,MyDbOpenHelper.orderById);
        while (c.moveToNext()){
            OnePayedOkObj one = new OnePayedOkObj();
            one.setId(c.getInt(c.getColumnIndex(MyDbOpenHelper.id)));
            one.setPayMsg(c.getString(c.getColumnIndex(MyDbOpenHelper.allpayobjPayMsg)));
            one.setCreateTimeOnlyDay(c.getString(c.getColumnIndex(MyDbOpenHelper.allpayobjCreateTime)));
            alls.add(one);
        }
        return alls.size() > 0 ? alls : null;
    }

    public ArrayList<OnePayedOkObj> getAllAllPayObjByCreateTime(String timeOnlyDay){
        ArrayList<OnePayedOkObj> alls = new ArrayList<>();
        Cursor c = db.query(MyDbOpenHelper.ALLPAYOBJTABLENAME,null,MyDbOpenHelper.allpayobjCreateTime + "=?",new String[]{timeOnlyDay},null,null,MyDbOpenHelper.orderById);
        while (c.moveToNext()){
            OnePayedOkObj one = new OnePayedOkObj();
            one.setId(c.getInt(c.getColumnIndex(MyDbOpenHelper.id)));
            one.setPayMsg(c.getString(c.getColumnIndex(MyDbOpenHelper.allpayobjPayMsg)));
            one.setCreateTimeOnlyDay(c.getString(c.getColumnIndex(MyDbOpenHelper.allpayobjCreateTime)));
            alls.add(one);
        }
        return alls.size() > 0 ? alls : null;
    }
}
