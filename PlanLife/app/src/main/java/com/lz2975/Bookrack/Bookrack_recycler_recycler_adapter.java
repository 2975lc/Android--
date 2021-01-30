package com.lz2975.Bookrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.Bill.Bill_record_data;
import com.lz2975.Bill.Bill_record_edit;
import com.lz2975.Bill.Bill_record_recycler_recycler_adapter;
import com.lz2975.R;

import java.util.List;

public class Bookrack_recycler_recycler_adapter extends RecyclerView.Adapter<Bookrack_recycler_recycler_adapter.ViewHolder> {
    public List<Book_data> datas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View MyView;
        TextView name;
        TextView author;
        TextView cheek ;
        public ViewHolder (View view){
            super(view);
            MyView=view;
            name = (TextView)view.findViewById(R.id.bookrack_recycler_data_name);
            author = (TextView)view.findViewById(R.id.bookrack_recycler_data_author);
            cheek = (TextView) view.findViewById(R.id.bookrack_recycler_data_cheek);
        }
    }
    public Bookrack_recycler_recycler_adapter(List<Book_data> datas) {
        this.datas = datas;
    }
    @Override
    public Bookrack_recycler_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookrack_recycler_data,parent,false);
        return new Bookrack_recycler_recycler_adapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(Bookrack_recycler_recycler_adapter.ViewHolder holder, int position){
        final Book_data data = datas.get(position);
        holder.name.setText(data.getBook_name());
        holder.author.setText(data.getBook_author());
        holder.cheek.setText(String.valueOf(data.getBook_cheek()));
        holder.MyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_edit(view,data);
            }
        });
    }
    @Override
    public int getItemCount(){
        return datas.size();
    }
    private void to_edit(View view,Book_data data){
        Intent intent = new Intent();
        intent.setClass(view.getContext(), Bookrack_edit.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Book_data", data);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }
}