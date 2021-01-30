package com.lz2975.Bill;

import com.lz2975.R;

import java.util.ArrayList;
import java.util.List;

public class Bill_record_add_image_select {
    private int image;
    private String describe;
    private int red;
    private int green;
    private int blue;
    public Bill_record_add_image_select(){

    }
    public Bill_record_add_image_select(int image,String describe,int red,int green,int blue){
        this.image=image;
        this.describe=describe;
        this.red=red;
        this.green=green;
        this.blue=blue;
    }

    public int getImage() {
        return image;
    }

    public String getDescribe() {
        return describe;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }


    public List<Bill_record_add_image_select> expends(){
        List<Bill_record_add_image_select> expends = new ArrayList<>();
        expends.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_expend_repast,"餐饮",255,165,0));
        expends.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_expend_shop,"购物",255,0,0));
        expends.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_expend_entertainment,"娱乐",87,250,255));
        expends.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_expend_education,"教育",128,0,128));
        return expends;
    }
    public List<Bill_record_add_image_select> incomes(){
        List<Bill_record_add_image_select> income = new ArrayList<>();
        income.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_income_alimony,"生活费",18,150,219));
        income.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_income_pluralistic,"兼职",212,35,122));
        income.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_income_salary,"工资",255,0,0));
        income.add(new Bill_record_add_image_select(R.mipmap.icon_bill_record_income_packet,"红包",216,30,6));
        return income;
    }
}
