package com.ucast.shouyin.sql.entity;

import com.alibaba.fastjson.JSON;
import com.ucast.shouyin.entities.Employee;
import com.ucast.shouyin.entities.OnePayObject;
import com.ucast.shouyin.tools.MyTools;

/**
 * Created by pj on 2019/4/24.
 */
public class OnePayedOkObj {
    private int id;
    private String payMsg;
    private String createTime;
    private String createTimeOnlyDay;

    public OnePayedOkObj() {
        setCurTimeToCreateTime();
        setCurTimeToCreateTimeOnlyDay();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayMsg() {
        return payMsg;
    }

    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeOnlyDay() {
        return createTimeOnlyDay;
    }

    public void setCreateTimeOnlyDay(String createTimeOnlyDay) {
        this.createTimeOnlyDay = createTimeOnlyDay;
    }

    public OnePayObject getOnePayObj(){
        OnePayObject one = (OnePayObject) JSON.parseObject(this.payMsg,OnePayObject.class);
        return one;
    }
    public void setOnePayObjJsonStr(OnePayObject onePayObject){
        this.payMsg = JSON.toJSONString(onePayObject);
    }
    public void setCurTimeToCreateTime(){
        setCreateTime(MyTools.getCreateTime());
    }
    public void setCurTimeToCreateTimeOnlyDay(){
        setCreateTimeOnlyDay(MyTools.getCreateTimeOnlyDay());
    }
}
