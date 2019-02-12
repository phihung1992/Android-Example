package com.hung.demosqlite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HUNG on 24/5/2017.
 */

public class DemoActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private DBManager dbManager;
    private ArrayList<Student> listStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initData();
    }

    private void initData() {
        dbManager = new DBManager(this);
//        if (dbManager.insert(dbManager.TABLE_STUDENT, new String[]{"name", "age", "gender"}, new String[]{"An", "19", "Male"})) {
//            Toast.makeText(this, "Insert Success!", Toast.LENGTH_SHORT).show();
//        }
//        if(dbManager.delete(dbManager.TABLE_STUDENT, "name = ? OR gender = ?", new String[]{"An", "Male"})){
//            Toast.makeText(this, "Delete Succcessfully!", Toast.LENGTH_SHORT).show();
//        }
        if (dbManager.update(dbManager.TABLE_STUDENT, new String[]{"age", "gender"},
                new String[]{"40", "Male"}, "name = ?", new String[]{"Mai"})) {
            Toast.makeText(this, "Update Successfully!", Toast.LENGTH_SHORT).show();
        }

        listStudent = dbManager.getListStudent();
        for (int i = 0; i < listStudent.size(); ++i) {
            Log.d(TAG, listStudent.get(i).toString());
        }
    }
}
