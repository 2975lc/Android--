package com.lz2975.Bill;

import androidx.appcompat.app.AppCompatActivity;

import com.lz2975.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.List;

public class Bill_budget_add extends AppCompatActivity {
    private TextView plug;
    private EditText cheek;
    private GridView grid;
    private List<Bill_record_add_image_select> images=new Bill_record_add_image_select().expends();
    private int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_budget_add_activity);
        plug = (TextView)findViewById(R.id.bill_budget_add_plug);
        cheek = (EditText)findViewById(R.id.bill_budget_add_cheek);
        grid = (GridView)findViewById(R.id.bill_budget_add_gridview);
        Bill_record_add_image_select image= images.get(number);
        plug.setBackgroundColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
        cheek.setTextColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
        Bill_record_add_expend_gridview_adapter adapter = new Bill_record_add_expend_gridview_adapter(this,images);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number=position;
                Bill_record_add_image_select image= images.get(number);
                plug.setBackgroundColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
                cheek.setTextColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
            }
        });
        plug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cheek.getText().toString().isEmpty()){
                    if (Double.parseDouble(cheek.getText().toString())>=0.01){
                        SimpleDateFormat month_forma = new SimpleDateFormat("yyyy-MM");
                        String month = month_forma.format(System.currentTimeMillis());
                        List<Bill_budget_data> budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,images.get(number).getDescribe()).find(Bill_budget_data.class);
                        Bill_budget_data data=new Bill_budget_data();
                        if (!budge_data.isEmpty()){
                            data=budge_data.get(0);
                            data.setBudget_plan(Double.parseDouble(cheek.getText().toString())+data.getBudget_plan());
                            data.save();
                        }else{
                            data.setBudget_type(images.get(number).getDescribe());
                            data.setBudget_month(month);
                            data.setBudget_spending(0);
                            data.setBudget_plan(Double.parseDouble(cheek.getText().toString()));
                            data.save();
                        }
                        finish();
                    }
                }
            }
        });
    }
}