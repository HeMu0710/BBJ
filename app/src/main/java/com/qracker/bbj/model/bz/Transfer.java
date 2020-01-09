package com.qracker.bbj.model.bz;

public class Transfer {
    private int payerId, payeeId;
    private double transferMoney;

    public Transfer(int payerId, int payeeId, double transferMoney) {
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.transferMoney = transferMoney;
    }

    public double getTransferMoney() {
        return transferMoney;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public int getPayerId() {
        return payerId;
    }

    @Override
    public String toString() {
        return payerId + "->" + payeeId + ":" + this.transferMoney;
    }
}
