package com.qracker.bbj.ui.aatool;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qracker.bbj.model.bz.Bill;
import com.qracker.bbj.model.bz.BillSystem;

import java.util.ArrayList;

public class AAtoolViewModel extends ViewModel {

    private BillSystem billSystem;

    public AAtoolViewModel() {
        billSystem = BillSystem.getInstance();
        Bill bill = new Bill("寝室均摊账本");
        bill.addMember("何牧",50);
        bill.addMember("梁理维",40);
        bill.addExpend("何牧",50);
        Bill bill1 = new Bill("1.7日聚会");
        bill1.addMember("梁理维",40);
        bill1.addMember("王泳淇",80);
        bill1.addMember("唐铭聪",5);
        Bill bill2 = new Bill("1.1日聚会");
        bill2.addMember("何牧",50);
        bill2.addMember("梁理维",40);
        bill2.addMember("王泳淇",80);
        bill2.addMember("唐铭聪",5);
        bill2.addExpend("何牧",50);
        billSystem.addBill(bill);
        billSystem.addBill(bill1);
        billSystem.addBill(bill2);
    }

    public ArrayList<Bill> getBillList() {
        return this.billSystem.getBills();
    }
}