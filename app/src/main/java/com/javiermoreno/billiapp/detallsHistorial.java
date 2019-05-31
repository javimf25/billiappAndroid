package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;

public class detallsHistorial extends Activity {

    private TextView tanpercent,promig,partides,equip;
    private User u;
    private Button tancar;
    private Bundle b;
    private String id,tipus,idclub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallshistorial);
        b=getIntent().getExtras();
        id=b.getString("id");
        tipus=b.getString("tipus");
        idclub=b.getString("idclub");
        equip=findViewById(R.id.EquipDetalls);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w=dm.widthPixels;
        int h=dm.heightPixels;
        getWindow().setLayout((int)(w*0.8),(int)(h*0.6));
        tanpercent=findViewById(R.id.tanpercentdetalls);
        promig=findViewById(R.id.promigvalordetalls);
        partides=findViewById(R.id.partidesjugadesdetalls);
        u=SharedPrefManager.getInstance(getApplicationContext()).getUser();
        obtenirDetalls();
        tancar=findViewById(R.id.tancardetallshistorial);
        this.tancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               finish();
            }
        });
    }

private void obtenirDetalls(){



    //if everything is fine

    class ObtenirDetails extends AsyncTask<Void, Void, String> {

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
                String primera,segona,tercera,quarta;
                JSONObject obj1=obj.getJSONObject("uno");
                JSONObject obj2=obj.getJSONObject("dos");
                JSONObject obj3=obj.getJSONObject("tres");
                JSONObject obj4=obj.getJSONObject("cuatro");
                JSONObject obj5=obj.getJSONObject("cinco");

                //if no error in response
                if (!obj.getBoolean("error")) {
                    detallsJugador aux= new detallsJugador(obj1.getInt("total1"),obj2.getDouble("total2"),obj3.getInt("promig1"),obj4.getInt("promig2"));
                    aux.setTanpercent(((double)aux.getPartidesguanyades())/((double)aux.getPartidesperdudes()));
                    partides.setText(String.valueOf(aux.getPartides()));
                    tanpercent.setText(String.valueOf( new DecimalFormat("#.##").format(aux.getTanpercent()*100))+"%");
                    promig.setText(String.valueOf(aux.getPromig()));
                    equip.setText(obj5.getString("Nom"));

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
            if(tipus.equals("classificacio")) {
                params.put("id",id);
            params.put("idclub",idclub);
            }
            else{

                params.put("id",u.getId());
                params.put("idclub",idclub);
            }

            //returing the response
            return requestHandler.sendPostRequest(URLs.URL_DETALLSHISTORIAL, params);
        }
    }

    ObtenirDetails ul = new ObtenirDetails();
    ul.execute();


}



}


