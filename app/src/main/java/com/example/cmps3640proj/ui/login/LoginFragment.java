package com.example.cmps3640proj.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.cmps3640proj.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.loginButton.setOnClickListener(v -> {
            String email = binding.usernameEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            loginViewModel.loginUser(email, password);
        });

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), isSuccess -> {
            if (isSuccess) {
                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                // Navigate to the next screen, e.g., home screen
            } else {
                Toast.makeText(getContext(), "Login Failed. Please check your credentials", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
