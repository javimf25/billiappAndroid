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

public  class historialadapter extends RecyclerView.Adapter<historialadapter.MyViewHolder> {
    static ArrayList<partit>  mDataset;
    public String ParcialsJ1,ParcialsJ2,FinalsJ1,FinalsJ2;
    private  Context con;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView NomJugador1,NomJugador2,PromigJ1,PromigJ2,PuntsFinalsJ1,PuntsFinalsJ2,ParcialsJ1,ParcialsJ2,FinalsJ1,FinalsJ2;
        public Button Detalls;
        public MyViewHolder(View itemView, Context context) {
            super(itemView);
            ParcialsJ1=new TextView(context);
            ParcialsJ2=new TextView(context);
            FinalsJ1=new TextView(context);
            FinalsJ2=new TextView(context);
            NomJugador1=itemView.findViewById(R.id.NomJugador1Fitxa);
            NomJugador2=itemView.findViewById(R.id.NomJugador2Fitxa);
            PromigJ1=itemView.findViewById(R.id.PromigJ1Fitxa);
            PromigJ2=itemView.findViewById(R.id.PromigJ2Fitxa);
            PuntsFinalsJ1=itemView.findViewById(R.id.PuntsJ1Fitxa);
            PuntsFinalsJ2=itemView.findViewById(R.id.PuntsJ2Fitxa);
            Detalls=itemView.findViewById(R.id.detalls);
            this.Detalls.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent i = new Intent (v.getContext(), actaHistorial.class);
                    i.putExtra("partit",mDataset.get(getAdapterPosition()));
                    if(mDataset.get(getAdapterPosition()).getIdCompeticio()==0){
                        i.putExtra("tipus","partidarapida");
                    }
                    else {i.putExtra("tipus","campionat");
                        i.putExtra("campionatid",mDataset.get(getAdapterPosition()).getIdCompeticio());
                    }
                    v.getContext().startActivity(i);

                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public historialadapter(ArrayList<partit> myDataset, Context context) {
        mDataset = myDataset;
        con=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public historialadapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.historialfitxe, parent, false);
        MyViewHolder vh = new MyViewHolder(v,con);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.NomJugador1.setText(mDataset.get(position).getJ1Nom());
        holder.NomJugador2.setText(mDataset.get(position).getJ2Nom());
        holder.PromigJ1.setText(String.valueOf(mDataset.get(position).getPromigJ1()));
        holder.PromigJ2.setText(String.valueOf(mDataset.get(position).getPromigJ2()));
        holder.PuntsFinalsJ1.setText(String.valueOf(mDataset.get(position).getPuntuacioFinalJ1()));
        holder.PuntsFinalsJ2.setText(String.valueOf(mDataset.get(position).getPuntuacioFinalJ2()));




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}