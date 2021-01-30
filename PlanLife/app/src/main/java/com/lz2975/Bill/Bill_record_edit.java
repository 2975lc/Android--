package com.lz2975.Bill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bill_record_edit extends AppCompatActivity {
    private Bill_record_data data;
    private ImageView image;
    private EditText cheek;
    private Button expend;
    private Button income;
    private GridView grid;
    private TextView timestamp;
    private TextView remark;
    private Button edit;
    private Button delete;

    private List<Bill_record_add_image_select> images;
    private int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_record_edit_activity);
        Intent intent = this.getIntent();
        data=(Bill_record_data)intent.getSerializableExtra("Bill_record_data");
        final Bill_record_data old=(Bill_record_data)intent.getSerializableExtra("Bill_record_data");
        data = DataSupport.where("Record_timestamp=?",String.valueOf(data.getRecord_timestamp())).find(Bill_record_data.class).get(0);

        image = (ImageView)findViewById(R.id.bill_record_edit_image);
        cheek = (EditText)findViewById(R.id.bill_record_edit_cheek);
        expend = (Button)findViewById(R.id.bill_record_edit_expend);
        income = (Button)findViewById(R.id.bill_record_edit_income);
        grid = (GridView)findViewById(R.id.bill_record_edit_gridview);
        timestamp = (TextView)findViewById(R.id.bill_record_edit_timestamp);
        remark = (TextView)findViewById(R.id.bill_record_edit_remark);
        edit = (Button)findViewById(R.id.bill_record_edit_edit);
        delete = (Button)findViewById(R.id.bill_record_edit_delete);

        initialize();

        expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                images=new Bill_record_add_image_select().expends();
                Bill_record_add_expend_gridview_adapter adapter = new Bill_record_add_expend_gridview_adapter(view.getContext(),images);
                grid.setAdapter(adapter);
                data.setRecord_type("--");
            }
        });
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                images=new Bill_record_add_image_select().incomes();
                Bill_record_add_expend_gridview_adapter adapter = new Bill_record_add_expend_gridview_adapter(view.getContext(),images);
                grid.setAdapter(adapter);
                data.setRecord_type("++");
            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number=position;
                Bill_record_add_image_select image_select= images.get(number);
                image.setImageResource(image_select.getImage());
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cheek.getText().toString().isEmpty()){
                    if (Double.parseDouble(cheek.getText().toString())>=0.01){
                        data.setRecord_cheek(Double.parseDouble(cheek.getText().toString()));
                        data.setRecord_category(images.get(number).getDescribe());
                        data.setRecord_image(images.get(number).getImage());
                        data.setRecord_remark(remark.getText().toString());
                        data.save();

                        if (old.getRecord_type().equals("--")){
                            String month = new SimpleDateFormat("yyyy-MM").format(System.currentTimeMillis());
                            List<Bill_budget_data> budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,old.getRecord_category()).find(Bill_budget_data.class);
                            if (!budge_data.isEmpty()){
                                Bill_budget_data bd=budge_data.get(0);
                                bd.setBudget_spending(bd.getBudget_spending()-old.getRecord_cheek());
                                bd.save();
                            }
                            if (data.getRecord_type().equals("--")){
                                budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,data.getRecord_category()).find(Bill_budget_data.class);
                                if (!budge_data.isEmpty()){
                                    Bill_budget_data bd=budge_data.get(0);
                                    bd.setBudget_spending(bd.getBudget_spending()+data.getRecord_cheek());
                                    bd.save();
                                }
                            }
                        }else{
                            String month = new SimpleDateFormat("yyyy-MM").format(System.currentTimeMillis());
                            List<Bill_budget_data> budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,old.getRecord_category()).find(Bill_budget_data.class);
                            if (data.getRecord_type().equals("--")){
                                budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,data.getRecord_category()).find(Bill_budget_data.class);
                                if (!budge_data.isEmpty()){
                                    Bill_budget_data bd=budge_data.get(0);
                                    bd.setBudget_spending(bd.getBudget_spending()+data.getRecord_cheek());
                                    bd.save();
                                }
                            }
                        }

                        finish();
                    }
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.delete();
                if (old.getRecord_type().equals("--")){
                    String month = new SimpleDateFormat("yyyy-MM").format(System.currentTimeMillis());
                    List<Bill_budget_data> budge_data = DataSupport.where("Budget_month=? and Budget_type=?",month,old.getRecord_category()).find(Bill_budget_data.class);
                    if (!budge_data.isEmpty()){
                        Bill_budget_data bd=budge_data.get(0);
                        bd.setBudget_spending(bd.getBudget_spending()-old.getRecord_cheek());
                        bd.save();
                    }
                }
                finish();
            }
        });
    }
    private void initialize(){
        if (data.getRecord_type().equals("--")){
            images=new Bill_record_add_image_select().expends();
        }else {
            images=new Bill_record_add_image_select().incomes();
        }
        image.setImageResource(data.getRecord_image());
        Bill_record_add_image_select image_select= images.get(number);
        image.setImageResource(image_select.getImage());
        cheek.setText(String.valueOf(data.getRecord_cheek()));
        Bill_record_add_expend_gridview_adapter adapter = new Bill_record_add_expend_gridview_adapter(this,images);
        grid.setAdapter(adapter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestamp.setText(simpleDateFormat.format(new Date(data.getRecord_timestamp())));
        remark.setText(data.getRecord_remark());
    }
}
