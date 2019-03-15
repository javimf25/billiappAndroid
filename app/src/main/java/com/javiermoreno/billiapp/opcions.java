package com.javiermoreno.billiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class opcions extends Activity {

    private Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcions);
        b2= findViewById(R.id.canviarcontra);
        b1 =findViewById(R.id.canviarusuari);
        this.b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                opcions.this.startActivity(new Intent(opcions.this, canvicontra.class));
            }
        });
        this.b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                opcions.this.startActivity(new Intent(opcions.this, canviusuari.class));
            }
        });

    }
}
