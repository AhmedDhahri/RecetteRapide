package com.rr.app.recetterapide.RV;

import com.rr.app.recetterapide.Calories;
import com.rr.app.recetterapide.Food_list;
import com.rr.app.recetterapide.Main;
import com.rr.app.recetterapide.R;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class mAdapter extends RecyclerView.Adapter<mAdapter.mViewHolder> {

    ArrayList<RV_Item> items;
    RecyclerView mRV;




    public mAdapter(ArrayList<RV_Item> items, RecyclerView mRV){
        this.items = items;
        this.mRV = mRV;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
        mViewHolder vh = new mViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder mViewHolder, int i) {
        RV_Item item = items.get(i);
        mViewHolder.mImageView.setImageResource(item.mImageView);
        mViewHolder.mTextView1.setText(item.mTextView1);
        mViewHolder.mTextView2.setText(item.mTextView2);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{


        ImageView mImageView;
        TextView mTextView1;
        TextView mTextView2;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ImgV);
            mTextView1 = itemView.findViewById(R.id.L1);
            mTextView2 = itemView.findViewById(R.id.L2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mTextView1.getText().equals("Calcule calories")){
                Main.context.startActivity(new Intent(Main.context, Calories.class));
            }
            if(mTextView1.getText().equals("Suggestions")){
                Main.context.startActivity(new Intent(Main.context, Food_list.class));
            }

        }
    }

}

