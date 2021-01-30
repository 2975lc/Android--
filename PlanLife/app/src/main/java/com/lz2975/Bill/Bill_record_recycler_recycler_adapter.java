package com.lz2975.Bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import java.util.List;

public class Bill_record_recycler_recycler_adapter extends RecyclerView.Adapter<Bill_record_recycler_recycler_adapter.ViewHolder> {
    public List<Bill_record_data> datas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView left;
        TextView right;
        ImageView icon ;
        public ViewHolder (View view){
            super(view);
            left = (TextView)view.findViewById(R.id.bill_record_recycler_data_left);
            right = (TextView)view.findViewById(R.id.bill_record_recycler_data_right);
            icon = (ImageView) view.findViewById(R.id.bill_record_recycler_data_icon);
        }
    }
    public Bill_record_recycler_recycler_adapter(List<Bill_record_data> datas) {
        this.datas = datas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_record_recycler_data,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final Bill_record_data data = datas.get(position);
        holder.icon.setImageResource(data.getRecord_image());
        if (data.getRecord_type().equals("++")){
            holder.left.setText(data.getRecord_category()+data.getRecord_cheek());
            holder.left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    to_edit(view,data);
                }
            });
        }else if (data.getRecord_type().equals("--")){
            holder.right.setText(data.getRecord_category()+data.getRecord_cheek());
            holder.right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    to_edit(view,data);
                }
            });
        }
    }
    @Override
    public int getItemCount(){
        return datas.size();
    }
    private void to_edit(View view,Bill_record_data data){
        Intent intent = new Intent();
        intent.setClass(view.getContext(), Bill_record_edit.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Bill_record_data", data);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }
}