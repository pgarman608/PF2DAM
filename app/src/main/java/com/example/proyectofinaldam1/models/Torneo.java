package com.example.proyectofinaldam1.models;

import java.util.ArrayList;

public class Torneo {
    private String uid;
    private String uidCreator;
    private String name;
    private String info;
    private int allPoints;
    private int numMaxUsers;
    private int numUsers;
    private int precioEntrada;
    private ArrayList<Set> sets;
    private ArrayList<Usuario> usersList;
    public Torneo(String uid,String uidCreator,String name, String info, int numMaxUsers, int precioEntrada) {
        this.uid = uid;
        this.name = name;
        this.uidCreator = uidCreator;
        this.info = info;
        this.allPoints = 0;
        this.numMaxUsers = numMaxUsers;
        this.precioEntrada = precioEntrada;
        this.numUsers = 0;
        this.sets = new ArrayList<>();
        this.usersList = new ArrayList<>();
    }
}
