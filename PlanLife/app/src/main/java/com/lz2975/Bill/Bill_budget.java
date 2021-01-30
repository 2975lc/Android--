package com.lz2975.Bill;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class Bill_budget extends Fragment {
    private RelativeLayout title;
    private TextView text;
    private TextView money;
    private TextView spending;
    private TextView plan;
    private TextView hint;
    private RecyclerView recycler;
    private List<Bill_budget_data> budget_data;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        title = (RelativeLayout)view.findViewById(R.id.bill_budget_title);
        text = (TextView)view.findViewById(R.id.bill_budget_title_text);
        money = (TextView)view.findViewById(R.id.bill_budget_title_money);
        spending = (TextView)view.findViewById(R.id.bill_budget_title_spending);
        plan = (TextView)view.findViewById(R.id.bill_budget_title_plan);
        hint = (TextView)view.findViewById(R.id.bill_budget_hint);
        recycler = (RecyclerView)view.findViewById(R.id.bill_budget_recycler);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Bill_budget_add.class));
            }
        });
        initialize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        Bill_budget_recycler_adapter adapter=new Bill_budget_recycler_adapter(budget_data);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.bill_budget,container,false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        budget_data.clear();
        initialize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        Bill_budget_recycler_adapter adapter=new Bill_budget_recycler_adapter(budget_data);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
    void initialize(){
        SimpleDateFormat month_forma = new SimpleDateFormat("yyyy-MM");
        String month = month_forma.format(System.currentTimeMillis());
        budget_data = DataSupport.where("Budget_month=?",month).find(Bill_budget_data.class);
        Collections.sort(budget_data);
        double sp=0,pl=0;
        for (int x = 0; x< budget_data.size(); x++){
            sp+= budget_data.get(x).getBudget_spending();
            pl+= budget_data.get(x).getBudget_plan();
        }
        text.setText(month+"月总预算");
        money.setText(String.valueOf(pl-sp));
        if(pl-sp>=0){
            money.setTextColor(Color.argb(100,0,255,0));
        }else{
            money.setTextColor(Color.argb(100,255,0,0));
        }
        spending.setText("已用："+sp);
        plan.setText("计划："+pl);
        if (pl-sp>0.01){
            hint.setVisibility(View.GONE);
        }
    }
}