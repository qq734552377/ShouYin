package com.ucast.shouyin.entities;

import java.util.ArrayList;

/**
 * Created by pj on 2019/4/16.
 */
public class OnePayObject {
    private float totalPayMoney;
    private float payedMoney;
    private float willPayMoney;
    private boolean isVipPay = false;
    private float discount;
    private String payID;
    private boolean isPayedOK;
    private ArrayList<PayedMoneyWithType> allPayedMoneys;

    public OnePayObject() {
        allPayedMoneys = new ArrayList<>();
    }

    public float getTotalPayMoney() {
        return totalPayMoney;
    }

    public void setTotalPayMoney(float totalPayMoney) {
        this.totalPayMoney = totalPayMoney;
    }

    public float getPayedMoney() {
        return payedMoney;
    }

    public void setPayedMoney(float payedMoney) {
        this.payedMoney = payedMoney;
    }

    public float getWillPayMoney() {
        return willPayMoney;
    }

    public void setWillPayMoney(float willPayMoney) {
        this.willPayMoney = willPayMoney;
    }

    public boolean isVipPay() {
        return isVipPay;
    }

    public void setVipPay(boolean vipPay) {
        isVipPay = vipPay;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getPayID() {
        return payID;
    }

    public void setPayID(String payID) {
        this.payID = payID;
    }

    public boolean isPayedOK() {
        return isPayedOK;
    }

    public void setPayedOK(boolean payedOK) {
        isPayedOK = payedOK;
    }

    public void addOnePayedMoneyWithType(PayedMoneyWithType oneItem){
        if (isPayedOK)
            return;
        this.allPayedMoneys.add(oneItem);
        this.payedMoney += oneItem.getMoneyNumber();
        this.willPayMoney -= oneItem.getMoneyNumber();
        if ((this.payedMoney + this.discount) == this.totalPayMoney){
            isPayedOK = true;
        }
    }

    public void initAllThing(){
        setPayID(null);
        setTotalPayMoney(0f);
        setPayedMoney(0f);
        setWillPayMoney(0f);
        setVipPay(false);
        setDiscount(0f);
        setPayedOK(false);
        allPayedMoneys.clear();
    }
}
