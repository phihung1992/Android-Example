package com.app2tap.myexample;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PersonAdapter personAdapter;
    private ListView lvPersons;
    private List<String> listPerson;
    private List<String> listResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Demo data
        listPerson = new ArrayList<>();
        listPerson.add("Hung");
        listPerson.add("Mai");
        listPerson.add("Nam");
        listPerson.add("Hoa");
        listPerson.add("Huong");
        listPerson.add("Lan");
        listPerson.add("Minh");
        listPerson.add("Duong");

        listResult = new ArrayList<>();
        listResult.addAll(listPerson);

        // Reference to listView and setAdapter to fill data.
        personAdapter = new PersonAdapter(this, listResult);
        lvPersons = (ListView) findViewById(R.id.lv_persons);
        lvPersons.setAdapter(personAdapter);

        // Reference to SearchView
        SearchView svPersons = (SearchView) findViewById(R.id.sv_persons);
        svPersons.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterResult(s);
                return false;
            }
        });
    }

    private void filterResult(String s) {
        listResult.clear();
        for (int i = 0; i < listPerson.size(); ++i) {
            if (listPerson.get(i).toLowerCase().contains(s.toLowerCase())) {
                listResult.add(listPerson.get(i));
            }
        }
        personAdapter.notifyDataSetChanged();
    }
}
