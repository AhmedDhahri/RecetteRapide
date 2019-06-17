package com.rr.app.recetterapide.Result_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.rr.app.recetterapide.FoodProvider;
import com.rr.app.recetterapide.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Result extends AppCompatActivity {
    ArrayList<String> ingredients;
    TextView t;
    HashMap<String,List> recep;
    FoodProvider fp;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> GroupData;
    HashMap<String, List<String>> ChildData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        expListView = findViewById(R.id.exListV);
        GroupData = new ArrayList<String>();
        ChildData = new HashMap<String, List<String>>();

        t = findViewById(R.id.t);
        Intent i = getIntent();
        fp = new FoodProvider();
        String text = "";
        ingredients = i.getStringArrayListExtra("inglist");
        try {
            recep = fp.getRecep(getResources());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(String x : recep.keySet()){
            boolean possible = true;
            for(String s : (String[]) recep.get(x).toArray(new String[recep.get(x).size()])){
                if(!ingredients.contains(s))
                    possible = false;
            }
            if(possible) {
                text = "Recettes possibles:";
                GroupData.add(x);
                ChildData.put(x,recep.get(x));
            }

        }
        if(!text.equals(""))
            t.setText(text);
        else
            t.setText("Il n'ya aucune recette.");

        listAdapter = new Adapter(this, GroupData, ChildData);
        expListView.setAdapter(listAdapter);
    }
}
