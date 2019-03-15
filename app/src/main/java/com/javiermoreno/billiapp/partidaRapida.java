package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class partidaRapida extends Activity {

        private Button b1,b2;
        private EditText e1,e2;
        private TextView tx1;
        private String username1,username2;
        private Intent i;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermigpartida);
        Bundle b = getIntent().getExtras();
        final boolean Guest = b.getBoolean("guest");
        b1=findViewById(R.id.validarusuaris);
        b2=findViewById(R.id.començarpartida);
        e1=findViewById(R.id.contrincant1);
        e2=findViewById(R.id.contrincant2);
        tx1=findViewById(R.id.usuaricorrecte);

        if(Guest==false) {
            e1.setVisibility(View.INVISIBLE);
            e2.setHint("Contrincant");
        }

            this.b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    userCheck(Guest);


                }
            });

        this.b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Tipus=new String();
                Tipus="rapida";
                i.putExtra("Tipus",Tipus);
                i.putExtra("J1",SharedPrefManager.getInstance(getApplicationContext()).getUser().getUsername());
                i.putExtra("J2",e2.getText().toString());
                startActivity(i);


            }
        });


    }

    private void userCheck(final Boolean guest) {
        //first getting the values
        if(guest==false){
            username1= SharedPrefManager.getInstance(getApplicationContext()).getUser().getUsername();
            username2=e2.getText().toString();
        }
        else {
             username1 = e1.getText().toString();
             username2 = e2.getText().toString();
        }


        class UserCheck extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar1);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {

                       i = new Intent(partidaRapida.this, counter.class);
                        i.putExtra("idJ1",obj.getString("user"));
                        i.putExtra("idJ2",obj.getString("user2"));
                        tx1.setVisibility(View.VISIBLE);
                        if(guest==true){
                            tx1.setText("Usuaris correctes!");
                        }
                        b2.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
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
                params.put("username1", username1);
                params.put("username2", username2);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_CHECKUSER, params);
            }
        }

        UserCheck ul = new UserCheck();
        ul.execute();
    }

}
