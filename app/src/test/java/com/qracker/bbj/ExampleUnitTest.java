package com.qracker.bbj;

import org.junit.Test;

import com.google.gson.Gson;
import com.qracker.bbj.model.bc.AccountingSystem;
import com.qracker.bbj.model.bc.MoneyEvent;
import com.qracker.bbj.model.bz.Bill;
import com.qracker.bbj.model.bz.BillSystem;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }
    @Test
    public void test() {
        Bill bill = new Bill("寝室均摊账本");
        bill.addMember("何牧",50);
        bill.addMember("梁理维",40);
        bill.addMember("王泳淇",80);
        bill.addMember("唐铭聪",5);
        bill.addExpend("何牧",50);
        Bill bill1 = new Bill("寝室均摊账本");
        bill1.addMember("何牧",50);
        bill1.addMember("梁理维",40);
        bill1.addMember("王泳淇",80);
        bill1.addMember("唐铭聪",5);
        bill1.addExpend("何牧",50);
        Bill bill2 = new Bill("寝室均摊账本");
        bill2.addMember("何牧",50);
        bill2.addMember("梁理维",40);
        bill2.addMember("王泳淇",80);
        bill2.addMember("唐铭聪",5);
        bill2.addExpend("何牧",50);
        BillSystem billSystem = BillSystem.getInstance();
        billSystem.addBill(bill);
        billSystem.addBill(bill1);
        billSystem.addBill(bill2);
        bill1.getSolution();
        billSystem.sort();
    }
}