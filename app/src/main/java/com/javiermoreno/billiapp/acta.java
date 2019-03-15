package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class acta extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> p1,p2,f1,f2;
    private String r1,r2,pr1,pr2;
    private Button tancar;
    private TextView promig1,promig2,resultat1,resultat2;
    private ArrayList<ArrayList<String>> puntuacions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acta);
        puntuacions = new ArrayList<ArrayList<String>>();
        tancar=findViewById(R.id.tancar);
        Bundle b = getIntent().getExtras();
        p1=b.getStringArrayList("parcialJ1");
        p2=b.getStringArrayList("parcialJ2");
        f1=b.getStringArrayList("finalJ1");
        f2=b.getStringArrayList("finalJ2");
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

                Boolean guest=false;
                Intent i = new Intent(acta.this, menu.class);
                i.putExtra("guest",guest);
                acta.this.startActivity(i);

            }
        });
    }
}