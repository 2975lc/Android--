package com.lz2975.Bill;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Bill_budget_data extends DataSupport implements Comparable<Bill_budget_data>,Serializable {
    private String Budget_month;
    private String Budget_type;
    private double Budget_spending;
    private double Budget_plan;

    public String getBudget_month() {
        return Budget_month;
    }
    public void setBudget_month(String budget_month) {
        Budget_month = budget_month;
    }
    public String getBudget_type() {
        return Budget_type;
    }
    public void setBudget_type(String budget_type) {
        Budget_type = budget_type;
    }
    public double getBudget_spending() {
        return Budget_spending;
    }
    public void setBudget_spending(double budget_spending) {
        Budget_spending = budget_spending;
    }
    public double getBudget_plan() {
        return Budget_plan;
    }
    public void setBudget_plan(double budget_plan) {
        Budget_plan = budget_plan;
    }

    @Override
    public int compareTo(Bill_budget_data data) {
        double i=data.getBudget_plan() - data.getBudget_spending();
        if (i>=0){
            return 1;
        }
        return -1;
    }
}
