package com.lz2975.Bookrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.lz2975.Bill.Bill_record_data;
import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Bookrack_edit extends AppCompatActivity {
    private Book_data data;
    private EditText name;
    private EditText author;
    private EditText cheek;
    private Bookrack_add_gridview_adapter adapter;
    private GridView grid;
    private List<Book_type> types=new Book_type().Book_types();
    private int number=0;
    private Button edit;
    private Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookrack_edit_activity);
        name = (EditText)findViewById(R.id.bookrack_edit_name_edit);
        author = (EditText)findViewById(R.id.bookrack_edit_author_edit);
        cheek = (EditText)findViewById(R.id.bookrack_edit_cheek_edit);
        grid = (GridView)findViewById(R.id.bill_record_edit_gridview);
        edit = (Button)findViewById(R.id.bookrack_edit_edit);
        delete = (Button)findViewById(R.id.bookrack_edit_delete);
        Intent intent = this.getIntent();
        data=(Book_data)intent.getSerializableExtra("Book_data");
        data = DataSupport.where("Book_timestamp=?",String.valueOf(data.getBook_timestamp())).find(Book_data.class).get(0);
        number=data.getBook_typeID();
        initialize();
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
                types.get(number).setSelect(true);
                adapter.notifyDataSetChanged();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty()){
                    if (!author.getText().toString().isEmpty()){
                        if (!cheek.getText().toString().isEmpty()){
                            if (Double.parseDouble(cheek.getText().toString())>=0.01){
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
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.delete();
                finish();
            }
        });
    }
    void initialize(){
        name.setText(data.getBook_name());
        name.setHint(data.getBook_name());
        author.setText(data.getBook_author());
        author.setHint(data.getBook_author());
        cheek.setText(String.valueOf(data.getBook_cheek()));
        cheek.setHint(String.valueOf(data.getBook_cheek()));
    }
}