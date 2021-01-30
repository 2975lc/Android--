package com.lz2975.Bookrack;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lz2975.Bill.Bill_record_add_expend_gridview_adapter;
import com.lz2975.Bill.Bill_record_add_image_select;
import com.lz2975.R;

import java.util.List;

public class Bookrack_add_gridview_adapter extends BaseAdapter {
    private Context context;
    private List<Book_type> datas;
    public Bookrack_add_gridview_adapter(Context context,List<Book_type> datas){
        this.context=context;
        this.datas=datas;
    }
    static class ViewHolder{
        public TextView type;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        Bookrack_add_gridview_adapter.ViewHolder holder = new Bookrack_add_gridview_adapter.ViewHolder();
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.bookrack_add_grid,null);
            holder.type=(TextView)view.findViewById(R.id.bookrack_add_grid_type);
            view.setTag(holder);
        }else {
            holder=(Bookrack_add_gridview_adapter.ViewHolder)view.getTag();
        }
        holder.type.setText(datas.get(position).getName());
        if (datas.get(position).isSelect()){
            holder.type.setBackgroundResource(R.drawable.bookrack_add_shape_select);
        }else {
            holder.type.setBackgroundResource(R.drawable.bookrack_add_shape_no_select);
        }
        return view;
    }
    @Override
    public int getCount(){
        return datas.size();
    }
    @Override
    public Object getItem(int position){
        return datas.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
}