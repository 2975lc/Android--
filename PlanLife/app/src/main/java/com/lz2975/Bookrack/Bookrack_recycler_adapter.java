package com.lz2975.Bookrack;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.Bill.Bill_record_data;
import com.lz2975.Bill.Bill_record_recycler_adapter;
import com.lz2975.Bill.Bill_record_recycler_recycler_adapter;
import com.lz2975.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bookrack_recycler_adapter extends RecyclerView.Adapter<Bookrack_recycler_adapter.ViewHolder> {
    public Context context;
    public List<List<Book_data>> datass;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView type;
        RecyclerView recycler ;
        public ViewHolder (View view){
            super(view);
            type = (TextView)view.findViewById(R.id.bookrack_recycler_type);
            recycler = (RecyclerView)view.findViewById(R.id.bookrack_recycler_recycler);
        }
    }
    public Bookrack_recycler_adapter(Context context,List<List<Book_data>> datass) {
        this.context=context;
        this.datass = datass;
    }
    @Override
    public Bookrack_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookrack_recycler_recycler,parent,false);
        return new Bookrack_recycler_adapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(Bookrack_recycler_adapter.ViewHolder holder, int position){
        holder.type.setText(String.valueOf(new Book_data().Book_type().get(position)));
        List<Book_data> datas = datass.get(position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        Bookrack_recycler_recycler_adapter adapter=new Bookrack_recycler_recycler_adapter(datas);
        holder.recycler.setLayoutManager(layoutManager);
        holder.recycler.setAdapter(adapter);
    }
    @Override
    public int getItemCount(){
        return datass.size();
    }
}