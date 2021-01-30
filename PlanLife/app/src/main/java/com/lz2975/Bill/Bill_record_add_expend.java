package com.lz2975.Bill;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Bill_record_add_expend extends Fragment {
    private TextView background;
    private EditText cheek;
    private EditText describe;
    private Button expend_finish;
    private GridView grid;
    private List<Bill_record_add_image_select> images=new Bill_record_add_image_select().expends();
    private int number=0;
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        background = (TextView)view.findViewById(R.id.bill_record_add_expend_background);
        cheek = (EditText)view.findViewById(R.id.bill_record_add_expend_cheek);
        describe = (EditText)view.findViewById(R.id.bill_record_add_expend_describe);
        expend_finish = (Button)view.findViewById(R.id.bill_record_add_expend_finish);
        grid = (GridView)view.findViewById(R.id.bill_record_add_expend_gridview);
        Bill_record_add_image_select image= images.get(number);
        background.setBackgroundColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
        Bill_record_add_expend_gridview_adapter adapter = new Bill_record_add_expend_gridview_adapter(view.getContext(),images);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number=position;
                Bill_record_add_image_select image= images.get(number);
                background.setBackgroundColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
            }
        });
        expend_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cheek.getText().toString().isEmpty()){
                    if (Double.parseDouble(cheek.getText().toString())>=0.01){
                        Bill_record_data data = new Bill_record_data();
                        data.setRecord_cheek(Double.parseDouble(cheek.getText().toString()));
                        data.setRecord_type("--");
                        data.setRecord_category(images.get(number).getDescribe());
                        data.setRecord_image(images.get(number).getImage());
                        data.setRecord_remark(describe.getText().toString());
                        data.setRecord_timestamp(System.currentTimeMillis());
                        data.save();

                        String month = new SimpleDateFormat("yyyy-MM").format(System.currentTimeMillis());
                        List<Bill_budget_data> budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,images.get(number).getDescribe()).find(Bill_budget_data.class);
                        if (!budge_data.isEmpty()){
                            Bill_budget_data bd=budge_data.get(0);
                            bd.setBudget_spending(bd.getBudget_spending()+Double.parseDouble(cheek.getText().toString()));
                            bd.save();
                        }
                        getActivity().finish();
                    }
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.bill_record_add_expend,container,false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        Bill_record_add_image_select image= images.get(number);
        background.setBackgroundColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
        Bill_record_add_expend_gridview_adapter adapter = new Bill_record_add_expend_gridview_adapter(getContext(),images);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number=position;
                Bill_record_add_image_select image= images.get(number);
                background.setBackgroundColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
            }
        });
        expend_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cheek.getText().toString().isEmpty()){
                    if (Double.parseDouble(cheek.getText().toString())>=0.01){
                        Bill_record_data data = new Bill_record_data();
                        data.setRecord_cheek(Double.parseDouble(cheek.getText().toString()));
                        data.setRecord_type("--");
                        data.setRecord_category(images.get(number).getDescribe());
                        data.setRecord_image(images.get(number).getImage());
                        data.setRecord_remark(describe.getText().toString());
                        data.setRecord_timestamp(System.currentTimeMillis());
                        data.save();
                        SimpleDateFormat month_forma = new SimpleDateFormat("yyyy-MM");
                        String month = month_forma.format(System.currentTimeMillis());
                        List<Bill_budget_data> budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,images.get(number).getDescribe()).find(Bill_budget_data.class);
                        if (!budge_data.isEmpty()){
                            Bill_budget_data bd=budge_data.get(0);
                            bd.setBudget_spending(bd.getBudget_spending()+Double.parseDouble(cheek.getText().toString()));
                            bd.save();
                        }
                        getActivity().finish();
                    }
                }
            }
        });
    }
}