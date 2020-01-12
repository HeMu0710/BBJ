package com.qracker.bbj;

import org.junit.Test;

import com.google.gson.Gson;
import com.qracker.bbj.model.bc.AccountingSystem;
import com.qracker.bbj.model.bc.MoneyEvent;
import com.qracker.bbj.model.bz.Bill;

import java.util.Date;

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
        System.out.println(bill.getSolution());
        Gson gson = new Gson();
        String json = gson.toJson(bill, Bill.class);
        System.out.println(json);
        Bill bill1 = gson.fromJson(json, Bill.class);
    }
}