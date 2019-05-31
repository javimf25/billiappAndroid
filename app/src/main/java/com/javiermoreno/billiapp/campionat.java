package com.javiermoreno.billiapp;


import android.app.Activity;	import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class campionat extends Activity {

    private Button  AfegirJugador,Començar,Cancelar;
    private EditText Jugadors,DataPartida,NomLliga,Competicio,Modalitat,Arbit,Jornada;
    private TextView MissatgeAfegir;
    private ArrayList<User> Participants;
    private campionatEntity c;
    final Calendar myCalendar = Calendar.getInstance();

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        DataPartida.setText(sdf.format(myCalendar.getTime()));
    }
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.esdeveniment8);
        Participants=new ArrayList<User>();
        AfegirJugador=findViewById(R.id.Afegir);
        Començar=findViewById(R.id.Començarboto);
        Cancelar=findViewById(R.id.Enrere);
        Jugadors=findViewById(R.id.Jugadors);
        DataPartida=findViewById(R.id.DataPartida);
        Competicio=findViewById(R.id.Competicio);
        Modalitat=findViewById(R.id.Modalitat);
        Arbit=findViewById(R.id.Arbit);
        Jornada=findViewById(R.id.Jornada);
        MissatgeAfegir=findViewById(R.id.JugadorAfegit);


        DataPartida.setOnClickListener(new View.OnClickListener() {
            DatePickerDialog.OnDateSetListener  date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {

                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }

            };
            @Override
            public void onClick(View v) {

                new DatePickerDialog(campionat.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        this.AfegirJugador.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AfegirJugadorCompeticio();
            }
        });


        this.Començar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Participants.size()==4 || Participants.size()==8 || Participants.size()==16) {
                    ComençarCompeticio();
                }
                else{
                    Toast.makeText(getApplicationContext(), "la competicio precisa tenir 8 o 16 participants , actualment: "+Participants.size(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.Cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                campionat.this.startActivity(new Intent(campionat.this, menu.class));
            }
        });
    }


    void ComençarCompeticio(){

        final String data=DataPartida.getText().toString();
        final String competicio=Competicio.getText().toString();
        final String modalitat=Modalitat.getText().toString();
        final String arbit=Arbit.getText().toString();
        final String jornada=Jornada.getText().toString();


        class CreateChampionship extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // progressBar = (ProgressBar) findViewById(R.id.progressBar1);
                // progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        String idCampionat=obj.getString("id");
                        int n=0;
                        Intent i=new Intent(campionat.this,counter.class);
                        i.putExtra("tipus","campionat");
                        i.putExtra("idcampionat",idCampionat);
                        i.putExtra("vectorJugadors",Participants);
                        i.putExtra("contador",n);
                        i.putExtra("t","campionat");
                        c=new campionatEntity(Integer.valueOf(idCampionat),Integer.valueOf(jornada),competicio,modalitat,arbit, data);
                        partit p = new partit(Integer.valueOf(idCampionat),arbit,data);
                        i.putExtra("partit",p);
                        i.putExtra("campionat",c);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Campionat no creat correctament", Toast.LENGTH_SHORT).show();
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
                params.put("data",data);
                params.put("arbit",arbit);
                params.put("jornada",jornada);
                params.put("modalitat",modalitat);
                params.put("competicio",competicio);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_CREATECHAMPIONSHIP, params);
            }
        }

        CreateChampionship ul = new CreateChampionship();
        ul.execute();
    }


    void AfegirJugadorCompeticio(){

        final String username=Jugadors.getText().toString();
        Jugadors.setText("");


        class CheckUser extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
               // progressBar = (ProgressBar) findViewById(R.id.progressBar1);
               // progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getString("id"),
                                userJson.getString("username")
                        );
                        Participants.add(user);
                        MissatgeAfegir.setText("Jugador afegit correctament: "+user.getUsername());
                        MissatgeAfegir.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username", Toast.LENGTH_SHORT).show();
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
                params.put("username", username);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_CHECKUSER1, params);
            }
        }

        CheckUser ul = new CheckUser();
        ul.execute();
    }


    }
