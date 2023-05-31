package com.example.proyectofinaldam1.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
@IgnoreExtraProperties
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

    public Torneo(String uid, String uidCreator, String name, String info, int allPoints, int numMaxUsers, int numUsers, int precioEntrada, ArrayList<Set> sets, ArrayList<Usuario> usersList) {
        this.uid = uid;
        this.uidCreator = uidCreator;
        this.name = name;
        this.info = info;
        this.allPoints = allPoints;
        this.numMaxUsers = numMaxUsers;
        this.numUsers = numUsers;
        this.precioEntrada = precioEntrada;
        this.sets = sets;
        this.usersList = usersList;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUidCreator() {
        return uidCreator;
    }

    public void setUidCreator(String uidCreator) {
        this.uidCreator = uidCreator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(int allPoints) {
        this.allPoints = allPoints;
    }

    public int getNumMaxUsers() {
        return numMaxUsers;
    }

    public void setNumMaxUsers(int numMaxUsers) {
        this.numMaxUsers = numMaxUsers;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public int getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(int precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }

    public ArrayList<Usuario> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<Usuario> usersList) {
        this.usersList = usersList;
    }
}
