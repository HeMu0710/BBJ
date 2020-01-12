package com.qracker.bbj.ui.aatool;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
        ListView billListView = root.findViewById(R.id.listView_aaTool);
        BillListAdapter adapter = new BillListAdapter(root.getContext(),
                R.layout.listitem_aatool, AAtoolViewModel.getBillList());
        billListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }
}
