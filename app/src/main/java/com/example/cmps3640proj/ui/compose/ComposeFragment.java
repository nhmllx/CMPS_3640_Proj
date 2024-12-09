package com.example.cmps3640proj.ui.compose;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cmps3640proj.databinding.FragmentComposeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ComposeFragment extends Fragment {

    private FragmentComposeBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ComposeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentComposeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Compose"); // Set to your collection name

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ComposeViewModel.class);

        // Set click listener for the Send button
        //binding.buttonSend.setOnClickListener(view -> sendEmailData());
        binding.buttonSend.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Button clicked, calling sendEmailData()", Toast.LENGTH_SHORT).show();
            sendEmailData();
        });

        return root;
    }

    private void sendEmailData() {
        // Get input fields
        EditText editTextRecipient = binding.editTextRecipient;
        EditText editTextSubject = binding.editTextSubject;
        EditText editTextMessage = binding.editTextMessage;

        // Extract text from inputs
        String recipient = editTextRecipient.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        // Validate inputs
        if (!validateInputs(recipient, subject, message)) {
            return;
        }

        // Get current user
        String userId = firebaseAuth.getCurrentUser() != null ? firebaseAuth.getCurrentUser().getUid() : null;
        if (userId == null) {
            Toast.makeText(getContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare email data
        HashMap<String, String> emailData = new HashMap<>();
        emailData.put("recipient", recipient);
        emailData.put("subject", subject);
        emailData.put("message", message);
        emailData.put("userId", userId);

        // Push data to Firebase Realtime Database collection
        databaseReference.push().setValue(emailData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getContext(), "Email data saved successfully", Toast.LENGTH_SHORT).show();

                // Clear fields after successful save
                clearFields(editTextRecipient, editTextSubject, editTextMessage);
            } else {
                Toast.makeText(getContext(), "Failed to save email data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInputs(String recipient, String subject, String message) {
        if (TextUtils.isEmpty(recipient)) {
            binding.editTextRecipient.setError("Recipient is required");
            return false;
        }
        if (TextUtils.isEmpty(subject)) {
            binding.editTextSubject.setError("Subject is required");
            return false;
        }
        if (TextUtils.isEmpty(message)) {
            binding.editTextMessage.setError("Message cannot be empty");
            return false;
        }
        return true;
    }

    private void clearFields(EditText recipient, EditText subject, EditText message) {
        recipient.setText("");
        subject.setText("");
        message.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
