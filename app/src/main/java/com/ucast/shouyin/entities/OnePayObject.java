package com.ucast.shouyin.entities;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;

import com.alibaba.fastjson.annotation.JSONField;
import com.ucast.shouyin.R;
import com.ucast.shouyin.exception.ExceptionApplication;
import com.ucast.shouyin.tools.Config;
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
//    private float zhaolingMoney;
    private boolean isVipPay = false;
    private float discount;
    private String payID;
    private Employee shouyinYuan;
    private boolean isPayedOK;
    private String createTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ArrayList<PayedMoneyWithType> getAllPayedMoneys() {
        return allPayedMoneys;
    }

    public void setAllPayedMoneys(ArrayList<PayedMoneyWithType> allPayedMoneys) {
        this.allPayedMoneys = allPayedMoneys;
    }

    @JSONField(serialize = false)
    public String getAllPayedType(){
        if (allPayedMoneys.size() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allPayedMoneys.size(); i++) {
            PayedMoneyWithType item = allPayedMoneys.get(i);
            sb.append(item.getPayType().getName() + "、");
        }
        return sb.toString();
    }
    @JSONField(serialize = false)
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
            if (i != allPayedMoneys.size() - 1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    @JSONField(serialize = false)
    public boolean addOnePayedMoneyWithType(PayedMoneyWithType oneItem){
        if (isPayedOK)
            return false;
        this.allPayedMoneys.add(oneItem);
        this.payedMoney += oneItem.getMoneyNumber();
        this.willPayMoney -= oneItem.getMoneyNumber();
        this.currentWillPayMoney = this.willPayMoney;
        if ((this.payedMoney + this.discount) == this.totalPayMoney){//结账完毕
            isPayedOK = true;
            return true;
        }
        if(oneItem.getPayType() == PayType.XIANJIN && (this.payedMoney + this.discount) > this.totalPayMoney){// 付钱过多  需添加找零
            isPayedOK = true;
            PayedMoneyWithType payedMoneyWithType = new PayedMoneyWithType(PayType.ZHAOLING,this.willPayMoney, ExceptionApplication.getInstance().getString(R.string.defult_xianjin_payer_id),MyTools.getCreateTime());
            this.allPayedMoneys.add(payedMoneyWithType);
            return true;
        }
        return true;
    }
    @JSONField(serialize = false)
    public String getShowMoneyString(){
        if (allPayedMoneys.size() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allPayedMoneys.size(); i++) {
            PayedMoneyWithType item = allPayedMoneys.get(i);
            String pay_type_str = item.getPayType().getName();
            String pay_number_str = "￥" + MyTools.floatToLastTwoString(Math.abs(item.getMoneyNumber()));
            int spaceNum = 0;
            try {
               spaceNum = Config.ONELINEMONEYSHOWNUMBER - pay_type_str.getBytes(Config.CHARSET).length - pay_number_str.getBytes(Config.CHARSET).length;
            }catch (Exception e){

            }
            sb.append(pay_type_str);
            for (int j = 0; j < spaceNum; j++) {
                sb.append(" ");
            }
            sb.append(pay_number_str);

            if (i != allPayedMoneys.size() - 1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    @JSONField(serialize = false)
    public float getAllPayedMoneyFloat(){
        float money = 0f;
        for (int i = 0; i < allPayedMoneys.size(); i++) {
            PayedMoneyWithType item = allPayedMoneys.get(i);
            if (item.getPayType() == PayType.ZHAOLING){
                continue;
            }
            money += item.getMoneyNumber();
        }
        return money;
    }

    @JSONField(serialize = false)
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
