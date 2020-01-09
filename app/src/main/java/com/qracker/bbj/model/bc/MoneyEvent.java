package com.qracker.bbj.model.bc;

public class MoneyEvent {
    private double money;
    private boolean isOut;   //true代表支出，false代表收入
    private String type;
    private String description;

    public MoneyEvent(double money, boolean isOut, String type, String description) {
        this.money = money;
        this.isOut = isOut;
        this.type = type;
        this.description = description;
    }

    public MoneyEvent(double money, boolean isOut, String type) {
        this.money = money;
        this.isOut = isOut;
        this.type = type;
        this.description = "及时行乐";
    }

    public MoneyEvent(double money, boolean isOut) {
        this.money = money;
        this.isOut = isOut;
        this.type = "生活";
        this.description = "及时行乐";
    }

    public double getMoney() {
        return money;
    }

    public boolean isOut() {
        return isOut;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
