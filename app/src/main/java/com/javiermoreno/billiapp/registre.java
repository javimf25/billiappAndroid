package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class registre extends Activity {

    private EditText ed1,ed2,ed3;
    Button b1,b2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registre);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        b1= findViewById(R.id.button4);
        b2=findViewById(R.id.button5);
        this.b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            registerUser();
            }
        });
        this.b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registre.this.startActivity(new Intent(registre.this, MainActivity.class));
            }
        });

    }

    private void registerUser() {

        ed1 = findViewById(R.id.username);
        ed2 = findViewById(R.id.pass);
        ed3 = findViewById(R.id.email);

        //first we will do the validations

        if (TextUtils.isEmpty(ed1.getText())) {
            ed1.setError("Please enter username");
            ed1.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(ed3.getText())) {
            ed3.setError("Please enter your email");
            ed3.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(ed3.getText()).matches()) {
            ed3.setError("Enter a valid email");
            ed3.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(ed2.getText())) {
            ed2.setError("Enter a password");
            ed2.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", ed1.getText().toString());
                params.put("email", ed3.getText().toString());
                params.put("password", ed2.getText().toString());

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        JSONObject userJson = obj.getJSONObject("user");
                        User user = new User(
                                userJson.getString("id"),
                                userJson.getString("username"),
                                userJson.getString("email"),
                                userJson.getString("password")
                        );
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        registre.this.startActivity(new Intent(registre.this, menu.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }

}
