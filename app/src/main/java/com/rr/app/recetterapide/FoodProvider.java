package com.rr.app.recetterapide;

import android.content.res.Resources;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class FoodProvider {

    public JSONObject getDB(Resources res, int file) throws IOException, JSONException {

        InputStream is = res.openRawResource(file);
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        return new JSONObject(new String(buffer,"UTF-8"));
    }

    public ArrayList<Food[]> getIngByTypes(Resources res) throws IOException, JSONException {

        ArrayList<Food[]> result = new ArrayList<Food[]>();
        JSONObject db = getDB(res,R.raw.db);
        for(int i = 0;i < Food._types.length;i++){

            JSONArray ja = db.getJSONArray(Food._types[i]);
            Food[] type = new Food[ja.length()];

            for(int k = 0;k < ja.length();k++){
                JSONObject o = ja.getJSONObject(k);
                type[k] = new Food(o);
            }
            result.add(type);
        }
        return result;
    }
    public HashMap<String,List> getRecep(Resources res) throws IOException, JSONException {
        HashMap<String,List> recep = new HashMap();
        JSONObject db = getDB(res,R.raw.recipes);
        Iterator it = db.keys();
        while(it.hasNext()) {
            String element = (String) it.next();
            JSONArray list = db.getJSONArray(element);
            //String[] _list = new String[list.length()];
            List<String> _list = new ArrayList<String>();
            for(int k = 0;k < list.length();k++){
                String s = list.getString(k);
                //_list[k] = s;
                _list.add(s);
            }
            recep.put(element,_list);
        }
        return recep;
    }
}
