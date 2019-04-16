package com.ucast.shouyin.entities;

/**
 * Created by pj on 2019/4/16.
 */
public class PayedMoneyWithType {

    private PayType payType;
    private float moneyNumber;

    public PayedMoneyWithType(PayType payType, float moneyNumber) {
        this.payType = payType;
        this.moneyNumber = moneyNumber;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public float getMoneyNumber() {
        return moneyNumber;
    }

    public void setMoneyNumber(float moneyNumber) {
        this.moneyNumber = moneyNumber;
    }
}
