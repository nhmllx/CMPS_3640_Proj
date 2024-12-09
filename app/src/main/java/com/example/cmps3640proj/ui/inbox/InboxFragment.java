package com.example.cmps3640proj.ui.inbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cmps3640proj.databinding.FragmentInboxBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class InboxFragment extends Fragment {

    private FragmentInboxBinding binding;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInboxBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = FirebaseFirestore.getInstance();

        // Reference to the document
        DocumentReference docRef = db.collection("Compose").document("3nxYrNMxJCNxkL6YKD7j");

        // Fetch and display data
        final TextView textViewMessage = binding.textMessage;
        final TextView textViewRecipient = binding.textRecipient;
        final TextView textViewSubject = binding.textSubject;
        final TextView textViewUserID = binding.textUserId;

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Retrieve fields from the document
                    String message = document.getString("message");
                    String recipient = document.getString("recipient");
                    String subject = document.getString("subject");
                    String userID = document.getString("userId");

                    // Display data in TextViews
                    textViewMessage.setText("Message: " + (message != null ? message : "N/A"));
                    textViewRecipient.setText("Recipient: " + (recipient != null ? recipient : "N/A"));
                    textViewSubject.setText("Subject: " + (subject != null ? subject : "N/A"));
                    textViewUserID.setText("Sender: " + (userID != null ? userID : "N/A"));
                } else {
                    textViewMessage.setText("Document does not exist.");
                }
            } else {
                FirebaseFirestoreException e = (FirebaseFirestoreException) task.getException();
                textViewMessage.setText("Error: " + e.getMessage());
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
