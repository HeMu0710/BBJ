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
        int r = nextId;
        nextId++;
        return r;
    }

    public double getExpend() {
        return this.expend;
    }

    public void addTransfer(double transferMoney, Member payee) {
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
