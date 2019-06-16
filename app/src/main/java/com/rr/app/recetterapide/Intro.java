package com.rr.app.recetterapide;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class Intro extends AppCompatActivity {

    View intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        intro = findViewById(R.id.intro);
        Animation reveal = new AlphaAnimation(0.0f,0.8f);
        reveal.setDuration(500);
        intro.startAnimation(reveal);
    }

    public void start(View view) {
        new transition(intro).execute();
    }

    private class transition extends AsyncTask<Void,Void,Void>{

        Animation disappear;
        View v;
        transition(View v){
            this.v = v;
        }


        Intent i = new Intent(Intro.this, Main.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        @Override
        protected void onPreExecute(){
            disappear = new AlphaAnimation(0.8f,0.0f);
            disappear.setDuration(500);
            v.startAnimation(disappear);
            v.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result){
            startActivity(i);
        }
    }
}
