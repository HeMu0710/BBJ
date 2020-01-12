package com.qracker.bbj.ui.aatool;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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
        TextView itemStatus = view.findViewById(R.id.itemStatus_aatool);
        ListView memberListView = view.findViewById(R.id.listView_bill_memberList);
        MemberListAdapter adapter = new MemberListAdapter(getContext(),
                R.layout.listitem_bill_memberlist, bill.getMembers());
        memberListView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(memberListView);
        memberListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        itemTitle.setText(bill.getComment());
        if(bill.isFinished())
            itemStatus.setText("已结清");
        else
            itemStatus.setText("未结清");
        Button buttonCheck = view.findViewById(R.id.button_aaTool_billCheck);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("nmsl","NMSLNMSL");
            }
        });
        return view;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        /**
        * @Description: 使子listview在父listview的item里显示完全
        * @Param: [listView]
        * @return: void
        * @Author: @CSDN
        * @Date: 2020/1/13
        */
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
