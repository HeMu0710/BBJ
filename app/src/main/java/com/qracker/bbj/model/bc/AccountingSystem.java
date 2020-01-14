package com.qracker.bbj.model.bc;

import java.util.ArrayList;

import com.qracker.bbj.model.tool.*;

public class AccountingSystem {
    private static AccountingSystem instance = new AccountingSystem();
    private ArrayList<MoneyEvent> events = new ArrayList<>();

    private AccountingSystem() {
    }

    public static AccountingSystem getInstance() {
        /**
        * @Description: AccountingSystem应实现单例模式，此方法为获取这个单例对象。
        * @Param: []
        * @return: com.qracker.bbj.model.bc.AccountingSystem
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        return instance;
    }

    public ArrayList<MoneyEvent> getEvents() {
        return events;
    }

    public void addEvent(MoneyEvent moneyEvent) {
        /**
        * @Description: 添加一笔记账至首位
        * @Param: [moneyEvent]
        * @return: void
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        this.events = addInFront(this.events, moneyEvent);
    }

    public double getMonthlyIncome(int month) {
        /**
        * @Description: 获取特定月份总收入
        * @Param: [month]
        * @return: double
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        double allIncome = 0;
        for (MoneyEvent me : events
             ) {
            if (!me.isOut() && me.getMonth() == month)
                allIncome = Arith.add(allIncome, me.getMoney());
        }
        return allIncome;
    }

    public double getMonthlyExpend(int month) {
        /**
        * @Description: 获取特定月份总支出
        * @Param: [month]
        * @return: double
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        double allExpand = 0;
        for (MoneyEvent me : events
             ) {
            if (me.isOut() && me.getMonth() == month)
                allExpand = Arith.add(allExpand, me.getMoney());
        }
        return allExpand;
    }

    public double getSurplus(int month) {
        /**
        * @Description: 获取特定月份结余，即收入减支出
        * @Param: [month]
        * @return: double
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        return Arith.sub(getMonthlyIncome(month), getMonthlyExpend(month));
    }

    public ArrayList<MoneyEvent> addInFront(ArrayList<MoneyEvent> moneyEvents, MoneyEvent moneyEventToAdd) {
        /**
        * @Description: 继承自自定义接口AddInFront,实现将对象添加到list的首位
        * @Param: [moneyEvents, moneyEventToAdd]
        * @return: java.util.ArrayList<com.qracker.bbj.model.bc.MoneyEvent>
        * @Author: HeMu-qracker
        * @Date: 2020/1/13
        */
        ArrayList<MoneyEvent> newEvents = new ArrayList<>();
        newEvents.add(moneyEventToAdd);
        newEvents.addAll(moneyEvents);
        return newEvents;
    }
}
