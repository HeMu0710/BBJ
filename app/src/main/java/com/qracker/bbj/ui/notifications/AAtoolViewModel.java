package com.qracker.bbj.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AAtoolViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AAtoolViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is aatool fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}