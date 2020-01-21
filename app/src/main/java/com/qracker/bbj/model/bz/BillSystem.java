package com.qracker.bbj.model.bz;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
        ArrayList<Bill> newBills = new ArrayList<>();
        newBills.add(new Bill(comment));
        newBills.addAll(this.bills);
        this.bills = newBills;
    }

    public void addBill(Bill bill) {
        ArrayList<Bill> newBills = new ArrayList<>();
        newBills.add(bill);
        newBills.addAll(this.bills);
        this.bills = newBills;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sort() {
        this.bills.sort(Comparator.comparing(Bill::isFinished).thenComparing(Bill::getDate));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Transfer> getSolution(int index) {
        ArrayList<Transfer> solutions = bills.get(index).getSolution();
        sort();
        return solutions;
    }
}
