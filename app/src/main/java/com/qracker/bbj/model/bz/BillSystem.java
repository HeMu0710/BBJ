package com.qracker.bbj.model.bz;

import java.util.ArrayList;

/**
 * @program: BBJ
 * @description: 均摊账单系统
 * @author: HeMu-qracker
 * @create: 2020-01-12 23:21
 **/
public class BillSystem {
    private ArrayList<Bill> bills;
    private static BillSystem instance = new BillSystem();
    public BillSystem() {
        bills = new ArrayList<>();
    }

    public BillSystem getInstance() {
        /**
        * @Description: 实现单例模式，获取该单例对象
        * @Param: []
        * @return: com.qracker.bbj.model.bz.BillSystem
        * @Author: HeMu-qracker
        * @Date: 2020/1/12
        */
        return this.instance;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void addBill(double... expends) {
        this.bills.add(new Bill(expends));
    }
}
