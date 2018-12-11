package com.example.austinbailey.final_project_austin_bailey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Lodge> mDataset;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, ratingTextView;
        public MyViewHolder(LinearLayout v) {
            super(v);
            titleTextView = v.findViewById(R.id.name);
            ratingTextView = v.findViewById(R.id.rating);
        }
    }

    public MyAdapter(ArrayList<Lodge> myDataset, Context context) {
        mDataset = myDataset; this.context = context;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new viewTextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.titleTextView.setText(mDataset.get(position).name);
        holder.ratingTextView.setText("Rating: " + Double.toString(mDataset.get(position).rating));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewLodgeActivity.class);
                // Conditionally set if this is starting activity from FavoritesActivity (to determine save vs. delete button rendering)
                if (mDataset.get(position).id != -1) {
                    intent.putExtra("isFavorites", true);
                } else {
                    intent.putExtra("isFavorites", false);
                }
                intent.putExtra("lodge", mDataset.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
