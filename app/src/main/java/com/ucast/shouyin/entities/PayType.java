package com.ucast.shouyin.entities;

import com.ucast.shouyin.R;
import com.ucast.shouyin.exception.ExceptionApplication;

import java.io.Serializable;

/**
 * Created by pj on 2019/4/16.
 */
public enum PayType implements Serializable {
    CHUZHIKA(ExceptionApplication.getInstance().getString(R.string.chuzhika),R.string.chuzhika),
    SAOMAZHIFU(ExceptionApplication.getInstance().getString(R.string.saomazhifu),R.string.saomazhifu),
    ZHIFUBAO(ExceptionApplication.getInstance().getString(R.string.zhifubao),R.string.zhifubao),
    WEIXIN(ExceptionApplication.getInstance().getString(R.string.weixin),R.string.weixin),
    YINHANGKA(ExceptionApplication.getInstance().getString(R.string.yinhangka),R.string.yinhangka),
    HUIYUANKA(ExceptionApplication.getInstance().getString(R.string.huiyuan),R.string.huiyuan),
    XIANJIN(ExceptionApplication.getInstance().getString(R.string.xianjin),R.string.xianjin);

    private String name;
    private int nameID;
    private PayType(String name,int nameID){
        this.name = name;
        this.nameID = nameID;
    }

    public String getName(){
        return this.name;
    }

    public int getNameID(){
        return this.nameID;
    }
}
