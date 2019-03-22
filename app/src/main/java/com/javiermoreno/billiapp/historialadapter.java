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
    private ArrayList<JSONObject>  mDataset;
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
                    i.putExtra("nomJ1",NomJugador1.getText().toString());
                    i.putExtra("nomJ2",NomJugador2.getText().toString());
                    i.putExtra("parcialJ1",ParcialsJ1.getText().toString());
                    i.putExtra("parcialJ2",ParcialsJ2.getText().toString());
                    i.putExtra("finalJ1",FinalsJ1.getText().toString());
                    i.putExtra("finalJ2",FinalsJ2.getText().toString());
                    i.putExtra("finalactaJ2",PuntsFinalsJ1.getText().toString());
                    i.putExtra("finalactaJ1",PuntsFinalsJ2.getText().toString());
                    i.putExtra("promigactaJ1",PromigJ1.getText().toString());
                    i.putExtra("promigactaJ2",PromigJ2.getText().toString());
                    v.getContext().startActivity(i);

                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public historialadapter(ArrayList<JSONObject> myDataset, Context context) {
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
        String n1,n2,p1,p2,f1,f2,Par1,Par2,Fi1,Fi2;


        try {
            p1=mDataset.get(position).getString("PromigJ1");
            p2=mDataset.get(position).getString("PromigJ2");
            f1=mDataset.get(position).getString("PuntuacioFinalJ1");
            f2=mDataset.get(position).getString("PuntuacioFinalJ2");
            n1=mDataset.get(position).getString("nomjugador1");
            n2=mDataset.get(position).getString("nomjugador2");
            Par1=mDataset.get(position).getString("parcialsJ1");
            Par2=mDataset.get(position).getString("parcialsJ2");
            Fi1=mDataset.get(position).getString("PuntsJugadorFinal1");
            Fi2=mDataset.get(position).getString("PuntsJugadorFinal2");
            holder.NomJugador1.setText(n1);
            holder.NomJugador2.setText(n2);
            holder.PuntsFinalsJ1.setText(f1);
            holder.PuntsFinalsJ2.setText(f2);
            holder.PromigJ1.setText(p1);
            holder.PromigJ2.setText(p2);
            holder.ParcialsJ1.setText(Par1);
            holder.ParcialsJ2.setText(Par2);
            holder.FinalsJ1.setText(Fi1);
            holder.FinalsJ2.setText(Fi2);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}