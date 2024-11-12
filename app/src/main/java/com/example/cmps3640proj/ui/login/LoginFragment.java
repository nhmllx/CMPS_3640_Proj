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
import com.example.cmps3640proj.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                // Navigate to InboxFragment
                                Navigation.findNavController(v).navigate(R.id.nav_inbox);
                            }
                        } else {
                            Toast.makeText(getContext(), "Login Failed. Please check your credentials", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // Navigate to SignupFragment when "Sign Up" is tapped
        binding.signUpTextView.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_nav_login_to_nav_signup)
        );

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
