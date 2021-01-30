package com.lz2975.Bill;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import java.util.List;

public class Bill_statement_recycler_adapter extends RecyclerView.Adapter<Bill_statement_recycler_adapter.ViewHolder> {
    public List<Bill_statement_data> datas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView ratio;
        TextView cheek;
        public ViewHolder (View view){
            super(view);
            name = (TextView)view.findViewById(R.id.bill_statement_recycler_name);
            ratio = (TextView)view.findViewById(R.id.bill_statement_recycler_ratio);
            cheek = (TextView) view.findViewById(R.id.bill_statement_recycler_cheek);
        }
    }
    public Bill_statement_recycler_adapter(List<Bill_statement_data> datas) {
        this.datas = datas;
    }
    @Override
    public Bill_statement_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_statement_recycler_data,parent,false);
        return new Bill_statement_recycler_adapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(Bill_statement_recycler_adapter.ViewHolder holder, int position){
        final Bill_statement_data data = datas.get(position);
        holder.name.setText(data.getName());
        holder.ratio.setText(String.format("%.2f",data.getCheek()/datas.get(0).getCheek()*100)+"%");
        holder.cheek.setText(String.format("%.2f",data.getCheek()));
    }
    @Override
    public int getItemCount(){
        return datas.size();
    }
}
