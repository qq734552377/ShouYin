package com.ucast.shouyin.entities;

import com.ucast.shouyin.R;
import com.ucast.shouyin.exception.ExceptionApplication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pj on 2019/4/16.
 */
public enum PayType implements Serializable {
    CHUZHIKA(ExceptionApplication.getInstance().getString(R.string.chuzhika),R.string.chuzhika,1),
    SAOMAZHIFU(ExceptionApplication.getInstance().getString(R.string.saomazhifu),R.string.saomazhifu,2),
    ZHIFUBAO(ExceptionApplication.getInstance().getString(R.string.zhifubao),R.string.zhifubao,3),
    WEIXIN(ExceptionApplication.getInstance().getString(R.string.weixin),R.string.weixin,4),
    YINHANGKA(ExceptionApplication.getInstance().getString(R.string.yinhangka),R.string.yinhangka,5),
    HUIYUANKA(ExceptionApplication.getInstance().getString(R.string.huiyuan),R.string.huiyuan,6),
    ZHAOLING(ExceptionApplication.getInstance().getString(R.string.zhaoling),R.string.zhaoling,7),
    XIANJIN(ExceptionApplication.getInstance().getString(R.string.xianjin),R.string.xianjin,8);

    private static final Map<Integer ,PayType> ALLPAYTYPES = new HashMap<>();
    static {
        for (PayType one : PayType.values()) {
            ALLPAYTYPES.put(one.getId(),one);
        }
    }

    private String name;
    private int nameID;
    private int id;
    private PayType(String name,int nameID,int id){
        this.name = name;
        this.nameID = nameID;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getNameID(){
        return this.nameID;
    }

    public int getId(){
        return  this.id;
    }

    public static PayType getPaytypeEnum(Integer id){
        return ALLPAYTYPES.get(id);
    }
}
