package com.example.cmps3640proj.ui.compose;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComposeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ComposeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Send");
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Method to be called when the send button is clicked
    public void onSendButtonClicked(Context context) {
        Toast.makeText(context, "Sent", Toast.LENGTH_SHORT).show();
    }
}
