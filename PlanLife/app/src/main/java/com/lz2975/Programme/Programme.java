package com.lz2975.Programme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.lz2975.R;
import com.lz2975.VP_adapter;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Programme extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private ViewPager2 VP;
    private RadioGroup RG;
    private View view;
    private TextView unfinished_backlog;
    private Long all=Long.valueOf(0);
    private TextView habit_time;

    @Override
    public void onViewCreated(View view1, @Nullable Bundle savedInstanceState) {
        view = view1;
        VP = (ViewPager2) view.findViewById(R.id.programme_VPager);
        RG = (RadioGroup) view.findViewById(R.id.programme_RGroup);
        unfinished_backlog = (TextView)view.findViewById(R.id.programme_title_unfinished_backlog);
        habit_time = (TextView)view.findViewById(R.id.programme_title_habit_time);
        unfinished_backlog.setText("未完成的代办："+DataSupport.where("Programme_backlog_finished=0").find(Programme_backlog_data.class).size()+"个");
        List<Programme_habit_data> datas = DataSupport.findAll(Programme_habit_data.class);
        for (Programme_habit_data data:datas){
            all+=data.getProgramme_total();
        }
        all=all/(1000*60);
        habit_time.setText("日常习惯总完成时间："+all+"分钟");

        VP_adapter adapter = new VP_adapter(getActivity());
        adapter.addFragment(new Programme_habit());
        adapter.addFragment(new Programme_backlog());
        VP.setAdapter(adapter);
        VP.setCurrentItem(0);
        RG.setOnCheckedChangeListener(this);

        VP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        ((RadioButton) view.findViewById(R.id.programme_habit)).setChecked(true);
                        break;
                    case 1:
                        ((RadioButton) view.findViewById(R.id.programme_backlog)).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.programme, container, false);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.programme_habit:
                VP.setCurrentItem(0);
                break;
            case R.id.programme_backlog:
                VP.setCurrentItem(1);
                break;
        }
    }
}