package com.lz2975.Programme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.percentlayout.widget.PercentRelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.Bill.Bill_record_data;
import com.lz2975.Bill.Bill_record_recycler_adapter;
import com.lz2975.Bill.Bill_record_recycler_recycler_adapter;
import com.lz2975.Bookrack.Bookrack_edit;
import com.lz2975.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Programme_habit_recycler_adapter extends RecyclerView.Adapter<Programme_habit_recycler_adapter.ViewHolder> {
    public Context context;
    public List<Programme_habit_data> datas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        PercentRelativeLayout layout;
        TextView name;
        TextView significance;
        ImageButton begin;
        public ViewHolder (View view){
            super(view);
            layout = (PercentRelativeLayout)view.findViewById(R.id.programme_habit_data_layout);
            name = (TextView)view.findViewById(R.id.programme_habit_data_name);
            significance = (TextView)view.findViewById(R.id.programme_habit_data_significance);
            begin = (ImageButton)view.findViewById(R.id.programme_habit_data_begin);
        }
    }
    public Programme_habit_recycler_adapter(Context context,List<Programme_habit_data> datas) {
        this.context=context;
        this.datas = datas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programme_habit_data,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final Programme_habit_data data = datas.get(position);
        holder.name.setText(data.getProgramme_name());
        String tmp="";
        for (int x=0;x<data.getProgramme_significance();x++){
            tmp+="☆";
        }
        holder.significance.setText(tmp);
        holder.begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Programme_habit_begin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Programme_habit_data", data);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Programme_habit_edit.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Programme_habit_data", data);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount(){
        return datas.size();
    }
}