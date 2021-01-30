package com.lz2975.Bookrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lz2975.Bill.Bill_budget_recycler_adapter;
import com.lz2975.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bookrack extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private TextView num;
    private TextView sum;
    private ImageButton add;
    private RadioGroup RG;
    private RecyclerView recycler;
    private List<List<Book_data>> datass;
    private List<Book_data> datas = new ArrayList<>();
    private int point=0;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        datas = DataSupport.findAll(Book_data.class);
        num = (TextView)view.findViewById(R.id.bookrack_title_num);
        sum = (TextView)view.findViewById(R.id.bookrack_title_sum);
        add = (ImageButton) view.findViewById(R.id.bookrack_title_add);
        RG = (RadioGroup)view.findViewById(R.id.bookrack_RGroup);
        recycler = (RecyclerView)view.findViewById(R.id.bookrack_recycler);
        RG.setOnCheckedChangeListener(this);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();//获取LayoutManager
                if (manager != null && manager instanceof LinearLayoutManager){
                    //第一个可见的位置
                    point =  ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                    switch (point){
                        case 0:
                            ((RadioButton) getView().findViewById(R.id.bookrack_synthesize)).setChecked(true);
                            break;
                        case 1:
                            ((RadioButton) getView().findViewById(R.id.bookrack_PhilosophyCulture)).setChecked(true);
                            break;
                        case 2:
                            ((RadioButton) getView().findViewById(R.id.bookrack_PhilosophyPoliticsEconomics)).setChecked(true);
                            break;
                        case 3:
                            ((RadioButton) getView().findViewById(R.id.bookrack_ScienceEducation)).setChecked(true);
                            break;
                        case 4:
                            ((RadioButton) getView().findViewById(R.id.bookrack_LanguageCharacter)).setChecked(true);
                            break;
                        case 5:
                            ((RadioButton) getView().findViewById(R.id.bookrack_LiteratureArt)).setChecked(true);
                            break;
                        case 6:
                            ((RadioButton) getView().findViewById(R.id.bookrack_HisoryGeography)).setChecked(true);
                            break;
                        case 7:
                            ((RadioButton) getView().findViewById(R.id.bookrack_MathematicsChemistry)).setChecked(true);
                            break;
                        case 8:
                            ((RadioButton) getView().findViewById(R.id.bookrack_AstronmyEarth)).setChecked(true);
                            break;
                        case 9:
                            ((RadioButton) getView().findViewById(R.id.bookrack_BiologyMedicine)).setChecked(true);
                            break;
                        case 10:
                            ((RadioButton) getView().findViewById(R.id.bookrack_SanitationEnvironment)).setChecked(true);
                            break;
                        case 11:
                            ((RadioButton) getView().findViewById(R.id.bookrack_AgricultureIndustrial)).setChecked(true);
                            break;
                        case 12:
                            ((RadioButton) getView().findViewById(R.id.bookrack_TransportationSpaceflight)).setChecked(true);
                            break;
                    }
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Bookrack_add.class));
            }
        });
        initialize();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.bookrack,container,false);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        initialize();
    }
    void initialize(){
        datass=new ArrayList<>();
        List<Book_data> all = DataSupport.findAll(Book_data.class);
        int l=new Book_data().Book_type().size();
        double sums=0;
        for (int n=0;n<l;n++){
            List<Book_data> datas=new ArrayList<>();
            datass.add(datas);
        }
        for (Book_data data:all){
            datass.get(data.getBook_typeID()).add(data);
            sums+=data.getBook_cheek();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        Bookrack_recycler_adapter adapter = new Bookrack_recycler_adapter(getContext(),datass);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

        num.setText("总数量："+all.size());
        sum.setText("总价值："+String.format("%.2f",sums));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.bookrack_synthesize:
                point=0;
                break;
            case R.id.bookrack_PhilosophyCulture:
                point=1;
                break;
            case R.id.bookrack_PhilosophyPoliticsEconomics:
                point=2;
                break;
            case R.id.bookrack_ScienceEducation:
                point=3;
                break;
            case R.id.bookrack_LanguageCharacter:
                point=4;
                break;
            case R.id.bookrack_LiteratureArt:
                point=5;
                break;
            case R.id.bookrack_HisoryGeography:
                point=6;
                break;
            case R.id.bookrack_MathematicsChemistry:
                point=7;
                break;
            case R.id.bookrack_AstronmyEarth:
                point=8;
                break;
            case R.id.bookrack_BiologyMedicine:
                point=9;
                break;
            case R.id.bookrack_SanitationEnvironment:
                point=10;
                break;
            case R.id.bookrack_AgricultureIndustrial:
                point=11;
                break;
            case R.id.bookrack_TransportationSpaceflight:
                point=12;
                break;
        }
        recycler.scrollToPosition(point);
        ((LinearLayoutManager) recycler.getLayoutManager()).scrollToPositionWithOffset(point, 0);
    }
}