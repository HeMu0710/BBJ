package com.qracker.bbj;

import org.junit.Test;

import com.google.gson.Gson;
import com.qracker.bbj.model.bc.AccountingSystem;
import com.qracker.bbj.model.bc.MoneyEvent;
import com.qracker.bbj.model.bz.Bill;
import com.qracker.bbj.model.bz.BillSystem;
import com.qracker.bbj.model.tool.Read;

import java.util.ArrayList;
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
        ArrayList<String> list = new ArrayList<>();
        list.add("nmsl");
        list.add(0,"wdnmd");
        list.add(0, "nmlgb");
        System.out.println(list);
    }
}