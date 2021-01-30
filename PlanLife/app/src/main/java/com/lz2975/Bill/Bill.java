package com.lz2975.Bill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.lz2975.R;
import com.lz2975.VP_adapter;

public class Bill extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private ViewPager2 VP;
    private RadioGroup RG;
    private View view;
    @Override
    public void onViewCreated(View view1, @Nullable Bundle savedInstanceState) {
        view=view1;
        VP = (ViewPager2)view.findViewById(R.id.bill_VPager);
        RG = (RadioGroup)view.findViewById(R.id.bill_RGroup);
        VP_adapter adapter=new VP_adapter(getActivity());
        adapter.addFragment(new Bill_record());
        adapter.addFragment(new Bill_budget());
        adapter.addFragment(new Bill_statement());
        VP.setAdapter(adapter);
        VP.setCurrentItem(0);
        RG.setOnCheckedChangeListener(this);

        VP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        ((RadioButton) view.findViewById(R.id.bill_record)).setChecked(true);
                        break;
                    case 1:
                        ((RadioButton) view.findViewById(R.id.bill_budget)).setChecked(true);
                        break;
                    case 2:
                        ((RadioButton) view.findViewById(R.id.bill_statement)).setChecked(true);
                        break;
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.bill,container,false);
        return view;
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.bill_record:
                VP.setCurrentItem(0);
                break;
            case R.id.bill_budget:
                VP.setCurrentItem(1);
                break;
            case R.id.bill_statement:
                VP.setCurrentItem(2);
                break;
        }
    }
}
