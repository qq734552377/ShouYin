package com.ucast.shouyin.events;

import com.ucast.shouyin.entities.PayType;

/**
 * Created by pj on 2019/4/25.
 */
public class SaoMaZhiFuResultEvent {
    boolean isOk ;
    String  info;

    PayType payType;

    public SaoMaZhiFuResultEvent(boolean isOk) {
        this.isOk = isOk;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }
}
