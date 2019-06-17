package com.rr.app.recetterapide;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rr.app.recetterapide.RV.RV_Item;
import com.rr.app.recetterapide.RV.mAdapter;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    ArrayList<RV_Item> Item_list;
    RecyclerView mRV;
    RecyclerView.Adapter mRVadapter;
    RecyclerView.LayoutManager mRVlmngr;

    LinearLayout foreground;
    Button help;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        foreground = findViewById(R.id.foreground);
        Animation reveal = new AlphaAnimation(0.0f,0.8f);
        reveal.setDuration(500);
        foreground.startAnimation(reveal);

        Item_list = new ArrayList<>();
        Item_list.add(new RV_Item(R.drawable.logo, "Suggestions", "Description"));
        Item_list.add(new RV_Item(R.drawable.rec, "Recettes", "Description"));
        Item_list.add(new RV_Item(R.drawable.cal, "Calcule calories", "Description"));
        Item_list.add(new RV_Item(R.drawable.cons, "Conseils", "Description"));


        mRV = findViewById(R.id.RView);
        mRV.setHasFixedSize(true);
        mRV.setVisibility(View.INVISIBLE);

        mRVlmngr = new LinearLayoutManager(this);
        mRVadapter = new mAdapter(Item_list,mRV);

        mRV.setLayoutManager(mRVlmngr);
        mRV.setAdapter(mRVadapter);

        new RvAnim(mRV).execute();

        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this,Calories.class));
            }
        });

    }

    private void Anim(RecyclerView mRV) {
        LayoutAnimationController LAC = AnimationUtils.loadLayoutAnimation(mRV.getContext(),R.anim.layout_anim);
        mRV.setLayoutAnimation(LAC);
        mRV.getAdapter().notifyDataSetChanged();
        mRV.scheduleLayoutAnimation();
    }

    public void Evaluer(View view) {
        startActivity(new Intent(this, Evaluer.class));
    }

    public void Cntct(View view) {
        startActivity(new Intent(this, ContactezNous.class));
    }

    private class RvAnim extends AsyncTask<Void,Void,Void>{
        RecyclerView mRV;
        RvAnim(RecyclerView mRV){
            this.mRV = mRV;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mRV.setVisibility(View.VISIBLE);
            Anim(mRV);
        }
    }
}
