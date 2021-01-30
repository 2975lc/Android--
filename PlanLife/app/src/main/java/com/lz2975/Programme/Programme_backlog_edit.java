package com.lz2975.Programme;

import androidx.appcompat.app.AppCompatActivity;
import com.lz2975.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

public class Programme_backlog_edit extends AppCompatActivity {
    private Programme_backlog_data data;
    private EditText name;
    private TextView significance;
    private ImageButton add;
    private ImageButton reduce;
    private EditText remark;
    private TextView timestamp;
    private Button edit;
    private Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programme_backlog_edit_activity);
        name = (EditText)findViewById(R.id.programme_backlog_edit_name);
        significance = (TextView)findViewById(R.id.programme_backlog_edit_significance);
        add = (ImageButton)findViewById(R.id.programme_backlog_edit_add);
        reduce = (ImageButton)findViewById(R.id.programme_backlog_edit_reduce);
        remark = (EditText)findViewById(R.id.programme_habit_edit_remark);
        timestamp = (TextView)findViewById(R.id.programme_habit_edit_timestamp);
        edit = (Button)findViewById(R.id.programme_backlog_edit_edit);
        delete = (Button)findViewById(R.id.programme_backlog_edit_delete);

        data=(Programme_backlog_data)getIntent().getSerializableExtra("Programme_backlog_data");
        data = DataSupport.where("Programme_backlog_timestamp=?",String.valueOf(data.getProgramme_backlog_timestamp())).find(Programme_backlog_data.class).get(0);
        initialize();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getProgramme_backlog_significance()<=4){
                    data.setProgramme_backlog_significance(data.getProgramme_backlog_significance()+1);
                }
                String s="";
                for (int x=0;x<data.getProgramme_backlog_significance();x++){
                    s = s + "☆";
                }
                significance.setText(s);
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getProgramme_backlog_significance()>=2){
                    data.setProgramme_backlog_significance(data.getProgramme_backlog_significance()-1);
                }
                String s="";
                for (int x=0;x<data.getProgramme_backlog_significance();x++){
                    s = s + "☆";
                }
                significance.setText(s);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()){
                    data.setProgramme_backlog_name(name.getText().toString());
                    data.setProgramme_backlog_remark(remark.getText().toString());
                    data.save();
                    finish();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.delete();
                finish();
            }
        });

    }
    void initialize(){
        name.setText(data.getProgramme_backlog_name());
        String s="";
        for (int x=0;x<data.getProgramme_backlog_significance();x++){
            s = s + "☆";
        }
        remark.setText(data.getProgramme_backlog_remark());
        significance.setText(s);
        timestamp.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.getProgramme_backlog_timestamp()));
    }
}
