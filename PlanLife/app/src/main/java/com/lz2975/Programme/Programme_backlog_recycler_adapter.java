package com.lz2975.Programme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.percentlayout.widget.PercentRelativeLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import java.util.List;

public class Programme_backlog_recycler_adapter extends RecyclerView.Adapter<Programme_backlog_recycler_adapter.ViewHolder> {
    public Context context;
    public List<Programme_backlog_data> datas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        PercentRelativeLayout layout;
        TextView name;
        TextView significance;
        ImageButton finish;
        public ViewHolder (View view){
            super(view);
            layout = (PercentRelativeLayout)view.findViewById(R.id.programme_backlog_data_layout);
            name = (TextView)view.findViewById(R.id.programme_backlog_data_name);
            significance = (TextView)view.findViewById(R.id.programme_backlog_data_significance);
            finish = (ImageButton)view.findViewById(R.id.programme_backlog_data_finish);
        }
    }
    public Programme_backlog_recycler_adapter(Context context,List<Programme_backlog_data> datas) {
        this.context=context;
        this.datas = datas;
    }
    @Override
    public Programme_backlog_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programme_backlog_data,parent,false);
        return new Programme_backlog_recycler_adapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(Programme_backlog_recycler_adapter.ViewHolder holder, int position){
        final Programme_backlog_data data = datas.get(position);
        holder.name.setText(data.getProgramme_backlog_name());
        String tmp="";
        for (int x=0;x<data.getProgramme_backlog_significance();x++){
            tmp+="☆";
        }
        holder.significance.setText(tmp);
        holder.finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setProgramme_backlog_finished(1);
                data.save();
                notifyDataSetChanged();
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Programme_backlog_edit.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Programme_backlog_data", data);
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
