package com.ucast.shouyin.entities;

import java.util.ArrayList;

/**
 * Created by pj on 2019/4/16.
 */
public class PayObjectSingleInsance {
    private static PayObjectSingleInsance instance;

    private OnePayObject onePayObject;

    private PayObjectSingleInsance() {
    }

    public static PayObjectSingleInsance getInstance(){
        if (instance == null){
            synchronized (PayObjectSingleInsance.class){
                if (instance == null){
                    instance = new PayObjectSingleInsance();
                }
            }
        }
        return instance;
    }

    public OnePayObject getOnePayObject() {
        return onePayObject;
    }

    public void setOnePayObject(OnePayObject onePayObject) {
        this.onePayObject = onePayObject;
    }
}
