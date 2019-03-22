package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class actaHistorial extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> p1,p2,f1,f2;
    private String r1,r2,pr1,pr2;
    private Button tancar;
    private TextView promig1,promig2,resultat1,resultat2,NomJugador1,NomJugador2;
    private ArrayList<ArrayList<String>> puntuacions;
    private ArrayList<String> parcialsJ1,parcialsJ2,finalsJ1,finalsJ2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acta);
        NomJugador1=findViewById(R.id.NomJugador1);
        NomJugador2=findViewById(R.id.NomJugador2);
        Bundle b = getIntent().getExtras();
        NomJugador1.setText(b.getString("nomJ1"));
        NomJugador2.setText(b.getString("nomJ2"));
        parcialsJ1=new ArrayList<String>();
        parcialsJ2=new ArrayList<String>();
        finalsJ1=new ArrayList<String>();
        finalsJ2=new ArrayList<String>();
        String vectorparcials1,vectorparcials2,vectorfinals1,vectorfinals2;
        vectorparcials1=b.getString("parcialJ1");
        vectorparcials2=b.getString("parcialJ2");
        vectorfinals1=b.getString("finalJ1");
        vectorfinals2=b.getString("finalJ2");
        int cont=0;
        for(int i=0;i<vectorparcials1.length()/2;i++){
            parcialsJ1.add(Character.toString(vectorparcials1.charAt(cont))+Character.toString(vectorparcials1.charAt(cont+1)));
            parcialsJ2.add(Character.toString(vectorparcials2.charAt(cont))+Character.toString(vectorparcials2.charAt(cont+1)));
            cont++;
            cont++;
        }
        cont=0;
        for(int i=0;i<vectorfinals1.length()/2;i++){

            finalsJ1.add(Character.toString(vectorfinals1.charAt(cont))+Character.toString(vectorfinals1.charAt(cont+1)));
            finalsJ2.add(Character.toString(vectorfinals2.charAt(cont))+Character.toString(vectorfinals2.charAt(cont+1)));
            cont++;
            cont++;
        }
        puntuacions = new ArrayList<ArrayList<String>>();
        tancar=findViewById(R.id.tancar);
        r1=b.getString("finalactaJ1");
        r2=b.getString("finalactaJ2");
        pr1=b.getString("promigactaJ1");
        pr2=b.getString("promigactaJ2");
        promig1=findViewById(R.id.PromigActaJ1);
        promig2=findViewById(R.id.PromigActaJ2);
        resultat1=findViewById(R.id.FinalActaJ1);
        resultat2=findViewById(R.id.FinalActaJ2);
        promig1.setText(pr1);
        promig2.setText(pr2);
        resultat1.setText(r1);
        resultat2.setText(r2);
        puntuacions.add(parcialsJ1);
        puntuacions.add(parcialsJ2);
        puntuacions.add(finalsJ1);
        puntuacions.add(finalsJ2);
        recyclerView = (RecyclerView) findViewById(R.id.punts);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(puntuacions);
        recyclerView.setAdapter(mAdapter);

        this.tancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent i = new Intent(actaHistorial.this, historial.class);
                actaHistorial.this.startActivity(i);

            }
        });
    }


}