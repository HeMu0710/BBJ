package com.qracker.bbj.ui.aatool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qracker.bbj.R;
import com.qracker.bbj.model.bz.Bill;

import java.util.List;

/**
 * @program: BBJ
 * @description: 均摊账单的ListView的adapter
 * @author: HeMu-qracker
 * @create: 2020-01-12 23:06
 **/
public class BillListAdapter extends ArrayAdapter<Bill> {
    private int newResourceId;

    public BillListAdapter(@NonNull Context context, int resource, @NonNull List<Bill> objects) {
        super(context, resource, objects);
        this.newResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bill bill = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(newResourceId, parent, false);
        TextView itemTitle = view.findViewById(R.id.itemTitle_aatool);
        itemTitle.setText(bill.getComment());
        return view;
    }
}
