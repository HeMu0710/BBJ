package com.qracker.bbj.model.bc;

import java.util.ArrayList;
import com.qracker.bbj.model.tool.Arith;

public class AccountingSystem {
    private static AccountingSystem instance = new AccountingSystem();
    private ArrayList<MoneyEvent> events = new ArrayList<>();

    private AccountingSystem() {
    }

    public static AccountingSystem getInstance() {
        return instance;
    }

    public ArrayList<MoneyEvent> getEvents() {
        return events;
    }

    public void addEvent(MoneyEvent moneyEvent) {
        this.events.add(moneyEvent);
    }

    public double getAllIncome() {
        double allIncome = 0;
        for (MoneyEvent me : events
             ) {
            if (!me.isOut())
                allIncome = Arith.add(allIncome, me.getMoney());
        }
        return allIncome;
    }

    public double getAllExpend() {
        double allExpand = 0;
        for (MoneyEvent me : events
             ) {
            if (me.isOut())
                allExpand = Arith.add(allExpand, me.getMoney());
        }
        return allExpand;
    }

    public double getNet() {
        return Arith.sub(getAllIncome(), getAllExpend());
    }
}
