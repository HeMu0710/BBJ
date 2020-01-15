package com.qracker.bbj.ui.accounting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.qracker.bbj.R;

public class AccountingFragment extends Fragment {

    private AccountingViewModel accountingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountingViewModel =
                ViewModelProviders.of(getActivity()).get(AccountingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accounting, container, false);
        ListView listView = root.findViewById(R.id.listView_accounting);
        AccountingAdapter adapter = new AccountingAdapter(root.getContext(), R.layout.listitem_accounting,
                accountingViewModel.getAccountingList());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listView_accounting);
        Adapter adapter = listView.getAdapter();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopupWindow popupWindow = new PopupWindow(100,100);
            }
        });
    }
}
