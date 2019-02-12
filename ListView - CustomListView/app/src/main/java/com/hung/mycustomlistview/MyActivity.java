package com.hung.mycustomlistview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HUNG on 2/5/2017.
 */

public class MyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<Person> listPersons;

    public MyActivity() {
        listPersons = new ArrayList<>();
        listPersons.add(new Person(R.drawable.ic_angry, "Hùng Coder"));
        listPersons.add(new Person(R.drawable.ic_cry, "Tuyết Mai"));
        listPersons.add(new Person(R.drawable.ic_dead, "Hoài Nam"));
        listPersons.add(new Person(R.drawable.ic_embarrass, "Bình Minh"));
        listPersons.add(new Person(R.drawable.ic_happy, "Đức Trí"));
        listPersons.add(new Person(R.drawable.ic_joy, "Anh Tuấn"));
        listPersons.add(new Person(R.drawable.ic_love, "Phú Hưởng"));
        listPersons.add(new Person(R.drawable.ic_sad, "Minh Tiến"));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
    }

    private void initView() {
        MyAdapter myAdapter = new MyAdapter(this, listPersons);
        ListView lvListPerson = (ListView) findViewById(R.id.lv_list_person);
        lvListPerson.setAdapter(myAdapter);
        lvListPerson.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Position: " + position, Toast.LENGTH_SHORT).show();
    }
}
