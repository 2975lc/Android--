package com.lz2975.Bill;

import org.litepal.crud.DataSupport;
import java.io.Serializable;
public class Bill_record_data extends DataSupport implements Comparable<Bill_record_data>, Serializable {
    private double Record_cheek;
    private String Record_type;
    private String Record_category;
    private int Record_image;
    private String Record_remark;
    private Long Record_timestamp;

    public double getRecord_cheek() {
        return Record_cheek;
    }

    public void setRecord_cheek(double record_cheek) {
        Record_cheek = record_cheek;
    }

    public String getRecord_type() {
        return Record_type;
    }

    public void setRecord_type(String record_type) {
        Record_type = record_type;
    }

    public String getRecord_category() {
        return Record_category;
    }

    public void setRecord_category(String record_category) {
        Record_category = record_category;
    }

    public int getRecord_image() {
        return Record_image;
    }

    public void setRecord_image(int record_image) {
        Record_image = record_image;
    }

    public String getRecord_remark() {
        return Record_remark;
    }

    public void setRecord_remark(String record_remark) {
        Record_remark = record_remark;
    }

    public Long getRecord_timestamp() {
        return Record_timestamp;
    }

    public void setRecord_timestamp(Long record_timestamp) {
        Record_timestamp = record_timestamp;
    }

    @Override
    public int compareTo(Bill_record_data record_data) {
        long i=record_data.getRecord_timestamp() - this.Record_timestamp;
        if (i>=0){
            return 1;
        }
        return -1;
    }
}
