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

public class classificacio extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button club,indi;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);
        obtenirjugadors();
        club=findViewById(R.id.classificarperclubs);
        indi=findViewById(R.id.Individual);
        this.club.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                obtenirclubs();
            }
        });
        this.indi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                obtenirjugadors();
            }
        });

    }

    private void obtenirjugadors(){



        //if everything is fine

        class getUsers extends AsyncTask<Void, Void, String> {

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

                        JSONArray usuaris=obj.getJSONArray("usuaris");
                        ArrayList <User>  aux = new ArrayList<>();
                        for(int i=0;i<usuaris.length();i++){

                            JSONObject a= usuaris.getJSONObject(i);
                            User b = new User (a.getString("id"),a.getString("Username"),Integer.valueOf(a.getString("punts")),Integer.valueOf(a.getString("idclub")));
                            aux.add(b);

                        }
                        recyclerView=findViewById(R.id.classificacio);
                        recyclerView.setHasFixedSize(true);
                        // use a linear layout manager
                        layoutManager = new LinearLayoutManager(classificacio.this);
                        recyclerView.setLayoutManager(layoutManager);
                        mAdapter = new classificacioadapter(aux,classificacio.this);
                        recyclerView.setAdapter(mAdapter);



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
                return requestHandler.sendPostRequest(URLs.URL_GETUSERS, params);
            }
        }

        getUsers ul = new getUsers();
        ul.execute();





    }

    private void obtenirclubs(){



        //if everything is fine

        class getclubs extends AsyncTask<Void, Void, String> {

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

                    JSONArray usuaris=obj.getJSONArray("clubs");
                    ArrayList <ClubEntity>  aux = new ArrayList<>();
                    for(int i=0;i<usuaris.length();i++){

                        JSONObject a= usuaris.getJSONObject(i);
                        ClubEntity b = new ClubEntity (a.getString("nom"),Integer.valueOf(a.getString("sumapunts")));
                        aux.add(b);

                    }
                    recyclerView=findViewById(R.id.classificacio);
                    recyclerView.setHasFixedSize(true);
                    // use a linear layout manager
                    layoutManager = new LinearLayoutManager(classificacio.this);
                    recyclerView.setLayoutManager(layoutManager);
                    mAdapter = new classificacioclubsadapter(aux,classificacio.this);
                    recyclerView.setAdapter(mAdapter);



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
                return requestHandler.sendPostRequest(URLs.URL_GETCLUBSRANK, params);
            }
        }

        getclubs ul = new getclubs();
        ul.execute();





    }

}
