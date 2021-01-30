package com.lz2975.Programme;

import androidx.appcompat.app.AppCompatActivity;
import com.lz2975.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

public class Programme_habit_begin extends AppCompatActivity {
    private Programme_habit_data data;
    private Chronometer time;
    private ImageButton pause;
    private ImageButton go;
    private ImageButton stop;
    private Long duration=Long.valueOf(0);
    private Long begin=SystemClock.elapsedRealtime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programme_habit_begin_activity);
        time = (Chronometer)findViewById(R.id.programme_habit_begin_time);
        pause = (ImageButton)findViewById(R.id.programme_habit_begin_pause);
        go = (ImageButton)findViewById(R.id.programme_habit_begin_go);
        stop = (ImageButton)findViewById(R.id.programme_habit_begin_stop);

        data=(Programme_habit_data)getIntent().getSerializableExtra("Programme_habit_data");
        data = DataSupport.where("Programme_timestamp=?",String.valueOf(data.getProgramme_timestamp())).find(Programme_habit_data.class).get(0);
        initialize();
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration=SystemClock.elapsedRealtime()-time.getBase();
                time.stop();
                pause.setVisibility(View.INVISIBLE);
                go.setVisibility(View.VISIBLE);
                stop.setVisibility(View.VISIBLE);

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBase(SystemClock.elapsedRealtime()-duration);
                time.start();
                pause.setVisibility(View.VISIBLE);
                go.setVisibility(View.GONE);
                stop.setVisibility(View.GONE);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration=SystemClock.elapsedRealtime()-time.getBase();
                if (duration>=60*1000){
                    data.setProgramme_number(data.getProgramme_number()+1);
                    data.setProgramme_total(data.getProgramme_total()+duration);
                    data.save();
                }
                finish();
            }
        });
        time.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime()-time.getBase()>=60*60*1000){
                    duration=Long.valueOf(60*60*1000);
                    time.stop();
                    pause.setVisibility(View.INVISIBLE);
                    go.setVisibility(View.VISIBLE);
                    stop.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    void initialize(){
        time.setBase(SystemClock.elapsedRealtime());
        time.start();
        pause.setVisibility(View.VISIBLE);
        go.setVisibility(View.GONE);
        stop.setVisibility(View.GONE);

    }
}
