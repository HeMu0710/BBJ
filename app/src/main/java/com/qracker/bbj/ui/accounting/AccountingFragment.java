package com.qracker.bbj.ui.accounting;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.qracker.bbj.MainActivity;
import com.qracker.bbj.R;
import com.qracker.bbj.model.bc.AccountingSystem;
import com.qracker.bbj.model.bc.MoneyEvent;
import com.qracker.bbj.model.tool.Arith;
import com.qracker.bbj.model.tool.Read;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class AccountingFragment extends Fragment {

    private AccountingViewModel accountingViewModel;
    private AccountingAdapter adapter;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountingViewModel =
                ViewModelProviders.of(getActivity()).get(AccountingViewModel.class);
        accountingViewModel.setAccountingSystem(read());
        root = inflater.inflate(R.layout.fragment_accounting, container, false);
        ListView listView = root.findViewById(R.id.listView_accounting);
        this.adapter = new AccountingAdapter(root.getContext(), R.layout.listitem_accounting,
                accountingViewModel.getAccountingList());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        /**
        * @Description: onViewCreated()方法在onCreatView()执行之后被执行，主要可以进行各种事件的绑定。
        * @Param: [view, savedInstanceState]
        * @return: void
        * @Author: HeMu-qracker
        * @Date: 2020/1/21
        */
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listView_accounting);
        AccountingAdapter adapter = (AccountingAdapter) listView.getAdapter();
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
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moneyEvent.setComment(String.valueOf(itemComment.getText()));
                        moneyEvent.setMoney(Arith.round(Double.valueOf(String.valueOf(itemMoney.getText())), 2));
                        moneyEvent.setOut(!inOrOutSwitch.isChecked());
                        String date = String.valueOf(datePicker.getText());
                        String time = String.valueOf(timePicker.getText());
                        moneyEvent.setDate(Read.readYear(date) - 1900, Read.readMonth(date) - 1, Read.readDay(date));
                        moneyEvent.setTime(Read.readHour(time), Read.readMinute(time));
                        adapter.notifyDataSetChanged();
                        updateCardView(getView());
                        save();
                    }
                });
                builder.setNegativeButton("取消", null);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(Menu.NONE, 0, 0,"删除");
                menu.add(Menu.NONE, 1, 0,"置顶");
            }
        });
        updateCardView(view);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                //删除
                accountingViewModel.getAccountingSystem().deleteEvent(menuInfo.position);
                updateListView();
                updateCardView(root);
                break;
            case 1:
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void save() {
        /**
        * @Description: 将accountingSystem转成json字符串然后存在data文件里
        * @Param: []
        * @return: void
        * @Author: HeMu-qracker
        * @Date: 2020/1/20
        */
        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput("data", MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(new Gson().toJson(accountingViewModel.getAccountingSystem()));
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

    public AccountingSystem read() {
        /**
        * @Description: 从data文件读取accountingSystem
        * @Param: []
        * @return: com.qracker.bbj.model.bc.AccountingSystem
        * @Author: HeMu-qracker
        * @Date: 2020/1/20
        */
        Gson gson = new Gson();
        BufferedReader br = null;
        FileInputStream fis = null;
        String json = null;
        try {
            fis = getActivity().openFileInput("data");
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
        System.out.println(json);
        AccountingSystem accountingSystem = gson.fromJson(json, AccountingSystem.class);
        if(accountingSystem == null)
            return new AccountingSystem();
        else
            return accountingSystem;
    }

    public void updateCardView(View view) {
        Date date = new Date();
        TextView surplusTextView = view.findViewById(R.id.textView_accounting_surplus);
        surplusTextView.setText(accountingViewModel.getAccountingSystem().getSurplus(date.getYear(), date.getMonth()) + "");
        TextView thisMonthlyExpend = view.findViewById(R.id.textView_accounting_monthlyExpend);
        thisMonthlyExpend.setText(accountingViewModel.getAccountingSystem().getMonthlyExpend(date.getYear(), date.getMonth()) + "");
        TextView thisMonthlyIncome = view.findViewById(R.id.textView_accounting_monthlyIncome);
        thisMonthlyIncome.setText(accountingViewModel.getAccountingSystem().getMonthlyIncome(date.getYear(), date.getMonth()) + "");
    }

    public void updateListView() {
        adapter.notifyDataSetChanged();
        save();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCardView(getView());
    }
}
