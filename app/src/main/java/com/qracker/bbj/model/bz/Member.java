package com.qracker.bbj.model.bz;

import com.qracker.bbj.model.tool.Arith;

import java.util.ArrayList;

public class Member {
    private int id;
    private String name;
    private double expend;
    private ArrayList<Transfer> transfers = new ArrayList<>();


    public Member(String name, double expend) {
        this.name = name;
        this.expend = expend;
    }

    public Member(String name) {
        this.name = name;
        this.expend = 0;
    }

    public double getExpend() {
        return this.expend;
    }

    public void addTransfer(double transferMoney, Member payee) {
        /**
        * @Description: 添加转账记录，并对双方支出进行处理
        * @Param: [transferMoney, payee]
        * @return: void
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        this.expend = Arith.add(this.expend, transferMoney);
        payee.expend = Arith.sub(payee.getExpend(),transferMoney);
        this.transfers.add(new Transfer(this.name, payee.name,transferMoney));
    }

    public void setExpend(double expend) {
        this.expend = expend;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Transfer> getTransfers() {
        return this.transfers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
