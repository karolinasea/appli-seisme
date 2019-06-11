package com.example.projet_seisme;

import java.util.Date;

public class
Seisme {
    private String title;
    private String place;
    private Double time;
    private double magnitude;
    private double coordinate1;
    private double coordinate2;


    public Seisme(String t, String p, Double d, double m, double coord1, double coord2){
        title = t;
        place = p;
        time = d;
        magnitude = m;
        coordinate1 = coord1;
        coordinate2 = coord2;
    }

    @Override
    public String toString(){
        return "*"+title+" took place at *"+place+"* with a magnitude of *"+magnitude+"*";
    }
    public String getTitle(){
        return title;
    }
    public String getPlace(){
        return place;
    }
    public Double getTime() { return time; }
    public double getMagnitude(){
        return magnitude;
    }

    public Double getCoordinate1() { return coordinate1; }
    public Double getCoordinate2() { return coordinate2; }

    public void setTitle(String s){
        title = s;
    }
    public void setPlace(String s){
        place = s;
    }
    public void setTime(Double s){ time = s; }
    public void setMagnitude(double s){
        magnitude = s;
    }

    public void setCoordinate1(Double c){ coordinate1 = c; }
    public void setCoordinate2(Double c){ coordinate2 = c; }

}

