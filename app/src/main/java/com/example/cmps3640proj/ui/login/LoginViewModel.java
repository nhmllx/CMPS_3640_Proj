package com.example.cmps3640proj.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    private final FirebaseAuth auth;
    private final MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

    public LoginViewModel() {
        auth = FirebaseAuth.getInstance();
    }

    public void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginResult.setValue(true);
                    } else {
                        loginResult.setValue(false);
                    }
                });
    }

    public LiveData<Boolean> getLoginResult() {
        return loginResult;
    }
}
