package com.adient.sapcontainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GradeActivity extends AppCompatActivity {
    Spinner spinner;
    String[] grades = {"Select One", "Supplier Quality Dashboard", "Operations Dashboard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        spinner = (Spinner) findViewById(R.id.spner_grae);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GradeActivity.this, android.R.layout.simple_list_item_1, grades);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(GradeActivity.this, LoadPage.class);
                intent.putExtra("grade", "" + grades[position]);
                startActivity(intent);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
