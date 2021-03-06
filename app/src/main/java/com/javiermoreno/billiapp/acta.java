package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class acta extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> p1,p2,f1,f2;
    private String r1,r2,pr1,pr2,Tipus;
    private Button tancar;
    private TextView promig1,promig2,resultat1,resultat2,NomJugador1,NomJugador2,data,arbit,modalitat,jornada,competicio,lliga;
    private ArrayList<ArrayList<String>> puntuacions;
    private partit p;
    private int n;
    private ArrayList<User> Participants;
    private campionatEntity c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acta);
        puntuacions = new ArrayList<ArrayList<String>>();
        tancar=findViewById(R.id.tancar);
        Bundle b = getIntent().getExtras();
        p=b.getParcelable("partit");
        Tipus=b.getString("tipus");
        n=b.getInt("n");
        Participants=b.getParcelableArrayList("vectorJugadors");
        NomJugador1=findViewById(R.id.NomJugador1);
        NomJugador2=findViewById(R.id.NomJugador2);
        p1=p.getParcialJ1();
        p2=p.getParcialJ2();
        f1=p.getFinalJ1();
        f2=p.getFinalJ2();
        r1=String.valueOf(p.getPuntuacioFinalJ1());
        r2=String.valueOf(p.getPuntuacioFinalJ2());
        pr1=String.valueOf(p.getPromigJ1());
        pr2=String.valueOf(p.getPromigJ2());
        NomJugador1.setText(p.getJ1Nom());
        NomJugador2.setText(p.getJ2Nom());
        data=findViewById(R.id.data);
        arbit=findViewById(R.id.NomArbitre);
        modalitat=findViewById(R.id.ValorModalitat);
        jornada=findViewById(R.id.ValorJornada);
        competicio=findViewById(R.id.CompeticioValor);
        if(Tipus.equals("campionat")) {
            c=b.getParcelable("campionat");
            data.setText(p.getData());
            arbit.setText(p.getArbit());
            modalitat.setText(c.getModalitat());
            jornada.setText(String.valueOf(c.getJornada()));
            competicio.setText(c.getNomCompeticio());
        }
        else{
            data.setVisibility(View.INVISIBLE);
            arbit.setVisibility(View.INVISIBLE);
            modalitat.setVisibility(View.INVISIBLE);
            jornada.setVisibility(View.INVISIBLE);
            competicio.setVisibility(View.INVISIBLE);
            lliga.setVisibility(View.INVISIBLE);
        }
        promig1=findViewById(R.id.PromigActaJ1);
        promig2=findViewById(R.id.PromigActaJ2);
        resultat1=findViewById(R.id.FinalActaJ1);
        resultat2=findViewById(R.id.FinalActaJ2);
        promig1.setText(pr1);
        promig2.setText(pr2);
        resultat1.setText(r1);
        resultat2.setText(r2);
        puntuacions.add(p1);
        puntuacions.add(p2);
        puntuacions.add(f1);
        puntuacions.add(f2);
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


                if(Tipus.equals("rapida")) {
                    Boolean guest = false;
                    Intent i = new Intent(acta.this, menu.class);
                    i.putExtra("guest", guest);
                    acta.this.startActivity(i);
                }
                else{
                    if(Participants.size()==1){
                        Boolean guest = false;
                        Toast.makeText(getApplicationContext(), "ha guanyat en " + Participants.get(0).getUsername(), Toast.LENGTH_LONG).show();
                        Intent i = new Intent(acta.this, menu.class);
                        i.putExtra("guest", guest);

                        acta.this.startActivity(i);
                    }
                    else {
                        if (n == Participants.size()) n = 0;
                        Toast.makeText(getApplicationContext(), "La seguent partida de " + Participants.get(n).getUsername() + " i " + Participants.get(n + 1).getUsername() + " esta a punt de començar...", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(acta.this, counter.class);
                        i.putExtra("tipus","campionat");
                        i.putExtra("contador", n);
                        i.putExtra("partit", p);
                        i.putExtra("t", Tipus);
                        i.putExtra("vectorJugadors", Participants);
                        i.putExtra("campionat",c);
                        acta.this.startActivity(i);

                    }

                }

            }
        });
    }
}