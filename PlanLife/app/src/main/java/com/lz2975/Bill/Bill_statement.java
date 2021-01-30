package com.lz2975.Bill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;
import com.lz2975.WheelView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bill_statement extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup RG;
    private RecyclerView recycler;
    private List<Bill_statement_data> datas = new ArrayList<>();
    private int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(System.currentTimeMillis())),month=Integer.parseInt(new SimpleDateFormat("MM").format(System.currentTimeMillis()));
    private String type="--";
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RG = (RadioGroup)view.findViewById(R.id.bill_statement_RGroup);
        recycler = (RecyclerView)view.findViewById(R.id.bill_statement_recycler);
        final WheelView wheel_year = (WheelView) view.findViewById(R.id.wheelview_year);
        final WheelView wheel_month = (WheelView) view.findViewById(R.id.wheelview_month);
        final List<String> list_year = new ArrayList<>();
        final List<String> list_month = new ArrayList<>();

        RG.setOnCheckedChangeListener(this);

        for(int i = year; i >= 2015; i--){
            list_year.add(String.valueOf(i));
        }
        for(int i = month; i >= 1; i--){
            list_month.add(String.valueOf(i));
        }
        for(int i = month+1; i <=12; i++){
            list_month.add(String.valueOf(i));
        }
        change();
        wheel_year.lists(list_year).fontSize(35).showCount(3).selectTip("年").select(0).listener(new WheelView.OnWheelViewItemSelectListener() {
            @Override
            public void onItemSelect(int index) {
                year=Integer.parseInt(list_year.get(index));
                change();
            }
        }).build();
        wheel_month.lists(list_month).fontSize(35).showCount(3).selectTip("月").select(0).listener(new WheelView.OnWheelViewItemSelectListener() {
            @Override
            public void onItemSelect(int index) {
                month=Integer.parseInt(list_month.get(index));
                change();
            }
        }).build();


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.bill_statement,container,false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        change();
    }
    void change(){
        SimpleDateFormat month_forma = new SimpleDateFormat("yyyy-MM");
        String str=year+"-"+month;
        datas.clear();
        List<Bill_record_data> all = DataSupport.findAll(Bill_record_data.class);
        Collections.sort(all);
        int find=0,n;
        if (type.equals("--")){
            datas.add(new Bill_statement_data("总支出",0));
        }else {
            datas.add(new Bill_statement_data("总收入",0));
        }
        for (Bill_record_data record_data:all){
            if (month_forma.format(record_data.getRecord_timestamp()).equals(str)){
                if (type.equals(record_data.getRecord_type())){
                    find=0;
                    for (n=1;n<datas.size();n++){
                        if (datas.get(n).getName().equals(record_data.getRecord_category())){
                            find=n;
                            break;
                        }
                    }
                    if (find==0){
                        datas.add(new Bill_statement_data(record_data.getRecord_category(),0));
                    }
                    datas.get(n).setCheek(datas.get(n).getCheek()+record_data.getRecord_cheek());
                    datas.get(0).setCheek(datas.get(0).getCheek()+record_data.getRecord_cheek());
                }
            }
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        Bill_statement_recycler_adapter adapter=new Bill_statement_recycler_adapter(datas);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.bill_statement_RGroup_expend:
                type="--";
                change();
                break;
            case R.id.bill_statement_RGroup_income:
                type="++";
                change();
                break;
        }
    }
}