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

public  class classificacioadapter extends RecyclerView.Adapter<classificacioadapter.MyViewHolder> {
    static ArrayList<User>  mDataset;
    public String ParcialsJ1,ParcialsJ2,FinalsJ1,FinalsJ2;
    private static Context con;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView nom,punts,id,nomequip;
        public MyViewHolder(final View itemView, Context context) {
            super(itemView);
            nom=itemView.findViewById(R.id.nomjugadorclassificacio);
            punts=itemView.findViewById(R.id.puntsclassificacio);
            id=itemView.findViewById(R.id.idclassificacio);
            nomequip=itemView.findViewById(R.id.nomequip);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent i=new Intent(con,detallsHistorial.class);
                    i.putExtra("tipus","classificacio");
                    i.putExtra("id",id.getText());
                    i.putExtra("idclub",nomequip.getText().toString());
                    v.getContext().startActivity(i);

                }
            });


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public classificacioadapter(ArrayList<User> myDataset, Context context) {
        mDataset = myDataset;
        con=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public classificacioadapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.classificaciofitxe, parent, false);
        MyViewHolder vh = new MyViewHolder(v,con);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.nom.setText(mDataset.get(position).getUsername());
        holder.punts.setText(String.valueOf(mDataset.get(position).getPunts()));
        holder.id.setText(mDataset.get(position).getId());
        holder.nomequip.setText(String.valueOf(mDataset.get(position).getIdclub()));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}