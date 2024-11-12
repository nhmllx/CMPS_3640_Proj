package com.example.cmps3640proj.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.cmps3640proj.R;
import com.example.cmps3640proj.databinding.FragmentSignupBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();  // Initialize FirebaseAuth

        binding.signupButton.setOnClickListener(v -> {
            String email = binding.signupEmail.getText().toString().trim();
            String password = binding.signupPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(getContext(), "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create new user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            // Navigate back to login page
                            Navigation.findNavController(v).navigate(R.id.nav_login);
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Sign Up Failed";
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
