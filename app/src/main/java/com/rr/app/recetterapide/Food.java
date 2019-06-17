package com.rr.app.recetterapide;

import org.json.JSONException;
import org.json.JSONObject;

public class Food {

    String ing;
    String _unit;
    int protein;
    int calories;

    public static String[] types = {"Laitier","Pates et grains","Animal","Epices et autres","Legumes"};
    public static String[] _types = {"Laitier","Pates_et_grains","Animal","Epices_et_autres","Legumes"};

    public Food (JSONObject j) throws JSONException {
        ing = j.getString("food");
        _unit = j.getString("unit");
        calories = j.getInt("calories");
        protein = j.getInt("protein");
    }
    Food(){
        ing = "";
        _unit = "";
        protein = 0;
        calories = 0;
    }
}
