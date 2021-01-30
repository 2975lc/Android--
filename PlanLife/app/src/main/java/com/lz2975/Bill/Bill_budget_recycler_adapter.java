package com.lz2975.Bill;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lz2975.R;
import java.util.List;

public class Bill_budget_recycler_adapter extends RecyclerView.Adapter<Bill_budget_recycler_adapter.ViewHolder> {
    public List<Bill_budget_data> datas;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView type;
        TextView month;
        TextView surplus;
        TextView spending;
        TextView budget;
        public ViewHolder (View view){
            super(view);
            type = (TextView)view.findViewById(R.id.bill_budget_recycler_data_type);
            month = (TextView)view.findViewById(R.id.bill_budget_recycler_data_month);
            surplus = (TextView) view.findViewById(R.id.bill_budget_recycler_data_surplus);
            spending = (TextView)view.findViewById(R.id.bill_budget_recycler_data_spending);
            budget = (TextView) view.findViewById(R.id.bill_budget_recycler_data_budget);
        }
    }
    public Bill_budget_recycler_adapter(List<Bill_budget_data> datas) {
        this.datas = datas;
    }
    @Override
    public Bill_budget_recycler_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_budget_recycler_data,parent,false);
        return new Bill_budget_recycler_adapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(Bill_budget_recycler_adapter.ViewHolder holder, int position){
        final Bill_budget_data data = datas.get(position);
        holder.type.setText(data.getBudget_type());
        holder.month.setText(data.getBudget_month());
        holder.surplus.setText(String.valueOf(data.getBudget_plan()-data.getBudget_spending()));
        holder.spending.setText(String.valueOf(data.getBudget_spending()));
        holder.budget.setText(String.valueOf(data.getBudget_plan()));
        if (data.getBudget_plan()-data.getBudget_spending()>=0){
            holder.surplus.setTextColor(Color.argb(100,0,255,0));
        }else{
            holder.surplus.setTextColor(Color.argb(100,255,0,0));
        }
    }
    @Override
    public int getItemCount(){
        return datas.size();
    }
}