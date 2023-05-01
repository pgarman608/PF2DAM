package com.example.proyectofinaldam1.models;

import java.util.ArrayList;

public class Torneo {
    private String uid;
    private String name;
    private String info;
    private int allPoints;
    private ArrayList<String> sets;

    public Torneo(String uid, String name, String info) {
        this.uid = uid;
        this.name = name;
        this.info = info;
        this.allPoints = 0;
        this.sets = new ArrayList<>();
    }
}
