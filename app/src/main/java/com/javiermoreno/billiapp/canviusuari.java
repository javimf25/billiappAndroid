package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class canviusuari extends Activity {

    private Button b1,b2;
    private EditText ed1,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canviusuari);
        b1=findViewById(R.id.confirmarusuari);
        b2=findViewById(R.id.tornar2);
        ed1=findViewById(R.id.Usuariantic);
        ed2=findViewById(R.id.Usuarinou);
        this.b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanviarUsuari();
            }
        });
        this.b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               canviusuari.this.startActivity(new Intent(canviusuari.this, opcions.class));
            }
        });
    }

    void CanviarUsuari(){

        final String usernamea=ed1.getText().toString();
        final String usernamen=ed2.getText().toString();
        if (TextUtils.isEmpty(usernamea)) {
            ed1.setError("Please enter your old username");
            ed1.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(usernamen)) {
            ed2.setError("Please enter your new username");
            ed2.requestFocus();
            return;
        }

        class UserChange extends AsyncTask<Void, Void, String> {

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

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("usernameN", usernamea);
                params.put("usernameA", usernamen);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_CHANGEUSER, params);
            }
        }

        UserChange ul = new UserChange();
        ul.execute();
    }

}

