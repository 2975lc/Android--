package com.lz2975.Bill;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.R;

import java.util.List;

public class Bill_record_add_expend_gridview_adapter extends BaseAdapter {
    private Context context;
    private List<Bill_record_add_image_select> images;
    public Bill_record_add_expend_gridview_adapter(Context context,List<Bill_record_add_image_select> images){
        this.context=context;
        this.images=images;
    }
    static class ViewHolder{
        public ImageView imagebutton;
        public TextView describe;
    }

    @Override
    public View getView(int position,View view,ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        final Bill_record_add_image_select image = images.get(position);
        if (view==null){
            view=LayoutInflater.from(context).inflate(R.layout.bill_record_add_image,null);
            holder.imagebutton=(ImageView)view.findViewById(R.id.bill_record_add_expend_cardview_image);
            holder.describe=(TextView)view.findViewById(R.id.bill_record_add_expend_cardview_describe);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        holder.imagebutton.setImageResource(image.getImage());
        holder.describe.setText(image.getDescribe());
        holder.describe.setTextColor(Color.rgb(image.getRed(),image.getGreen(),image.getBlue()));
        return view;
    }
    @Override
    public int getCount(){
        return images.size();
    }
    @Override
    public Object getItem(int position){
        return images.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
}