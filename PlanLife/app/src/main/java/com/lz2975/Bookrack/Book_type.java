package com.lz2975.Bookrack;

import java.util.ArrayList;
import java.util.List;

public class Book_type {
    private String name;
    private boolean select;
    Book_type(){}
    Book_type(String name,boolean select){
        this.name=name;
        this.select=select;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isSelect() {
        return select;
    }
    public void setSelect(boolean select) {
        this.select = select;
    }
    public List<Book_type> Book_types(){
        List<Book_type>Book_types = new ArrayList<>();
        Book_types.add(new Book_type("综合类图书",false));
        Book_types.add(new Book_type("哲学、文化",false));
        Book_types.add(new Book_type("政治、经济",false));
        Book_types.add(new Book_type("科学、教育",false));
        Book_types.add(new Book_type("语言、文字",false));
        Book_types.add(new Book_type("文学、艺术",false));
        Book_types.add(new Book_type("历史、地理",false));
        Book_types.add(new Book_type("数理、化学",false));
        Book_types.add(new Book_type("天文、地球",false));
        Book_types.add(new Book_type("生物、医药",false));
        Book_types.add(new Book_type("卫生、环境",false));
        Book_types.add(new Book_type("农业、工业",false));
        Book_types.add(new Book_type("交运、航天",false));
        return Book_types;
    }
}
