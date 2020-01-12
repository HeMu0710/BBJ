package com.qracker.bbj.ui.aatool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qracker.bbj.model.bz.Member;

import java.util.List;
import com.qracker.bbj.R;

/**
 * @program: BBJ
 * @description: 均摊账本成员listview的adpter
 * @author: HeMu-qracker
 * @create: 2020-01-13 01:35
 **/
public class MemberListAdapter extends ArrayAdapter<Member> {
    private int newResourceId;
    public MemberListAdapter(@NonNull Context context, int resource, @NonNull List<Member> objects) {
        super(context, resource, objects);
        this.newResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(newResourceId, parent, false);
        TextView itemName = view.findViewById(R.id.itemName_bill_member);
        TextView itemExpend = view.findViewById(R.id.itemExpend_bill_member);
        Member member = getItem(position);
        itemName.setText(member.getName());
        itemExpend.setText(member.getExpend() + " RMB");
        return view;
    }
}
