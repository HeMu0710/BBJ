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

    public BillSystem() {
        bills = new ArrayList<>();
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void addBill(String comment) {
        bills.add(0, new Bill(comment));
    }

    public void addBill(Bill bill) {
        this.bills.add(0, bill);
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
