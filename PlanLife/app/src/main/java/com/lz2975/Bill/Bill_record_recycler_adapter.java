package com.lz2975.Bill;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill_record_recycler_adapter extends RecyclerView.Adapter<Bill_record_recycler_adapter.ViewHolder> {
    public Context context;
    public List<List<Bill_record_data>> datass;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView left;
        TextView right;
        ImageView point;
        RecyclerView recycler ;
        public ViewHolder (View view){
            super(view);
            left = (TextView)view.findViewById(R.id.bill_record_recycler_left);
            right = (TextView)view.findViewById(R.id.bill_record_recycler_right);
            point = (ImageView)view.findViewById(R.id.bill_record_recycler_point);
            recycler = (RecyclerView)view.findViewById(R.id.bill_record_recycler_recycler);
        }
    }
    public Bill_record_recycler_adapter(Context context,List<List<Bill_record_data>> datass) {
        this.context=context;
        this.datass = datass;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_record_recycler_recycler,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        List<Bill_record_data> datas = datass.get(position);
        if (!datas.isEmpty()){
            double num=0;
            for (Bill_record_data data:datas){
                if (data.getRecord_type().equals("++")){
                    num+=data.getRecord_cheek();
                }else if (data.getRecord_type().equals("--")){
                    num-=data.getRecord_cheek();
                }
            }
            SimpleDateFormat date_forma = new SimpleDateFormat("yyyy-MM-dd");
            String date = date_forma.format(new Date(datas.get(0).getRecord_timestamp()));
            if (num>=0){
                holder.point.setImageResource(R.drawable.icon_bill_record_recycler_point_blue);
                holder.left.setText(""+num);
                holder.right.setText(date);
                holder.left.setTextColor(Color.rgb(87,250,255));
                holder.right.setTextColor(Color.rgb(87,250,255));
            }else {
                holder.point.setImageResource(R.drawable.icon_bill_record_recycler_point_read);
                holder.left.setText(date);
                holder.right.setText(""+num);
                holder.left.setTextColor(Color.rgb(216,30,6));
                holder.right.setTextColor(Color.rgb(216,30,6));
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            Bill_record_recycler_recycler_adapter adapter=new Bill_record_recycler_recycler_adapter(datas);
            holder.recycler.setLayoutManager(layoutManager);
            holder.recycler.setAdapter(adapter);
        }
    }
    @Override
    public int getItemCount(){
        return datass.size();
    }
}
