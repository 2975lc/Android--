package com.lz2975.Bookrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import com.lz2975.R;

import java.util.List;

public class Bookrack_add extends AppCompatActivity {
    private Button finish;
    private EditText name;
    private EditText author;
    private EditText cheek;
    private GridView grid;
    private Bookrack_add_gridview_adapter adapter;
    private List<Book_type> types=new Book_type().Book_types();
    private int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookrack_add_activity);
        finish = (Button)findViewById(R.id.bookrack_add_finish);
        name = (EditText)findViewById(R.id.bookrack_add_name_edit);
        author = (EditText)findViewById(R.id.bookrack_add_author_edit);
        cheek = (EditText)findViewById(R.id.bookrack_add_cheek_edit);
        grid = (GridView)findViewById(R.id.bookrack_add_gridview);
        types.get(number).setSelect(true);
        adapter = new Bookrack_add_gridview_adapter(this,types);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number=position;
                for(int i=0;i<types.size();i++){
                    types.get(i).setSelect(false);
                }
                types.get(position).setSelect(true);
                adapter.notifyDataSetChanged();
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty()){
                    if (!author.getText().toString().isEmpty()){
                        if (!cheek.getText().toString().isEmpty()){
                            if (Double.parseDouble(cheek.getText().toString())>=0.01){
                                Book_data data=new Book_data();
                                data.setBook_name(name.getText().toString());
                                data.setBook_author(author.getText().toString());
                                data.setBook_cheek(Double.parseDouble(cheek.getText().toString()));
                                data.setBook_typeID(number);
                                data.setBook_timestamp(System.currentTimeMillis());
                                data.save();
                                finish();
                            }
                        }
                    }
                }
            }
        });
    }
}