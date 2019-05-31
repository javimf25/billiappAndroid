package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class menu extends Activity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        Bundle b = getIntent().getExtras();
       final boolean Guest = b.getBoolean("guest");

        this.b1 = findViewById(R.id.partidarapida);
        this.b2 = findViewById(R.id.campionat);
        this.b3 = findViewById(R.id.historial);
        this.b4 = findViewById(R.id.opcions);
        this.b5=findViewById(R.id.clasificacio);
        this.b6 = findViewById(R.id.logout);
        if (Guest == true) {

            b2.setVisibility(View.INVISIBLE);
            b3.setVisibility(View.INVISIBLE);
            b4.setVisibility(View.INVISIBLE);
            b2.setText("Tornar al login");
            this.b1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {

                    Intent i = new Intent(menu.this, partidaRapida.class);
                    i.putExtra("guest",Guest);
                    menu.this.startActivity(i);
                }
            });
            this.b6.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    SharedPrefManager.getInstance(menu.this).logout();
                }
            });

        } else {
            this.b1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(menu.this, partidaRapida.class);
                    i.putExtra("guest",Guest);
                    menu.this.startActivity(i);
                    finish();
                }
            });
            this.b2.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    menu.this.startActivity(new Intent(menu.this, campionat.class));
                }
            });
            this.b3.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    menu.this.startActivity(new Intent(menu.this, historial.class));
                }
            });
            this.b4.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    menu.this.startActivity(new Intent(menu.this, opcions.class));
                }
            });
            this.b5.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    menu.this.startActivity(new Intent(menu.this, classificacio.class));
                }
            });
            this.b6.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(menu.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("EXIT", true);
                    startActivity(i);
                    finish();
                }
            });

        }
    }
}
