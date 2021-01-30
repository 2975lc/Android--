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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lz2975.R;

import java.util.ArrayList;
import java.util.List;

public class Bill_record_add_income extends Fragment {
    private TextView background;
    private EditText cheek;
    private EditText describe;
    private Button income_finish;
    private GridView grid;
    private List<Bill_record_add_image_select> images = new Bill_record_add_image_select().incomes();
    private int number=0;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        background = (TextView)view.findViewById(R.id.bill_record_add_income_background);
        cheek = (EditText)view.findViewById(R.id.bill_record_add_income_cheek);
        describe = (EditText)view.findViewById(R.id.bill_record_add_income_describe);
        income_finish = (Button)view.findViewById(R.id.bill_record_add_income_finish);
        grid = (GridView)view.findViewById(R.id.bill_record_add_income_gridview);
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
        income_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cheek.getText().toString().isEmpty()){
                    if (Double.parseDouble(cheek.getText().toString())>=0.01){
                        Bill_record_data data=new Bill_record_data();
                        data.setRecord_cheek(Double.parseDouble(cheek.getText().toString()));
                        data.setRecord_type("++");
                        data.setRecord_category(images.get(number).getDescribe());
                        data.setRecord_image(images.get(number).getImage());
                        data.setRecord_remark(describe.getText().toString());
                        data.setRecord_timestamp(System.currentTimeMillis());
                        data.save();
                        getActivity().finish();
                    }
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.bill_record_add_income,container,false);
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
        income_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cheek.getText().toString().isEmpty()){
                    if (Double.parseDouble(cheek.getText().toString())>=0.01){
                        Bill_record_data data=new Bill_record_data();
                        data.setRecord_cheek(Double.parseDouble(cheek.getText().toString()));
                        data.setRecord_type("++");
                        data.setRecord_category(images.get(number).getDescribe());
                        data.setRecord_image(images.get(number).getImage());
                        data.setRecord_remark(describe.getText().toString());
                        data.setRecord_timestamp(System.currentTimeMillis());
                        data.save();
                        getActivity().finish();
                    }
                }
            }
        });
    }
}