package com.app2tap.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TUTORIALS[] = {
            "Button", "TextView", "ImageView"
    };
    private static final String[][] TUTORIALS_DATA = {
            {"Button 1", "Button 2", "Button 3", "Button 4"},
            {"TextView 1", "TextView 2", "TextView 3", "TextView 4", "TextView 5"},
            {"ImageView 1", "ImageView 2"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView elvTutorials = (ExpandableListView) findViewById(R.id.elv_tutorial);
        TutorialAdapter adapter = new TutorialAdapter(this, TUTORIALS, TUTORIALS_DATA);
        elvTutorials.setAdapter(adapter);
        elvTutorials.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView,
                                        View view, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, "" + groupPosition + " - " + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
