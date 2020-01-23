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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class AAToolFragment extends Fragment {

    private AAtoolViewModel AAtoolViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AAtoolViewModel =
                ViewModelProviders.of(getActivity()).get(AAtoolViewModel.class);
        AAtoolViewModel.setBillSystem(read());
        View root = inflater.inflate(R.layout.fragment_aatool, container, false);
        ListView billListView = root.findViewById(R.id.listView_aaTool);
        BillListAdapter adapter = new BillListAdapter(root.getContext(),
                R.layout.listitem_aatool, AAtoolViewModel.getBillList());
        billListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        save();
    }

    public void save() {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = getActivity().openFileOutput("aatool", MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(new Gson().toJson(AAtoolViewModel.getBillSystem(), BillSystem.class));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BillSystem read() {
        FileInputStream fis = null;
        BufferedReader br = null;
        String json = null;
        try {
            fis = getActivity().openFileInput("aatool");
            br = new BufferedReader(new InputStreamReader(fis));
            json = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BillSystem billSystem = new Gson().fromJson(json, BillSystem.class);
        if(billSystem == null)
            return new BillSystem();
        else
            return billSystem;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.save();
    }
}
