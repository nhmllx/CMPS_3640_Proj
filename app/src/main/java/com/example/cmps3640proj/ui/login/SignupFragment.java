package com.example.cmps3640proj.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.cmps3640proj.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.signupButton.setOnClickListener(v -> {
            String username = binding.signupUsername.getText().toString().trim();
            String email = binding.signupEmail.getText().toString().trim();
            String password = binding.signupPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Implement signup logic here
                Toast.makeText(getContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
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
