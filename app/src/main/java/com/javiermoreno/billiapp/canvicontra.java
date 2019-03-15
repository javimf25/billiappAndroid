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

public class canvicontra extends Activity {
    private Button b1,b2;
    private EditText ed1,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canvicontra);
        b1=findViewById(R.id.confirmarcontra);
        b2=findViewById(R.id.tornar1);
        ed1=findViewById(R.id.ContraAntigua);
        ed2=findViewById(R.id.ContraNova);
        this.b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CanviarContra();
            }
        });
        this.b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                canvicontra.this.startActivity(new Intent(canvicontra.this, opcions.class));
            }
        });
    }

    void CanviarContra(){
        final String passworda=ed1.getText().toString();
        final String passwordn=ed2.getText().toString();
        if (TextUtils.isEmpty(passworda)) {
            ed1.setError("Please enter your old password");
            ed1.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passwordn)) {
            ed2.setError("Please enter your new password");
            ed2.requestFocus();
            return;
        }

        class PasswordChange extends AsyncTask<Void, Void, String> {

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
                params.put("passwordN", passworda);
                params.put("passwordA", passwordn);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_CHANGEPASS, params);
            }
        }

        PasswordChange ul = new PasswordChange();
        ul.execute();
    }

    }


