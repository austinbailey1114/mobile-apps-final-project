package com.example.austinbailey.final_project_austin_bailey;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Lodge> mDataset;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleTextView, ratingTextView;
        public MyViewHolder(LinearLayout v) {
            super(v);
            titleTextView = v.findViewById(R.id.name);
            ratingTextView = v.findViewById(R.id.rating);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Lodge> myDataset, Context context) {
        mDataset = myDataset; this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new viewTextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        v.setOnClickListener(new MyOnClickListener(this.context));
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.titleTextView.setText(mDataset.get(position).name);
        holder.ratingTextView.setText(Double.toString(mDataset.get(position).rating));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
