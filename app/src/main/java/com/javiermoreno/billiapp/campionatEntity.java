package com.javiermoreno.billiapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class campionatEntity implements Parcelable {

    private int id,jornada;
    private String NomCompeticio,Modalitat,Arbit,DataCompeticio;



    public campionatEntity(int id,  int jornada, String nomCompeticio, String modalitat, String arbit, String dataCompeticio) {
        this.id = id;
        this.jornada = jornada;
        NomCompeticio = nomCompeticio;
        Modalitat = modalitat;
        Arbit = arbit;
        DataCompeticio = dataCompeticio;
    }

    protected campionatEntity(Parcel in) {
        id = in.readInt();
        jornada = in.readInt();
        NomCompeticio = in.readString();
        Modalitat = in.readString();
        Arbit = in.readString();
    }

    public static final Creator<campionatEntity> CREATOR = new Creator<campionatEntity>() {
        @Override
        public campionatEntity createFromParcel(Parcel in) {
            return new campionatEntity(in);
        }

        @Override
        public campionatEntity[] newArray(int size) {
            return new campionatEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public String getNomCompeticio() {
        return NomCompeticio;
    }

    public void setNomCompeticio(String nomCompeticio) {
        NomCompeticio = nomCompeticio;
    }

    public String getModalitat() {
        return Modalitat;
    }

    public void setModalitat(String modalitat) {
        Modalitat = modalitat;
    }

    public String getArbit() {
        return Arbit;
    }

    public void setArbit(String arbit) {
        Arbit = arbit;
    }

    public String getDataCompeticio() {
        return DataCompeticio;
    }

    public void setDataCompeticio(String dataCompeticio) {
        DataCompeticio = dataCompeticio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(jornada);
        dest.writeString(NomCompeticio);
        dest.writeString(Modalitat);
        dest.writeString(Arbit);
    }
}
