package com.qracker.bbj.ui.accounting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qracker.bbj.R;
import com.qracker.bbj.model.bc.MoneyEvent;

import java.util.List;


/**
 * @program: BBJ
 * @description: 记账列表的适配器
 * @author: HeMu-qracker
 * @create: 2020-01-11 15:55
 **/
public class AccountingAdapter extends ArrayAdapter<MoneyEvent> {
    private int newResourceId;
    public AccountingAdapter(@NonNull Context context, int resource, @NonNull List<MoneyEvent> objects) {
        super(context, resource, objects);
        this.newResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MoneyEvent moneyEvent = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(newResourceId, parent, false);
        TextView itemText = view.findViewById(R.id.itemDetail_accounting);
        TextView itemDate = view.findViewById(R.id.itemDate_accounting);
        TextView itemMoney = view.findViewById(R.id.itemMoney_accounting);
        ImageView itemIcon = view.findViewById(R.id.itemIcon_accounting);
        itemDate.setText(moneyEvent.getDate());
        itemText.setText(moneyEvent.getComment());
        if(moneyEvent.isOut())
            itemMoney.setText("-" + moneyEvent.getMoney());
        else
            itemMoney.setText("+" + moneyEvent.getMoney());
        itemIcon.setBaseline(R.drawable.baseline_attach_money_black_48);
        return view;
    }
}
