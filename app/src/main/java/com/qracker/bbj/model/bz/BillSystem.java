package com.qracker.bbj.model.bz;

import com.qracker.bbj.model.tool.AddInFront;

import java.util.ArrayList;

/**
 * @program: BBJ
 * @description: 均摊账单系统
 * @author: HeMu-qracker
 * @create: 2020-01-12 23:21
 **/
public class BillSystem implements AddInFront<Bill> {
    private ArrayList<Bill> bills;
    private static BillSystem instance = new BillSystem();
    public BillSystem() {
        bills = new ArrayList<>();
    }

    public static BillSystem getInstance() {
        /**
        * @Description: 实现单例模式，获取该单例对象
        * @Param: []
        * @return: com.qracker.bbj.model.bz.BillSystem
        * @Author: HeMu-qracker
        * @Date: 2020/1/12
        */
        return instance;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void addBill(String comment) {
        this.bills = addInFront(this.bills,new Bill(comment));
    }

    public void addBill(Bill bill) {
        this.bills = addInFront(this.bills,bill);
    }

    @Override
    public ArrayList<Bill> addInFront(ArrayList<Bill> arrayList, Bill bill) {
        ArrayList<Bill> newArrayList = new ArrayList<>();
        newArrayList.add(bill);
        newArrayList.addAll(arrayList);
        return newArrayList;
    }
}
