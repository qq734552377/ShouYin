package com.ucast.shouyin.entities;

import com.ucast.shouyin.tools.SavePasswd;

/**
 * Created by pj on 2019/4/17.
 */
public class EmployeeSingleInstance {
    private static EmployeeSingleInstance instance;
    Employee oneEmployee;

    private EmployeeSingleInstance() {
    }

    public static EmployeeSingleInstance getInstance(){
        if (instance == null){
            synchronized (EmployeeSingleInstance.class){
                if (instance == null)
                    instance = new EmployeeSingleInstance();
            }
        }
        return instance;
    }

    public Employee getOneEmployee() {
        if (oneEmployee == null){
            Employee employee = new Employee();
            employee.setId(SavePasswd.getInstace().get(SavePasswd.EMPLOYEEID,"001"));
            employee.setName(SavePasswd.getInstace().get(SavePasswd.EMPLOYEENAME,"王小姐"));
            Store store = new Store();
            store.setId(SavePasswd.getInstace().get(SavePasswd.STOREID,"1001"));
            store.setName(SavePasswd.getInstace().get(SavePasswd.STORENAME,"龙龙小店"));
            employee.setStore(store);
            oneEmployee = employee;
        }
        return oneEmployee;
    }

    public void setOneEmployee(Employee oneEmployee) {
        this.oneEmployee = oneEmployee;
    }
}
