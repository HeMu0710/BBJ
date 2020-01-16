package com.qracker.bbj.ui.accounting;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.qracker.bbj.R;
import com.qracker.bbj.model.bc.MoneyEvent;

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
                MoneyEvent moneyEvent = (MoneyEvent) adapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_accounting,parent,false);

                EditText itemComment = dialogView.findViewById(R.id.editText_accounting_dialog_comment);
                itemComment.setText(moneyEvent.getComment());
                EditText itemMoney = dialogView.findViewById(R.id.editText_accounting_dialog_money);
                itemMoney.setText(String.valueOf(moneyEvent.getMoney()));
                Switch inOrOutSwitch = dialogView.findViewById(R.id.switch_accounting_dialog_inOrOut);
                inOrOutSwitch.setChecked(!moneyEvent.isOut());
                Button datePicker = dialogView.findViewById(R.id.button_accounting_dialog_datePicker);
                datePicker.setText(moneyEvent.getDate());
                Button timePicker = dialogView.findViewById(R.id.button_accounting_dialog_timePicker);
                timePicker.setText(moneyEvent.getTime());
                datePicker.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        DatePicker datePicker1 = new DatePicker(v.getContext());
                        datePicker1.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datePicker.setText(year + "/" + (monthOfYear  + 1) + "/" + dayOfMonth);
                            }
                        });
                        builder1.setView(datePicker1);
                        builder1.show();
                    }
                });
                timePicker.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                        TimePicker timePicker1 = new TimePicker(v.getContext());
                        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                            @Override
                            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                                if(minute < 10)
                                    timePicker.setText(hourOfDay + ":0" + minute);
                                else
                                    timePicker.setText(hourOfDay + ":" + minute);
                            }
                        });
                        builder1.setView(timePicker1);
                        builder1.show();
                    }
                });

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moneyEvent.setComment(String.valueOf(itemComment.getText()));
                    }
                });
                builder.setNegativeButton("取消", null);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
