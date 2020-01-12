package com.qracker.bbj.model.bz;

public class Transfer {
    private String payerName, payeeName;
    private double transferMoney;

    public Transfer(String payerName, String payeeName, double transferMoney) {
        this.payerName = payerName;
        this.payeeName= payeeName;
        this.transferMoney = transferMoney;
    }

    public double getTransferMoney() {
        return transferMoney;
    }

    public String getPayeeId() {
        return payeeName;
    }

    public String getPayerId() {
        return payerName;
    }

    @Override
    public String toString() {
        return payerName + "转给" + payeeName + ":" + this.transferMoney + "RMB";
    }
}
