package com.example.cmps3640proj.ui.compose;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cmps3640proj.databinding.FragmentComposeBinding;

public class ComposeFragment extends Fragment {

    private FragmentComposeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ComposeViewModel galleryViewModel =
                new ViewModelProvider(this).get(ComposeViewModel.class);

        binding = FragmentComposeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCompose;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}