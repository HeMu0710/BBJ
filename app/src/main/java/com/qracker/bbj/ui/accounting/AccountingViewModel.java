package com.qracker.bbj.ui.accounting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qracker.bbj.model.bc.AccountingSystem;
import com.qracker.bbj.model.bc.MoneyEvent;

import java.util.ArrayList;
import java.util.Date;

public class AccountingViewModel extends ViewModel {

    private AccountingSystem accountingSystem;

    public AccountingViewModel() {
        accountingSystem = AccountingSystem.getInstance();
        accountingSystem.addEvent(new MoneyEvent(100,true, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(100,false, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(8000.54,true, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(99,true, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(52,false, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(8000,false, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(2,true, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(7,false, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
        accountingSystem.addEvent(new MoneyEvent(0,true, " ",
                "Material Design引入了三维中z轴的概念维中z轴的概念",new Date()));
    }

    public ArrayList<MoneyEvent> getAccountingList() {
        return accountingSystem.getEvents();
    }

    public AccountingSystem getAccountingSystem() {
        return this.accountingSystem;
    }
}