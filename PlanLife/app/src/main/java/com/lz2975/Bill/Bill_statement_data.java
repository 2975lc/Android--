package com.lz2975.Bill;

public class Bill_statement_data {
    private String name;
    private double cheek;

    Bill_statement_data(String name,double cheek){
        this.name=name;
        this.cheek=cheek;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCheek() {
        return cheek;
    }
    public void setCheek(double cheek) {
        this.cheek = cheek;
    }
}
