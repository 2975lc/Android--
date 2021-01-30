package com.lz2975.Programme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Programme_habit extends Fragment {
    private EditText edit;
    private Button add;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private Programme_habit_recycler_adapter adapter;
    private List<Programme_habit_data> datas;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        edit = (EditText)view.findViewById(R.id.programme_habit_edit);
        add = (Button)view.findViewById(R.id.programme_habit_add);
        recycler = (RecyclerView)view.findViewById(R.id.programme_habit_recycler);
        initialize();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit.getText().toString();
                if (!name.isEmpty()){
                    Programme_habit_data data = new Programme_habit_data();
                    data.setProgramme_name(name);
                    data.setProgramme_significance(1);
                    data.setProgramme_number(0);
                    data.setProgramme_total(0);
                    data.setProgramme_timestamp(System.currentTimeMillis());
                    data.save();
                    edit.setText("");
                    onResume();
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.programme_habit,container,false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        initialize();
    }
    private void initialize(){
        datas= DataSupport.findAll(Programme_habit_data.class);
        Collections.sort(datas);
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new Programme_habit_recycler_adapter(getContext(),datas);
        recycler.setAdapter(adapter);
    }
}