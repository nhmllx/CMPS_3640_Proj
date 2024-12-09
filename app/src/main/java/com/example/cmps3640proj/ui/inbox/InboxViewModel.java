package com.example.cmps3640proj.ui.inbox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InboxViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InboxViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to your inbox");
    }

    public LiveData<String> getText() {
        return mText;
    }
}