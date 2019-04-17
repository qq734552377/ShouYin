package com.ucast.shouyin.entities;

/**
 * Created by pj on 2019/4/17.
 */
public class Employee {
    private String id;
    private String name;
    private Store store;

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
