package com.rr.app.recetterapide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

public class Calories extends AppCompatActivity {
    ListView Sex;
    ArrayAdapter Listadp;
    String[] StringArray = {"Male", "Female"};
    EditText age;
    EditText height;
    EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);

    }

    public void Calculer(View view) {
        int agev = Integer.parseInt(age.getText().toString());
        int heightv = Integer.parseInt(height.getText().toString());
        int weightv = Integer.parseInt(weight.getText().toString());
        double res = 10 * weightv + 6.25 * heightv - 5 *  agev;
    }
}
