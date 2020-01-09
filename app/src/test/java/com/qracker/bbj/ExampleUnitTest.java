package com.qracker.bbj;

import org.junit.Test;

import com.google.gson.Gson;
import com.qracker.bbj.model.bc.AccountingSystem;
import com.qracker.bbj.model.bc.MoneyEvent;
import com.qracker.bbj.model.bz.Bill;

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
        Bill bill = new Bill(0, 500, 901, 421, 152);
        System.out.println(bill.getSolution());
        Bill bill1 = new Bill(51, 987, 517, 84);
        System.out.println(bill1.getSolution());
    }
}