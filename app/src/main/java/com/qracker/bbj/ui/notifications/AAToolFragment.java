package com.qracker.bbj.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.qracker.bbj.R;

public class AAToolFragment extends Fragment {

    private AAtoolViewModel AAtoolViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AAtoolViewModel =
                ViewModelProviders.of(this).get(AAtoolViewModel.class);
        View root = inflater.inflate(R.layout.fragment_aatool, container, false);
        final TextView textView = root.findViewById(R.id.text_aatool);
        AAtoolViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
