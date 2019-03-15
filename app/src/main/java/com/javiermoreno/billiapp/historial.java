package com.javiermoreno.billiapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class historial extends Activity {
    private User u;
    private partit p;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificacio);
        p=new partit();
        u=SharedPrefManager.getInstance(getApplicationContext()).getUser();
        String id=u.getId();
        ObtenirPartides(id);
        /*
        recyclerView = (RecyclerView) findViewById(R.id.punts);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        // mAdapter = new MyAdapter(puntuacions);
        recyclerView.setAdapter(mAdapter);
        */
    }

    void ObtenirPartides(String id){



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
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        int a=9;
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
