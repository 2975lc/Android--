package com.lz2975.Bill;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bill_record extends Fragment {
    private TextView budget;
    private TextView money_left;
    private TextView money_right;
    private ImageButton add;
    private TextView hint;
    private RecyclerView recycler;
    private List<List<Bill_record_data>> record_data;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        budget = (TextView)view.findViewById(R.id.bill_record_budget);
        money_left = (TextView)view.findViewById(R.id.bill_record_money_left);
        money_right = (TextView)view.findViewById(R.id.bill_record_money_right);
        add = (ImageButton)view.findViewById(R.id.bill_record_add);
        hint = (TextView)view.findViewById(R.id.bill_record_hint);
        recycler = (RecyclerView)view.findViewById(R.id.bill_record_recycler);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Bill_record_add.class));
            }
        });
        initialize();
        if (record_data.get(0).isEmpty()){
            hint.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }else {
            hint.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recycler.setLayoutManager(layoutManager);
            Bill_record_recycler_adapter adapter = new Bill_record_recycler_adapter(getContext(), record_data);
            recycler.setAdapter(adapter);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.bill_record,container,false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        record_data.clear();
        initialize();
        if (record_data.get(0).isEmpty()){
            hint.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }else {
            hint.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recycler.setLayoutManager(layoutManager);
            Bill_record_recycler_adapter adapter = new Bill_record_recycler_adapter(getContext(), record_data);
            recycler.setAdapter(adapter);
        }
    }
    private void initialize(){
        SimpleDateFormat month_forma = new SimpleDateFormat("yyyy-MM");
        String month = month_forma.format(System.currentTimeMillis());
        SimpleDateFormat date_forma = new SimpleDateFormat("yyyy-MM-dd");
        String date = date_forma.format(System.currentTimeMillis());


        List<Bill_budget_data> budget_data = DataSupport.where("Budget_month=?",month).find(Bill_budget_data.class);
        double sp=0,pl=0;
        for (int x=0;x<budget_data.size();x++){
            sp+=budget_data.get(x).getBudget_spending();
            pl+=budget_data.get(x).getBudget_plan();
        }
        budget.setText("预算剩余："+(pl-sp));
        if(pl-sp>=0){
            budget.setTextColor(Color.argb(100,0,255,0));
        }else{
            budget.setTextColor(Color.argb(100,255,0,0));
        }

        record_data =new ArrayList<>();
        double income=0;
        double expend=0;
        List<Bill_record_data> all = DataSupport.findAll(Bill_record_data.class);
        List<Bill_record_data> datas=new ArrayList<>();
        Collections.sort(all);
        for (Bill_record_data data:all){
            if (!date_forma.format(data.getRecord_timestamp()).equals(date)){
                if (!datas.isEmpty()){
                    record_data.add(datas);
                }
                datas=new ArrayList<>();
                date=date_forma.format(data.getRecord_timestamp());
            }
            datas.add(data);
            if (month_forma.format(data.getRecord_timestamp()).equals(month)){
                if (data.getRecord_type().equals("++")){
                    income+=data.getRecord_cheek();
                }else if (data.getRecord_type().equals("--")){
                    expend+=data.getRecord_cheek();
                }
            }
        }
        record_data.add(datas);
        set_month_money(income,expend);
    }
    private void set_month_money(double income,double expend){
        money_left.setText(""+income);
        money_right.setText(""+expend);
        //加粗
        TextPaint tpl = money_left.getPaint();
        tpl.setFakeBoldText(true);
        TextPaint tpr = money_right.getPaint();
        tpr.setFakeBoldText(true);
    }
}
