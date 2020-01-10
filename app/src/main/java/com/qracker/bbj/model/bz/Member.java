package com.qracker.bbj.model.bz;

import com.qracker.bbj.model.tool.Arith;

import java.util.ArrayList;

public class Member {
    public static int nextId;
    public final int id = assignId();
    private double expend;
    private ArrayList<Transfer> transfers = new ArrayList<>();


    public Member(double expend) {
        this.expend = expend;
    }

    private int assignId() {
        /**
        * @Description: 分发ID
        * @Param: []
        * @return: int
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        int r = nextId;
        nextId++;
        return r;
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
        this.transfers.add(new Transfer(this.id, payee.id,transferMoney));
    }

    public void setExpend(double expend) {
        this.expend = expend;
    }

    public ArrayList<Transfer> getTransfers() {
        return this.transfers;
    }
}
