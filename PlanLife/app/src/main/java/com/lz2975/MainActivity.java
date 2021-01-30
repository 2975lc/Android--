package com.lz2975;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lz2975.Bill.Bill;
import com.lz2975.Bookrack.Bookrack;
import com.lz2975.Programme.Programme;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private ViewPager2 VP;
    private RadioGroup RG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VP = (ViewPager2)findViewById(R.id.VPager);
        RG = (RadioGroup)findViewById(R.id.RGroup);

        //关闭父控件滑动，后续需调整
        VP.setUserInputEnabled(false);
        //关闭父控件滑动，后续需调整

        VP_adapter adapter=new VP_adapter(this);
        adapter.addFragment(new Bill());
        adapter.addFragment(new Programme());
        adapter.addFragment(new Bookrack());
        VP.setAdapter(adapter);
        VP.setCurrentItem(0);
        RG.setOnCheckedChangeListener(this);

        VP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        ((RadioButton) findViewById(R.id.bill)).setChecked(true);
                        break;
                    case 1:
                        ((RadioButton) findViewById(R.id.programme)).setChecked(true);
                        break;
                    case 2:
                        ((RadioButton) findViewById(R.id.bookrack)).setChecked(true);
                        break;
                }
            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.bill:
                VP.setCurrentItem(0);
                break;
            case R.id.programme:
                VP.setCurrentItem(1);
                break;
            case R.id.bookrack:
                VP.setCurrentItem(2);
                break;
        }
    }
}
