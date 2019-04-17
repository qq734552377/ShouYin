package com.ucast.shouyin.entities;

import com.ucast.shouyin.tools.MyTools;

import java.util.ArrayList;

/**
 * Created by pj on 2019/4/16.
 */
public class OnePayObject {
    private float totalPayMoney;
    private float payedMoney;
    private float willPayMoney;
    private float currentWillPayMoney;
    private boolean isVipPay = false;
    private float discount;
    private String payID;
    private Employee shouyinYuan;
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

    public float getCurrentWillPayMoney() {
        return currentWillPayMoney;
    }

    public void setCurrentWillPayMoney(float currentWillPayMoney) {
        this.currentWillPayMoney = currentWillPayMoney;
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

    public Employee getShouyinYuan() {
        return shouyinYuan;
    }

    public void setShouyinYuan(Employee shouyinYuan) {
        this.shouyinYuan = shouyinYuan;
    }

    public boolean isPayedOK() {
        return isPayedOK;
    }

    public void setPayedOK(boolean payedOK) {
        isPayedOK = payedOK;
    }

    public String getAllPayedMoney(){
        if (allPayedMoneys.size() == 0){
            return "￥0.00";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allPayedMoneys.size(); i++) {
            PayedMoneyWithType item = allPayedMoneys.get(i);
            sb.append(item.getPayType().getName());
            sb.append("  ￥");
            sb.append(MyTools.floatToLastTwoString(item.getMoneyNumber()));
        }
        return sb.toString();
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
        setCurrentWillPayMoney(0f);
        setVipPay(false);
        setDiscount(0f);
        setPayedOK(false);
        allPayedMoneys.clear();
    }
}
