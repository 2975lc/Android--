package com.lz2975.Programme;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.Bill.Bill_record_add_image_select;
import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

public class Programme_backlog extends Fragment {
    private EditText edit;
    private Button add;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private Programme_backlog_recycler_adapter adapter;
    private List<Programme_backlog_data> datas;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        edit = (EditText)view.findViewById(R.id.programme_backlog_edit);
        add = (Button)view.findViewById(R.id.programme_backlog_add);
        recycler = (RecyclerView)view.findViewById(R.id.programme_backlog_recycler);
        initialize();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit.getText().toString();
                if (!name.isEmpty()){
                    Programme_backlog_data data = new Programme_backlog_data();
                    data.setProgramme_backlog_name(name);
                    data.setProgramme_backlog_significance(1);
                    data.setProgramme_backlog_finished(0);
                    data.setProgramme_backlog_remark("");
                    data.setProgramme_backlog_timestamp(System.currentTimeMillis());
                    data.save();
                    edit.setText("");
                    onResume();
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.programme_backlog,container,false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        initialize();
    }
    private void initialize(){
        datas= DataSupport.where("Programme_backlog_finished=0").find(Programme_backlog_data.class);
        Collections.sort(datas);
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new Programme_backlog_recycler_adapter(getContext(),datas);
        recycler.setAdapter(adapter);
    }
}