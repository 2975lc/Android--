package com.lz2975.Programme;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Programme_backlog_data extends DataSupport implements Comparable<Programme_backlog_data>, Serializable {
    private String Programme_backlog_name;
    private int Programme_backlog_significance;
    private int Programme_backlog_finished;
    private String Programme_backlog_remark;
    private long Programme_backlog_timestamp;

    public String getProgramme_backlog_name() {
        return Programme_backlog_name;
    }
    public void setProgramme_backlog_name(String programme_backlog_name) {
        Programme_backlog_name = programme_backlog_name;
    }
    public int getProgramme_backlog_significance() {
        return Programme_backlog_significance;
    }
    public void setProgramme_backlog_significance(int programme_backlog_significance) {
        Programme_backlog_significance = programme_backlog_significance;
    }
    public int getProgramme_backlog_finished() {
        return Programme_backlog_finished;
    }
    public void setProgramme_backlog_finished(int programme_backlog_finished) {
        Programme_backlog_finished = programme_backlog_finished;
    }
    public String getProgramme_backlog_remark() {
        return Programme_backlog_remark;
    }
    public void setProgramme_backlog_remark(String programme_backlog_remark) {
        Programme_backlog_remark = programme_backlog_remark;
    }
    public long getProgramme_backlog_timestamp() {
        return Programme_backlog_timestamp;
    }
    public void setProgramme_backlog_timestamp(long programme_backlog_timestamp) {
        Programme_backlog_timestamp = programme_backlog_timestamp;
    }
    @Override
    public int compareTo(Programme_backlog_data habit_data) {
        long i=habit_data.getProgramme_backlog_significance() - this.Programme_backlog_significance;
        if (i>0){
            return 1;
        }else if (i==0){
            long l = habit_data.getProgramme_backlog_timestamp()-this.Programme_backlog_timestamp;
            if (l>=0){
                return 1;
            }
        }
        return -1;
    }
}
