package com.qracker.bbj.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccountingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is accounting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}