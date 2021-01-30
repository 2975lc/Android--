package com.lz2975.Programme;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Programme_habit_data extends DataSupport implements Comparable<Programme_habit_data>, Serializable {
    private String Programme_name;
    private int Programme_significance;
    private int Programme_number;
    private long Programme_total;
    private long Programme_timestamp;

    public String getProgramme_name() {
        return Programme_name;
    }
    public void setProgramme_name(String programme_name) {
        Programme_name = programme_name;
    }
    public int getProgramme_significance() {
        return Programme_significance;
    }
    public void setProgramme_significance(int programme_significance) {
        Programme_significance = programme_significance;
    }
    public int getProgramme_number() {
        return Programme_number;
    }
    public void setProgramme_number(int programme_number) {
        Programme_number = programme_number;
    }
    public long getProgramme_total() {
        return Programme_total;
    }
    public void setProgramme_total(long programme_total) {
        Programme_total = programme_total;
    }
    public long getProgramme_timestamp() {
        return Programme_timestamp;
    }
    public void setProgramme_timestamp(long programme_timestamp) {
        Programme_timestamp = programme_timestamp;
    }

    @Override
    public int compareTo(Programme_habit_data habit_data) {
        long i=habit_data.getProgramme_significance() - this.Programme_significance;
        if (i>0){
            return 1;
        }else if (i==0){
            long l = habit_data.getProgramme_timestamp()-this.Programme_timestamp;
            if (l>=0){
                return 1;
            }
        }
        return -1;
    }
}
