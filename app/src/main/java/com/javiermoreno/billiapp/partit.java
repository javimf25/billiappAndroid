package com.javiermoreno.billiapp;


import java.util.ArrayList;

import static java.sql.Types.NULL;

public class partit  {

    private String Competicio,Modalitat,Arbit,J1Nom,J2Nom;
    private int Jornada,PuntuacioFinalJ1,PuntuacioFinalJ2,PromigJ1,PromigJ2,lliga,idJ1,idJ2;
    private ArrayList<String> ParcialJ1,ParcialJ2,FinalJ1,FinalJ2;


    public void setCompeticio(String competicio) {
        Competicio = competicio;
    }

    public int getLliga() {
        return lliga;
    }

    public partit() {

    }

    public void setIdJ1(int idJ1) {
        this.idJ1 = idJ1;
    }

    public void setIdJ2(int idJ2) {
        this.idJ2 = idJ2;
    }

    public partit(int puntuacioFinalJ1, int puntuacioFinalJ2, int promigJ1, int promigJ2, int idJ1, int idJ2, ArrayList<String> parcialJ1, ArrayList<String> parcialJ2, ArrayList<String> finalJ1, ArrayList<String> finalJ2) {
        PuntuacioFinalJ1 = puntuacioFinalJ1;
        PuntuacioFinalJ2 = puntuacioFinalJ2;
        PromigJ1 = promigJ1;
        PromigJ2 = promigJ2;
        this.idJ1 = idJ1;
        this.idJ2 = idJ2;
        ParcialJ1 = parcialJ1;
        ParcialJ2 = parcialJ2;
        FinalJ1 = finalJ1;
        FinalJ2 = finalJ2;
    }

    public void setLliga(int lliga) {
        this.lliga = lliga;
    }

    public void setModalitat(String modalitat) {
        Modalitat = modalitat;
    }

    public void setArbit(String arbit) {
        Arbit = arbit;
    }

    public void setJ1Nom(String j1Nom) {
        J1Nom = j1Nom;
    }

    public void setJ2Nom(String j2Nom) {
        J2Nom = j2Nom;
    }

    public void setJornada(int jornada) {
        Jornada = jornada;
    }

    public void setPuntuacioFinalJ1(int puntuacioFinalJ1) {
        PuntuacioFinalJ1 = puntuacioFinalJ1;
    }

    public void setPuntuacioFinalJ2(int puntuacioFinalJ2) {
        PuntuacioFinalJ2 = puntuacioFinalJ2;
    }

    public void setPromigJ1(int promigJ1) {
        PromigJ1 = promigJ1;
    }

    public void setPromigJ2(int promigJ2) {
        PromigJ2 = promigJ2;
    }

    public String getCompeticio() {
        return Competicio;
    }

    public String getModalitat() {
        return Modalitat;
    }

    public int getPuntuacioFinalJ1() {
        return PuntuacioFinalJ1;
    }

    public int getPuntuacioFinalJ2() {
        return PuntuacioFinalJ2;
    }

    public int getPromigJ1() {
        return PromigJ1;
    }

    public int getPromigJ2() {
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

    public int getJornada() {
        return Jornada;
    }

    public partit(String Competicio, String Modalitat, String Arbit, String J1Nom, String J2Nom, int Jornada) {

        this.Competicio=Competicio;
        this.Modalitat=Modalitat;
        this.Arbit=Arbit;
        this.J1Nom=J1Nom;
        this.J2Nom=J2Nom;
        this.Jornada=Jornada;
        this.ParcialJ1= new ArrayList <String>();
        this.ParcialJ2= new ArrayList <String>();
        this.FinalJ1= new ArrayList <String>();
        this.FinalJ2= new ArrayList <String>();

    }

    public ArrayList<String> getParcialJ1() {
        return ParcialJ1;
    }

    public ArrayList<String> getParcialJ2() {
        return ParcialJ2;
    }

    public ArrayList<String> getFinalJ1() {
        return FinalJ1;
    }

    public ArrayList<String> getFinalJ2() {
        return FinalJ2;
    }

    public partit(String J1Nom, String J2Nom ){

        this.Competicio="";
        this.Modalitat="";
        this.Arbit="";
        this.J1Nom=J1Nom;
        this.J2Nom=J2Nom;
        this.Jornada=NULL;
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


}

