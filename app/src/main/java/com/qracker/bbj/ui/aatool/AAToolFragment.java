package com.qracker.bbj.ui.aatool;

import android.content.Context;
import android.icu.util.BuddhistCalendar;
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

import com.google.gson.Gson;
import com.qracker.bbj.R;
import com.qracker.bbj.model.bz.BillSystem;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AAToolFragment extends Fragment {

    private AAtoolViewModel AAtoolViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AAtoolViewModel =
                ViewModelProviders.of(getActivity()).get(AAtoolViewModel.class);
        View root = inflater.inflate(R.layout.fragment_aatool, container, false);
        ListView billListView = root.findViewById(R.id.listView_aaTool);
        BillListAdapter adapter = new BillListAdapter(root.getContext(),
                R.layout.listitem_aatool, AAtoolViewModel.getBillList());
        billListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        save();
        return root;
    }

    public void save() {
        String json = new Gson().toJson(AAtoolViewModel.getBillSystem(), BillSystem.class);
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = getActivity().openFileOutput("bills", Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BillSystem read() {
        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.save();
    }
}
