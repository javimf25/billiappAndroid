package com.javiermoreno.billiapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends Activity {
    private Button b1;
    private Button b2;
    private Button b3;
    private EditText ed1;
    private Boolean guest;
    private EditText ed2;
    private View view1;
    private View view2,view3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button1);
        b3 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.userLogin);
        ed2 = (EditText) findViewById(R.id.passwordlogin);
        view1 = findViewById(R.id.registre);
        view2 = findViewById(R.id.mainmenu);
        view3= findViewById(R.id.activitymain);

        this.b1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /*
                guest=false;
                Intent i = new Intent(MainActivity.this, menu.class);
                i.putExtra("guest",guest);
                startActivity(i);
*/
                userLogin();
            }
        });
        this.b2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, registre.class));
            }
        });
        this.b3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                guest=true;
                Intent i = new Intent(MainActivity.this, menu.class);
                i.putExtra("guest",guest);
                MainActivity.this.startActivity(i);
            }
        });
    }

    private void userLogin() {
        //first getting the values
        final String username = ed1.getText().toString();
        final String password = ed2.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            ed1.setError("Please enter your username");
            ed1.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ed2.setError("Please enter your password");
            ed2.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

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

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getString("id"),
                                userJson.getString("username")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        //starting the profile activity
                        finish();
                        guest=false;
                        Intent i = new Intent(MainActivity.this, menu.class);
                        i.putExtra("guest",guest);
                        startActivity(i);
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
                params.put("username", username);
                params.put("password", password);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }


    public void onButtonShowPopupWindowClick() {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.loading, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view3, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
    }

