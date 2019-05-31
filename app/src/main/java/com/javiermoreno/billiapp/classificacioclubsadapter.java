package com.javiermoreno.billiapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.javiermoreno.billiapp.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public  class classificacioclubsadapter extends RecyclerView.Adapter<classificacioclubsadapter.MyViewHolder> {
    static ArrayList<ClubEntity>  mDataset;
    public String ParcialsJ1,ParcialsJ2,FinalsJ1,FinalsJ2;
    private static Context con;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView nom,punts;
        public MyViewHolder(final View itemView, Context context) {
            super(itemView);
            nom=itemView.findViewById(R.id.Equipclassclubs);
            punts=itemView.findViewById(R.id.puntsclassclubs);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public classificacioclubsadapter(ArrayList<ClubEntity> myDataset, Context context) {
        mDataset = myDataset;
        con=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public classificacioclubsadapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fitxaclassificacioclubs, parent, false);
        MyViewHolder vh = new MyViewHolder(v,con);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.nom.setText(mDataset.get(position).getNom());
        holder.punts.setText(String.valueOf(mDataset.get(position).getPunts()));



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}