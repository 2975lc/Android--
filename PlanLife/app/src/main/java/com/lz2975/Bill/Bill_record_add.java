package com.lz2975.Bill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lz2975.R;
import com.lz2975.VP_adapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Bill_record_add extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private ViewPager2 VP;
    private RadioGroup RG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_record_add_activity);
        VP = (ViewPager2)findViewById(R.id.bill_record_add_VPager);
        RG = (RadioGroup)findViewById(R.id.bill_record_add_RGroup);
        VP_adapter adapter=new VP_adapter(this);
        adapter.addFragment(new Bill_record_add_expend());
        adapter.addFragment(new Bill_record_add_income());
        VP.setAdapter(adapter);
        VP.setCurrentItem(0);
        RG.setOnCheckedChangeListener(this);
        VP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        ((RadioButton) findViewById(R.id.bill_record_add_expend)).setChecked(true);
                        break;
                    case 1:
                        ((RadioButton) findViewById(R.id.bill_record_add_income)).setChecked(true);
                        break;
                }
            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.bill_record_add_expend:
                VP.setCurrentItem(0);
                break;
            case R.id.bill_record_add_income:
                VP.setCurrentItem(1);
                break;
        }
    }
}
