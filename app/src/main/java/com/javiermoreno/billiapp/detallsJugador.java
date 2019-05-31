package com.javiermoreno.billiapp;

public class detallsJugador {

    private int partides;
    private double promig;
    private int partidesguanyades;
    private int partidesperdudes;
    private double tanpercent;

    public detallsJugador(int partides, double promig, int partidesguanyades, int partidesperdudes) {
        this.partides = partides;
        this.promig = promig;
        this.partidesguanyades = partidesguanyades;
        this.partidesperdudes = partidesperdudes;
    }

    public int getPartides() {
        return partides;
    }

    public void setPartides(int partides) {
        this.partides = partides;
    }

    public double getPromig() {
        return promig;
    }

    public void setPromig(double promig) {
        this.promig = promig;
    }

    public int getPartidesguanyades() {
        return partidesguanyades;
    }

    public void setPartidesguanyades(int partidesguanyades) {
        this.partidesguanyades = partidesguanyades;
    }

    public int getPartidesperdudes() {
        return partidesperdudes;
    }

    public void setPartidesperdudes(int partidesperdudes) {
        this.partidesperdudes = partidesperdudes;
    }

    public double getTanpercent() {
        return tanpercent;
    }

    public void setTanpercent(double tanpercent) {
        this.tanpercent = tanpercent;
    }
}
