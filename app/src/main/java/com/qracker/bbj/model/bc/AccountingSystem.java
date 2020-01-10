package com.qracker.bbj.model.bc;

import java.util.ArrayList;
import com.qracker.bbj.model.tool.Arith;

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
        * @Description: 添加一笔记账
        * @Param: [moneyEvent]
        * @return: void
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        this.events.add(moneyEvent);
    }

    public double getAllIncome() {
        /**
        * @Description: 获取总收入
        * @Param: []
        * @return: double
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        double allIncome = 0;
        for (MoneyEvent me : events
             ) {
            if (!me.isOut())
                allIncome = Arith.add(allIncome, me.getMoney());
        }
        return allIncome;
    }

    public double getAllExpend() {
        /**
        * @Description: 获取总支出
        * @Param: []
        * @return: double
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        double allExpand = 0;
        for (MoneyEvent me : events
             ) {
            if (me.isOut())
                allExpand = Arith.add(allExpand, me.getMoney());
        }
        return allExpand;
    }

    public double getSurplus() {
        /**
        * @Description: 获取结余，即收入减支出
        * @Param: []
        * @return: double
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        return Arith.sub(getAllIncome(), getAllExpend());
    }
}
