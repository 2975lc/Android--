package com.lz2975.Programme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lz2975.R;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

public class Programme_habit_edit extends AppCompatActivity {
    private Programme_habit_data data;
    private EditText name;
    private ImageButton reduce;
    private TextView significance;
    private ImageButton add;
    private TextView number;
    private TextView total;
    private TextView advance;
    private TextView timestamp;
    private Button edit;
    private Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programme_habit_edit_activity);
        name = (EditText)findViewById(R.id.programme_habit_edit_name);
        reduce = (ImageButton)findViewById(R.id.programme_habit_edit_reduce);
        significance = (TextView)findViewById(R.id.programme_habit_edit_significance);
        add = (ImageButton)findViewById(R.id.programme_habit_edit_add);
        number = (TextView)findViewById(R.id.programme_habit_edit_number);
        total = (TextView)findViewById(R.id.programme_habit_edit_total);
        advance = (TextView)findViewById(R.id.programme_habit_edit_advance);
        timestamp = (TextView)findViewById(R.id.programme_habit_edit_timestamp);
        edit = (Button)findViewById(R.id.programme_habit_edit_edit);
        delete = (Button)findViewById(R.id.programme_habit_edit_delete);

        data=(Programme_habit_data)getIntent().getSerializableExtra("Programme_habit_data");
        data = DataSupport.where("Programme_timestamp=?",String.valueOf(data.getProgramme_timestamp())).find(Programme_habit_data.class).get(0);
        initialize();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getProgramme_significance()<=4){
                    data.setProgramme_significance(data.getProgramme_significance()+1);
                }
                String s="";
                for (int x=0;x<data.getProgramme_significance();x++){
                    s = s + "☆";
                }
                significance.setText(s);
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.getProgramme_significance()>=2){
                    data.setProgramme_significance(data.getProgramme_significance()-1);
                }
                String s="";
                for (int x=0;x<data.getProgramme_significance();x++){
                    s = s + "☆";
                }
                significance.setText(s);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()){
                    data.setProgramme_name(name.getText().toString());
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
    @Override
    public void onResume(){
        super.onResume();
        initialize();
    }
    void initialize(){
        name.setText(data.getProgramme_name());
        number.setText(String.valueOf(data.getProgramme_number()));
        String s="";
        for (int x=0;x<data.getProgramme_significance();x++){
            s = s + "☆";
        }
        significance.setText(s);
        total.setText(get_timestamp_string(data.getProgramme_total()));
        if (data.getProgramme_number()>0){
            advance.setText(get_timestamp_string(data.getProgramme_total()/data.getProgramme_number()));
        }else {
            advance.setText("无执行记录");
        }
        timestamp.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.getProgramme_timestamp()));
    }
    String get_timestamp_string(Long timestamp){
        Long min=timestamp/(1000*60);
        return min+"分钟";
    }
}
