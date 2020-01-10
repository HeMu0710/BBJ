package com.qracker.bbj.ui.dashboard;

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

public class AccountingFragment extends Fragment {

    private AccountingViewModel accountingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountingViewModel =
                ViewModelProviders.of(this).get(AccountingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accounting, container, false);
        final TextView textView = root.findViewById(R.id.text_accounting);
        accountingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
