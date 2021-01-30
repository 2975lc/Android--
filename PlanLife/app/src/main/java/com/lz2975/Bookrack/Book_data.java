package com.lz2975.Bookrack;

import com.lz2975.Bill.Bill_record_data;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book_data extends DataSupport implements Comparable<Book_data>, Serializable {
    private int Book_typeID;
    private String Book_name;
    private String Book_author;
    private double Book_cheek;
    private long Book_timestamp;

    public int getBook_typeID() {
        return Book_typeID;
    }
    public void setBook_typeID(int book_typeID) {
        Book_typeID = book_typeID;
    }
    public String getBook_name() {
        return Book_name;
    }
    public void setBook_name(String book_name) {
        Book_name = book_name;
    }
    public String getBook_author() {
        return Book_author;
    }
    public void setBook_author(String book_author) {
        Book_author = book_author;
    }
    public double getBook_cheek() {
        return Book_cheek;
    }
    public void setBook_cheek(double book_cheek) {
        Book_cheek = book_cheek;
    }
    public long getBook_timestamp() {
        return Book_timestamp;
    }
    public void setBook_timestamp(long book_timestamp) {
        Book_timestamp = book_timestamp;
    }

    public List<String> Book_type(){
        List<String>Book_type = new ArrayList<>();
        Book_type.add("综合类图书");
        Book_type.add("哲学、文化");
        Book_type.add("政治、经济");
        Book_type.add("科学、教育");
        Book_type.add("语言、文字");
        Book_type.add("文学、艺术");
        Book_type.add("历史、地理");
        Book_type.add("数理、化学");
        Book_type.add("天文、地球");
        Book_type.add("生物、医药");
        Book_type.add("卫生、环境");
        Book_type.add("农业、工业");
        Book_type.add("交运、航天");
        return Book_type;
    }
    @Override
    public int compareTo(Book_data data) {
        if (this.Book_typeID >data.getBook_typeID()){
            return 1;
        }else if (this.Book_typeID <data.getBook_typeID()){
            return -1;
        }else{
            if (this.Book_cheek>data.getBook_cheek()){
                return 1;
            }else{
                return -1;
            }
        }
    }
}
