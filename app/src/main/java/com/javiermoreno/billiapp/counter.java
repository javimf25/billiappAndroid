package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Result;
import java.util.Arrays;
import java.util.HashMap;

public class counter extends Activity {

    private Button MesEntrades,MenysEntrades,Menys10J1,Menys1J1,Mes1J1,Mes10J1,Menys10J2,Menys1J2,Mes1J2,Mes10J2;
    private TextView PromigJ1,PromigJ2,NomJ1,NomJ2,ResultatJ1,ResultatJ2,Entrades;
    private partit p1;
    private int puntsparcialsJ1,puntsparcialsJ2,PuntsperGuanyar,idJ1,idJ2;
    private String tipus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter);
        puntsparcialsJ1=0;
        puntsparcialsJ2=0;
        PuntsperGuanyar=50;
        Bundle b = getIntent().getExtras();
        tipus = b.getString("Tipus");
        idJ1=Integer.valueOf(b.getString("idJ1"));
        idJ2=Integer.valueOf(b.getString("idJ2"));
        p1=new partit(b.getString("J1"),b.getString("J2"));
        Entrades=findViewById(R.id.Entrades);
        NomJ1=findViewById(R.id.NomJugador1);
        NomJ2=findViewById(R.id.NomJugador2);
        NomJ1.setText(b.getString("J1"));
        NomJ2.setText(b.getString("J2"));
        MesEntrades=findViewById(R.id.EntradaMes);
        MenysEntrades=findViewById(R.id.EntradaMenys);
        Menys10J1=findViewById(R.id._10J1);
        Menys1J1=findViewById(R.id._1J1);
        Mes1J1=findViewById(R.id.sumar1J1);
        Mes10J1=findViewById(R.id.sumar10J1);
        Menys10J2=findViewById(R.id._10J2);
        Menys1J2=findViewById(R.id._1J2);
        Mes1J2=findViewById(R.id.sumar1J2);
        Mes10J2=findViewById(R.id.sumar10J2);
        PromigJ1=findViewById(R.id.NPromigJ1);
        PromigJ2=findViewById(R.id.NPromigJ2);
        ResultatJ1=findViewById(R.id.PuntsFinalJ1);
        ResultatJ2=findViewById(R.id.PuntsFinalJ2);

        this.MesEntrades.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String aux="0";
                String ppJ1,ppJ2, pfJ1,pfJ2;
                Double e=Double.parseDouble(Entrades.getText().toString());
                e++;
                Entrades.setText(String.valueOf(e.intValue()));
                double pro1=Double.parseDouble(ResultatJ1.getText().toString())/e;
                double pro2=Double.parseDouble(ResultatJ2.getText().toString())/e;
                PromigJ1.setText(String.valueOf(pro1));
                PromigJ2.setText(String.valueOf(pro2));
                if(puntsparcialsJ1<10)ppJ1=aux+puntsparcialsJ1;
                else ppJ1=String.valueOf(puntsparcialsJ1);
                if(puntsparcialsJ2<10)ppJ2=aux+puntsparcialsJ2;
                else ppJ2=String.valueOf(puntsparcialsJ2);
                if(Integer.parseInt(ResultatJ1.getText().toString())<10)pfJ1=aux+ResultatJ1.getText().toString();
                else pfJ1=ResultatJ1.getText().toString();
                if(Integer.parseInt(ResultatJ1.getText().toString())<10)pfJ2=aux+ResultatJ2.getText().toString();
                else pfJ2=ResultatJ2.getText().toString();
                p1.afegirPuntuacio("ParcialJ1",ppJ1);
                p1.afegirPuntuacio("ParcialJ2",ppJ2);
                p1.afegirPuntuacio("FinalJ1",pfJ1);
                p1.afegirPuntuacio("FinalJ2",pfJ2);
                puntsparcialsJ1=0;
                puntsparcialsJ2=0;

            }
        });
        this.MenysEntrades.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                if(e>0){
                    e--;
                    Entrades.setText(String.valueOf(e.intValue()));
                    double pro1=Double.parseDouble(ResultatJ1.getText().toString())/e;
                    double pro2=Double.parseDouble(ResultatJ2.getText().toString())/e;
                    PromigJ1.setText(String.valueOf(pro1));
                    PromigJ2.setText(String.valueOf(pro2));
                }

            }
        });

        this.Mes1J1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ1++;
                int aux= Integer.parseInt(ResultatJ1.getText().toString());
                aux++;
                ResultatJ1.setText(String.valueOf(aux));
                double pro1=Double.parseDouble(ResultatJ1.getText().toString())/e;
                PromigJ1.setText(String.valueOf(pro1));
                if(Integer.valueOf(ResultatJ1.getText().toString())>49){
                    Intent i = new Intent(counter.this, acta.class);
                    i.putExtra("parcialJ1",p1.getParcialJ1());
                    i.putExtra("parcialJ2",p1.getParcialJ2());
                    i.putExtra("finalJ1",p1.getFinalJ1());
                    i.putExtra("finalJ2",p1.getFinalJ2());
                    i.putExtra("finalactaJ1",ResultatJ1.getText().toString());
                    i.putExtra("finalactaJ2",ResultatJ2.getText().toString());
                    i.putExtra("finalactaJ1",ResultatJ1.getText().toString());
                    i.putExtra("PromigactaJ1",PromigJ1.getText().toString());
                    i.putExtra("PromigactaJ2",PromigJ2.getText().toString());
                    RegistrarPartida(idJ1,idJ2);
                    counter.this.startActivity(i);

                }
            }
        });

        this.Menys1J1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ1--;
                int aux= Integer.parseInt(ResultatJ1.getText().toString());
                aux--;
                ResultatJ1.setText(String.valueOf(aux));
                double pro1=Double.parseDouble(ResultatJ1.getText().toString())/e;
                PromigJ1.setText(String.valueOf(pro1));
            }
        });

        this.Mes10J1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ1=puntsparcialsJ1+10;
                int aux= Integer.parseInt(ResultatJ1.getText().toString());
                aux=aux+10;
                ResultatJ1.setText(String.valueOf(aux));
                double pro1=Double.parseDouble(ResultatJ1.getText().toString())/e;
                PromigJ1.setText(String.valueOf(pro1));
            }
        });

        this.Menys10J1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ1=puntsparcialsJ1-10;
                int aux= Integer.parseInt(ResultatJ1.getText().toString());
                aux=aux-10;
                ResultatJ1.setText(String.valueOf(aux));
                double pro1=Double.parseDouble(ResultatJ1.getText().toString())/e;
                PromigJ1.setText(String.valueOf(pro1));
            }
        });
        this.Mes1J2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ2=puntsparcialsJ2+1;
                int aux= Integer.parseInt(ResultatJ2.getText().toString());
                aux=aux+1;
                ResultatJ2.setText(String.valueOf(aux));
                double pro2=Double.parseDouble(ResultatJ2.getText().toString())/e;
                PromigJ2.setText(String.valueOf(pro2));
                if(Integer.valueOf(ResultatJ2.getText().toString())>49){
                    Intent i = new Intent(counter.this, acta.class);
                    i.putExtra("parcialJ1",p1.getParcialJ1());
                    i.putExtra("parcialJ2",p1.getParcialJ2());
                    i.putExtra("finalJ1",p1.getFinalJ1());
                    i.putExtra("finalJ2",p1.getFinalJ2());
                    i.putExtra("finalactaJ2",ResultatJ2.getText().toString());
                    i.putExtra("finalactaJ1",ResultatJ1.getText().toString());
                    i.putExtra("promigactaJ1",PromigJ1.getText().toString());
                    i.putExtra("promigactaJ2",PromigJ2.getText().toString());
                    i.putExtra("nomJ1",NomJ1.getText().toString());
                    i.putExtra("nomJ2",NomJ2.getText().toString());
                    RegistrarPartida(idJ1,idJ2);
                    counter.this.startActivity(i);

                }
            }
        });

        this.Menys1J2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ2=puntsparcialsJ2-1;
                int aux= Integer.parseInt(ResultatJ2.getText().toString());
                aux=aux-1;
                ResultatJ2.setText(String.valueOf(aux));
                double pro2=Double.parseDouble(ResultatJ2.getText().toString())/e;
                PromigJ2.setText(String.valueOf(pro2));
            }
        });

        this.Mes10J2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ2=puntsparcialsJ2+10;
                int aux= Integer.parseInt(ResultatJ2.getText().toString());
                aux=aux+10;
                ResultatJ2.setText(String.valueOf(aux));
                double pro2=Double.parseDouble(ResultatJ2.getText().toString())/e;
                PromigJ2.setText(String.valueOf(pro2));
            }
        });

        this.Menys10J2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Double e=Double.parseDouble(Entrades.getText().toString());
                puntsparcialsJ2=puntsparcialsJ2-10;
                int aux= Integer.parseInt(ResultatJ2.getText().toString());
                aux=aux-10;
                ResultatJ2.setText(String.valueOf(aux));
                double pro2=Double.parseDouble(ResultatJ2.getText().toString())/e;
                PromigJ2.setText(String.valueOf(pro2));
            }
        });
        }

        void RegistrarPartida(int id1,int id2){


               if(tipus=="rapida"){

                   p1.setPuntuacioFinalJ1(Integer.valueOf(ResultatJ1.getText().toString()));
                   p1.setPuntuacioFinalJ2(Integer.valueOf(ResultatJ2.getText().toString()));
                   p1.setPromigJ1(Integer.valueOf(PromigJ1.getText().toString()));
                   p1.setPromigJ2(Integer.valueOf(PromigJ2.getText().toString()));
                   p1.setIdJ1(id1);
                   p1.setIdJ2(id2);
               }

            //if everything is fine

            class RegisterMatch extends AsyncTask<Void, Void, String> {

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

                            finish();
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

                    String vectorfinalJ1="",vectorfinalJ2="",vectorparcialJ1="",vectorparcialJ2="";

                    for(int i=0;i<p1.getFinalJ1().size();i++){

                        vectorfinalJ1=vectorfinalJ1+p1.getFinalJ1().get(i);
                        vectorfinalJ2=vectorfinalJ2+p1.getFinalJ2().get(i);
                        vectorparcialJ1=vectorparcialJ1+p1.getParcialJ1().get(i);
                        vectorparcialJ2=vectorparcialJ2+p1.getParcialJ2().get(i);
                    }
                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                   // params.put("password", password);
                    params.put("vectorfinalJ1",vectorfinalJ1);
                    params.put("vectorfinalJ2",vectorfinalJ2);
                    params.put("vectorparcialJ1",vectorparcialJ1);
                    params.put("vectorparcialJ2",vectorparcialJ2);
                    params.put("resultatFinalJ1",ResultatJ1.getText().toString());
                    params.put("resultatFinalJ2",ResultatJ2.getText().toString());
                    params.put("idJ1",String.valueOf(idJ1));
                    params.put("idJ2",String.valueOf(idJ2));
                    params.put("promigJ1",String.valueOf(p1.getPromigJ1()));
                    params.put("promigJ2",String.valueOf(p1.getPromigJ2()));
                    params.put("NomJ1",NomJ1.getText().toString());
                    params.put("NomJ2",NomJ2.getText().toString());
                    //returing the response
                    return requestHandler.sendPostRequest(URLs.URL_SAVEMATCH, params);
                }
            }

            RegisterMatch ul = new RegisterMatch();
            ul.execute();


        }
    }

