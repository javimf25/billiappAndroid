package com.javiermoreno.billiapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class counter extends Activity {

    public  Button MesEntrades,MenysEntrades,Menys10J1,Menys1J1,Mes1J1,Mes10J1,Menys10J2,Menys1J2,Mes1J2,Mes10J2,Bt;
    private TextView PromigJ1,PromigJ2,NomJ1,NomJ2,ResultatJ1,ResultatJ2,Entrades;
    private partit p1;
    private int puntsparcialsJ1,puntsparcialsJ2,PuntsperGuanyar,idJ1,idJ2,n;
    private UUID MY_UUID_INSECURE=UUID.fromString("96d1372e-7bdf-11e9-8f9e-2a86e4085a59");
    private String tipus;
    private ArrayList<User> Participants;
    private BluetoothAdapter mBluetoothAdapter;
    private ConnectedThread Cthread;
    private AcceptThread Athread;
    public static final int SEND_CODE = 1;
    public static final int QUIT_CODE = 2;
    private Object missatge;
    private Handler mHandler;
    private campionatEntity c;


    public void debugMsg(final Message msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                byte[] auxo= (byte[]) msg.obj;
                int aux= (int)auxo[0];
                if(aux==48){
                MenysEntrades.performClick();

                }
                else  if(aux==49){
                    MesEntrades.performClick();

                }
                else   if(aux==50){
                    Menys10J1.performClick();
                }
                else  if(aux==51){
                    Menys10J2.performClick();
                }
                else  if(aux==52){
                    Menys1J1.performClick();
                }
                else  if(aux==53){
                    Menys1J2.performClick();
                }
                else  if(aux==56){
                    Mes1J1.performClick();
                }
                else   if(aux==57){
                    Mes1J2.performClick();
                }
                else if(aux==54){
                    Mes10J1.performClick();
                }
                else  if(aux==55){
                    Mes10J2.performClick();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter);
         mHandler =  new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                debugMsg(msg);

            }
        };
        Participants=new ArrayList<>();
        puntsparcialsJ1=0;
        puntsparcialsJ2=0;
        PuntsperGuanyar=50;
        NomJ1=findViewById(R.id.NomJugador1);
        NomJ2=findViewById(R.id.NomJugador2);
        Bundle b = getIntent().getExtras();
        c=b.getParcelable("campionat");
        tipus = b.getString("tipus");
        if(tipus.equals("campionat")){
            p1=b.getParcelable("partit");
            Participants=b.getParcelableArrayList("vectorJugadors");
            n=b.getInt("contador");
            idJ1=Integer.valueOf(Participants.get(n).getId());
            idJ2=Integer.valueOf(Participants.get(n+1).getId());
            p1.setIdJ1(idJ1);
            p1.setIdJ2(idJ2);
            NomJ1.setText(Participants.get(n).getUsername());
            NomJ2.setText(Participants.get(n+1).getUsername());
            p1.setJ1Nom(NomJ1.getText().toString());
            p1.setJ2Nom(NomJ2.getText().toString());
            p1.netejarpuntuacions();
        }
        else{
            idJ1=Integer.valueOf(b.getString("idJ1"));
            idJ2=Integer.valueOf(b.getString("idJ2"));
            NomJ1.setText(b.getString("J1"));
            NomJ2.setText(b.getString("J2"));
            p1=new partit(b.getString("J1"),b.getString("J2"));
            p1.setIdJ1(idJ1);
            p1.setIdJ2(idJ2);
            p1.setJ1Nom(b.getString("J1"));
            p1.setJ2Nom(b.getString("J2"));
        }

        Entrades=findViewById(R.id.Entrades);
        MesEntrades=findViewById(R.id.EntradaMes);
        MenysEntrades=findViewById(R.id.EntradaMenys);
        Menys10J1=findViewById(R.id.menys10J1);
        Menys1J1=findViewById(R.id.menys1J1);
        Mes1J1=findViewById(R.id.sumar1J1);
        Mes10J1=findViewById(R.id.sumar10J1);
        Menys10J2=findViewById(R.id.menys10J2);
        Menys1J2=findViewById(R.id.menys1J2);
        Mes1J2=findViewById(R.id.sumar1J2);
        Mes10J2=findViewById(R.id.sumar10J2);
        PromigJ1=findViewById(R.id.NPromigJ1);
        PromigJ2=findViewById(R.id.NPromigJ2);
        ResultatJ1=findViewById(R.id.PuntsFinalJ1);
        ResultatJ2=findViewById(R.id.PuntsFinalJ2);
        Bt=findViewById(R.id.blue);
        this.Bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pendent de conexio bluetooth", Toast.LENGTH_LONG).show();
                mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
                Athread=new AcceptThread();
                Athread.start();
            }
        });
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
                if(puntsparcialsJ1<10)ppJ1=String.valueOf(aux+puntsparcialsJ1);
                else ppJ1=String.valueOf(puntsparcialsJ1);
                if(puntsparcialsJ2<10)ppJ2=String.valueOf(aux+puntsparcialsJ2);
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
                    if(tipus.equals("campionat")) {
                        Participants.remove(n);
                        n++;
                        Intent i = new Intent(counter.this, acta.class);
                        i.putExtra("n", n);
                        i.putExtra("partit", p1);
                        i.putExtra("tipus", tipus);
                        i.putExtra("vectorJugadors", Participants);
                        RegistrarPartida();
                        i.putExtra("campionat",c);
                        counter.this.startActivity(i);
                    }
                    else{
                        RegistrarPartida();
                        Intent i = new Intent(counter.this, actaHistorial.class);
                        i.putExtra("partit", p1);
                        i.putExtra("tipus", tipus);
                        startActivity(i);
                    }
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
                    if(tipus.equals("campionat")) {
                        Participants.remove(n);
                        n++;
                        Intent i = new Intent(counter.this, acta.class);
                        i.putExtra("n", n);
                        i.putExtra("partit", p1);
                        i.putExtra("tipus", tipus);
                        i.putExtra("vectorJugadors", Participants);
                        RegistrarPartida();
                        i.putExtra("campionat",c);
                        counter.this.startActivity(i);
                    }
                    else{
                        RegistrarPartida();
                        Intent i = new Intent(counter.this, actaHistorial.class);
                        i.putExtra("partit", p1);
                        i.putExtra("tipus", tipus);
                        startActivity(i);
                    }
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

        void RegistrarPartida(){

                   p1.setPuntuacioFinalJ1(Integer.valueOf(ResultatJ1.getText().toString()));
                   p1.setPuntuacioFinalJ2(Integer.valueOf(ResultatJ2.getText().toString()));
                   p1.setPromigJ1(Double.valueOf(PromigJ1.getText().toString()));
                   p1.setPromigJ2(Double.valueOf(PromigJ2.getText().toString()));


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

                    if(s!=null) {
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(s);

                            //if no error in response
                            if (!obj.getBoolean("error")) {


                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                    params.put("tipus",tipus);
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
                    if(tipus.equals("campionat")){

                        params.put("arbit",p1.getArbit());
                        params.put("data", p1.getData());
                        params.put("idCompeticio",String.valueOf(p1.getIdCompeticio()));


                    }
                    //returing the response
                    if(tipus.equals("campionat")) return requestHandler.sendPostRequest(URLs.URL_SAVEMATCHCHAMP, params);
                    else  return requestHandler.sendPostRequest(URLs.URL_SAVEMATCH, params);
                }
            }

            RegisterMatch ul = new RegisterMatch();
            ul.execute();


        }

    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("mando", MY_UUID_INSECURE);
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    Cthread=new ConnectedThread(socket);
                    Cthread.start();

                    try {
                        mmServerSocket.close();
                    } catch (IOException e) { }
                    break;
                }
            }
        }

        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;


        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                Message msg= mHandler.obtainMessage(SEND_CODE, bytes, -1, buffer);
                mHandler.handleMessage(msg);
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

}

