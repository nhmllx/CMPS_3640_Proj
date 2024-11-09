package com.example.cmps3640proj.ui.compose;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComposeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ComposeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is compose email fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}