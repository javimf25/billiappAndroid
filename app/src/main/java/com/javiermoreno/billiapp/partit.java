package com.javiermoreno.billiapp;


import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.sql.Types.NULL;

public class partit  implements Parcelable {

    private String Arbit,J1Nom,J2Nom,data;
    private int PuntuacioFinalJ1,PuntuacioFinalJ2,idJ1,idJ2,idCompeticio;
    private double PromigJ1,PromigJ2;
    private ArrayList<String> ParcialJ1,ParcialJ2,FinalJ1,FinalJ2;


    protected partit(Parcel in) {
        Arbit = in.readString();
        J1Nom = in.readString();
        J2Nom = in.readString();
        PuntuacioFinalJ1 = in.readInt();
        PuntuacioFinalJ2 = in.readInt();
        PromigJ1 = in.readDouble();
        PromigJ2 = in.readDouble();
        idJ1 = in.readInt();
        idJ2 = in.readInt();
        data= in.readString();
        ParcialJ1 = in.createStringArrayList();
        ParcialJ2 = in.createStringArrayList();
        FinalJ1 = in.createStringArrayList();
        FinalJ2 = in.createStringArrayList();
        idCompeticio= in.readInt();
    }

    public static final Creator<partit> CREATOR = new Creator<partit>() {
        @Override
        public partit createFromParcel(Parcel in) {
            return new partit(in);
        }

        @Override
        public partit[] newArray(int size) {
            return new partit[size];
        }
    };

    public void setCompeticio(int competicio) {
        idCompeticio = competicio;
    }


    public partit() {

        ParcialJ1= new ArrayList <String> ();
        ParcialJ2 = new ArrayList <String>();
        FinalJ1= new ArrayList <String>();
        FinalJ2= new ArrayList <String>();
    }


    public void setData(String data) {
        this.data = data;
    }

    public void setParcialJ1(String parcialJ1) {
        for(int i=0;i<parcialJ1.length()-1;i=i+2){
            if(parcialJ1.substring(i,i+2)!=null) {
                ParcialJ1.add(parcialJ1.substring(i, i + 2));
            }

        }
    }

    public void setParcialJ2(String parcialJ2) {
        for(int i=0;i<parcialJ2.length()-1;i=i+2){
            if(parcialJ2.substring(i,i+2)!=null) {
                ParcialJ2.add(parcialJ2.substring(i, i + 2));
            }
        }
    }

    public void setFinalJ1(String finalJ1) {
        for(int i=0;i<finalJ1.length()-1;i=i+2){
            if(finalJ1.substring(i,i+2)!=null) {
                FinalJ1.add(finalJ1.substring(i, i + 2));
            }
        }
    }

    public void setFinalJ2(String finalJ2) {
        for(int i=0;i<finalJ2.length()-1;i=i+2){
            if(finalJ2.substring(i,i+2)!=null) {
                FinalJ2.add(finalJ2.substring(i, i + 2));
            }
        }
    }

    public void setIdJ1(int idJ1) {
        this.idJ1 = idJ1;
    }

    public void setIdJ2(int idJ2) {
        this.idJ2 = idJ2;
    }

    public int getIdCompeticio() {
        return idCompeticio;
    }


    public void setJ1Nom(String j1Nom) {
        J1Nom = j1Nom;
    }

    public void setJ2Nom(String j2Nom) {
        J2Nom = j2Nom;
    }


    public void setPuntuacioFinalJ1(int puntuacioFinalJ1) {
        PuntuacioFinalJ1 = puntuacioFinalJ1;
    }

    public void setPuntuacioFinalJ2(int puntuacioFinalJ2) {
        PuntuacioFinalJ2 = puntuacioFinalJ2;
    }

    public void setPromigJ1(double promigJ1) {
        PromigJ1 = promigJ1;
    }

    public void setPromigJ2(double promigJ2) {
        PromigJ2 = promigJ2;
    }



    public int getPuntuacioFinalJ1() {
        return PuntuacioFinalJ1;
    }

    public int getPuntuacioFinalJ2() {
        return PuntuacioFinalJ2;
    }

    public double getPromigJ1() {
        return PromigJ1;
    }

    public double getPromigJ2() {
        return PromigJ2;
    }

    public String getArbit() {
        return Arbit;
    }

    public String getJ1Nom() {
        return J1Nom;
    }

    public String getJ2Nom() {
        return J2Nom;
    }




    public ArrayList<String> getParcialJ1() {
        return ParcialJ1;
    }

    public ArrayList<String> getParcialJ2() {
        return ParcialJ2;
    }

    public String getData() {
        return data;
    }

    public ArrayList<String> getFinalJ1() {
        return FinalJ1;
    }

    public ArrayList<String> getFinalJ2() {
        return FinalJ2;
    }

    void netejarpuntuacions(){

        ParcialJ1.clear();
        ParcialJ2.clear();
        FinalJ1.clear();
        FinalJ2.clear();
        PromigJ1=0;
        PromigJ2=0;
        PuntuacioFinalJ1=0;
        PuntuacioFinalJ2=0;

    }
    public partit(String J1Nom, String J2Nom ){

        this.Arbit="";
        this.J1Nom=J1Nom;
        this.J2Nom=J2Nom;
        this.ParcialJ1= new ArrayList <String>();
        this.ParcialJ2= new ArrayList <String>();
        this.FinalJ1= new ArrayList <String>();
        this.FinalJ2= new ArrayList <String>();


    }
    public void afegirPuntuacio (String vector, String punts){

        if(vector=="ParcialJ1"){

            this.ParcialJ1.add(punts);
        }
        else if (vector=="ParcialJ2"){

            this.ParcialJ2.add(punts);
        }
        else if (vector=="FinalJ1"){

            this.FinalJ1.add(punts);
        }
        else if (vector=="FinalJ2"){

            this.FinalJ2.add(punts);
        }

    }

    public partit(int idCompeticio,String arbit,String data) {
        this.Arbit=arbit;
        this.idCompeticio = idCompeticio;
        this.data=data;
        this.ParcialJ1= new ArrayList <String>();
        this.ParcialJ2= new ArrayList <String>();
        this.FinalJ1= new ArrayList <String>();
        this.FinalJ2= new ArrayList <String>();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Arbit);
        dest.writeString(J1Nom);
        dest.writeString(J2Nom);
        dest.writeInt(PuntuacioFinalJ1);
        dest.writeInt(PuntuacioFinalJ2);
        dest.writeDouble(PromigJ1);
        dest.writeDouble(PromigJ2);
        dest.writeInt(idJ1);
        dest.writeInt(idJ2);
        dest.writeString(data);
        dest.writeStringList(ParcialJ1);
        dest.writeStringList(ParcialJ2);
        dest.writeStringList(FinalJ1);
        dest.writeStringList(FinalJ2);
        dest.writeInt(idCompeticio);
    }
}

