package com.qracker.bbj.model.bc;

import androidx.lifecycle.LiveData;

import java.util.Date;

public class MoneyEvent {
    private double money;
    private boolean isOut;   //true代表支出，false代表收入
    private String type;
    private String comment;
    private Date date;

    public MoneyEvent(double money, boolean isOut, String type, String comment, Date date) {
        this.money = money;
        this.isOut = isOut;
        this.type = type;
        this.comment = comment;
        this.date = date;
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

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return (date.getYear() + 1900) + "/" + (date.getMonth() + 1) + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();
    }

    public int getMonth() {
        return this.date.getMonth();
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(int year, int monthOfYear, int dayOfMonth) {
        this.date.setYear(year);
        this.date.setMonth(monthOfYear);
        this.date.setDate(dayOfMonth);
    }
}
