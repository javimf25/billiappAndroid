package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public  class historial extends Activity {
    private User u;
    private partit p;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<JSONObject> puntuacions;
    private Button filtrar,detalls;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificacio);
        detalls=findViewById(R.id.detallshistorial);
        p=new partit();
        puntuacions=new ArrayList<JSONObject>();
        u=SharedPrefManager.getInstance(getApplicationContext()).getUser();
        String id=u.getId();
        ObtenirPartides();
        recyclerView = (RecyclerView) findViewById(R.id.historiapartides);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        this.detalls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(historial.this,detallsHistorial.class);
                i.putExtra("tipus","historial");
                startActivity(i);

            }
        });

    }

    void ObtenirPartides(){



        //if everything is fine

        class getMatches extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                    /*
                    progressBar = (ProgressBar) findViewById(R.id.progressBar1);
                    progressBar.setVisibility(View.VISIBLE);
                    */
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                    progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        ArrayList<partit> aux= new ArrayList<>();
                        JSONArray partits=obj.getJSONArray("user");
                        for (int i = 0; i < partits.length(); ++i) {
                            JSONObject rec = partits.getJSONObject(i);
                            partit p =new partit();
                            p.setJ1Nom(rec.getString("nomjugador1"));
                            p.setJ2Nom(rec.getString("nomjugador2"));
                            p.setPuntuacioFinalJ1(rec.getInt("PuntuacioFinalJ1"));
                            p.setPuntuacioFinalJ2(rec.getInt("PuntuacioFinalJ2"));
                            p.setFinalJ1(rec.getString("PuntsJugadorFinal1"));
                            p.setFinalJ2(rec.getString("PuntsJugadorFinal2"));
                            p.setParcialJ1(rec.getString("parcialsJ1"));
                            p.setParcialJ2(rec.getString("parcialsJ2"));
                            p.setPromigJ1(rec.getDouble("PromigJ1"));
                            p.setPromigJ2(rec.getDouble("PromigJ2"));
                            p.setData(rec.getString("Data"));
                            p.setCompeticio(rec.getInt("Competicio"));
                            aux.add(p);
                        }

                        mAdapter = new historialadapter(aux,historial.this);
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();
                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                // params.put("password", password);
                //returing the response
                params.put("id",u.getId());
                return requestHandler.sendPostRequest(URLs.URL_GETMATCHESUSER, params);
            }
        }

        getMatches ul = new getMatches();
        ul.execute();


    }
}
