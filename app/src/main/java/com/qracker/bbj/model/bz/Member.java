package com.qracker.bbj.model.bz;

import com.qracker.bbj.tool.Arith;

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
        this.expend = Arith.round(Arith.add(this.expend, transferMoney), 2);
        payee.expend = Arith.round(Arith.sub(payee.getExpend(),transferMoney), 2);
        this.transfers.add(new Transfer(this.id, payee.id,transferMoney));
    }

    public ArrayList<Transfer> getTransfers() {
        return this.transfers;
    }
}
