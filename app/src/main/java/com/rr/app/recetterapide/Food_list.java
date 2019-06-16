package com.rr.app.recetterapide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.rr.app.recetterapide.Result_activity.Result;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class Food_list extends AppCompatActivity {


    TextView prev;
    TextView pres;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float v) {
                view.setPivotX(v < 0 ? 0 : view.getWidth());
                view.setPivotY(view.getHeight() / 2f);
                float scale = v < 0 ? 1f + v : 1f - v;
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        });

    }


    public static class PlaceholderFragment extends Fragment {
        ListView listview;
        TextView prev;
        TextView pres;
        TextView next;
        Button valider;
        String[] list;
        Food[] array;
        FoodProvider fp;
        static ArrayList<String> ingredients = new ArrayList<String>();

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
            fp = new FoodProvider();
        }

        public static PlaceholderFragment newInstance(int sectionNumber, Context context) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_food_list, container, false);
            prev = rootView.findViewById(R.id.prev);
            pres = rootView.findViewById(R.id.pres);
            next = rootView.findViewById(R.id.next);
            valider = rootView.findViewById(R.id.valider);
            listview = rootView.findViewById(R.id.food);
            listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            final int position = getArguments().getInt(ARG_SECTION_NUMBER);
            String[] f = Food.types;


            if((position < 5) && (position > 1)){
                prev.setText("< "+f[position-2]);
                pres.setText(f[position-1]);
                next.setText(f[position]+" >");
            }else if(position == 1){
                prev.setText("");
                pres.setText(f[position-1]);
                next.setText(f[position]+" >");
            }else if(position == 5){
                prev.setText("< "+f[position-2]);
                pres.setText(f[position-1]);
                next.setText("");
            }

            try {
                array = fp.getIngByTypes(getResources()).get(position-1);
                list = new String[array.length];
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(int i = 0;i < array.length;i++)
                list[i] = array[i].ing;

            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.list_type,list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String item = adapter.getItem(i);
                    if(ingredients.contains(item))
                        ingredients.remove(item);
                    else
                        ingredients.add(item);
                }
            });
            valider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), Result.class).putStringArrayListExtra("inglist",ingredients);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        Context context;
        public SectionsPagerAdapter(FragmentManager fm,Context c) {
            super(fm);
            context = c;
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1,context);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
