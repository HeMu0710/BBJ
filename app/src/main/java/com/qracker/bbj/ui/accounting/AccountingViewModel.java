package com.qracker.bbj.ui.accounting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.qracker.bbj.model.bc.AccountingSystem;
import com.qracker.bbj.model.bc.MoneyEvent;

import java.util.ArrayList;
import java.util.Date;

public class AccountingViewModel extends ViewModel {

    private AccountingSystem accountingSystem;

    public AccountingViewModel() {
    }

    public ArrayList<MoneyEvent> getAccountingList() {
        return accountingSystem.getEvents();
    }

    public AccountingSystem getAccountingSystem() {
        return this.accountingSystem;
    }

    public void setAccountingSystem(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
    }
}