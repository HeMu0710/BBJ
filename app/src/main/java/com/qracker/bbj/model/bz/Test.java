package com.qracker.bbj.model.bz;

import com.google.gson.Gson;

public class Test {
    public static void main(String[] args) {
        Gson gson= new Gson();
        String json = gson.toJson(new Transfer(0, 1, 100), Transfer.class);
        System.out.println(json);
        System.out.println(gson.fromJson(json, Transfer.class));
        Member tom = new Member(100);
        tom.addTransfer(50,new Member(20));
        json = gson.toJson(tom, Member.class);
        System.out.println(json);
        Member jerry = gson.fromJson(json, Member.class);
        Bill bill = new Bill(100, 50, 60, 12, 999);
        bill.getSolution();
        json = gson.toJson(bill, Bill.class);
        System.out.println(json);
        Bill bil = gson.fromJson(json, Bill.class);
        System.out.println(bil.getSolution());
    }
}
