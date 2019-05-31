package com.javiermoreno.billiapp;

public class ClubEntity {

    private int id,telf,punts;
    private String Nom,Localitat,President;


    public ClubEntity(int id, String nom) {
        this.id = id;
        Nom = nom;
    }

    public ClubEntity (String nom, int punts){

        Nom=nom;
        this.punts=punts;

    }
    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getLocalitat() {
        return Localitat;
    }

    public void setLocalitat(String localitat) {
        Localitat = localitat;
    }

    public String getPresident() {
        return President;
    }

    public void setPresident(String president) {
        President = president;
    }
}
