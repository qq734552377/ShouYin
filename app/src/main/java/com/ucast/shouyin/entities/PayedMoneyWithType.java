package com.ucast.shouyin.entities;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pj on 2019/4/16.
 */
public class PayedMoneyWithType {

    private PayType payType;
    private float moneyNumber;
    private String payerID;
    private String createTime;

    public PayedMoneyWithType() {
    }

    public PayedMoneyWithType(PayType payType, float moneyNumber,String payerID,String createTime) {
        this.payType = payType;
        this.moneyNumber = moneyNumber;
        this.payerID = payerID;
        this.createTime = createTime;
    }
    @JSONField(name = "paytype")
    public int getPaytypeInt(){
        return payType.getId();
    }
    @JSONField(name = "paytype")
    public void setPaytypeInt(int paytypeInt){
        this.payType = PayType.getPaytypeEnum(paytypeInt);
    }

    @JSONField(serialize = false)
    public PayType getPayType() {
        return payType;
    }

    @JSONField(deserialize = false)
    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public float getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(float moneyNumber) {
        this.moneyNumber = moneyNumber;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
