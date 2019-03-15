package com.javiermoreno.billiapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.javiermoreno.billiapp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<ArrayList<String>>  mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView jugades,p1,p2,f1,f2;
        public MyViewHolder(View itemView) {
            super(itemView);
            jugades=itemView.findViewById(R.id.numeroentrades);
            p1=itemView.findViewById(R.id.parcialJ1);
            p2=itemView.findViewById(R.id.parcialJ2);
            f1=itemView.findViewById(R.id.finalJ1);
            f2=itemView.findViewById(R.id.finalJ2);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<ArrayList<String>> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclepunts, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    String p1,p2,f1,f2;
    int jugada;
    jugada=position+1;
    p1=mDataset.get(0).get(position);
    p2=mDataset.get(1).get(position);
    f1=mDataset.get(2).get(position);
    f2=mDataset.get(3).get(position);

    holder.p1.setText(p1);
    holder.p2.setText(p2);
    holder.f1.setText(f1);
    holder.f2.setText(f2);
    holder.jugades.setText(String.valueOf(jugada));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.get(0).size();
    }
}