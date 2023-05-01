package com.example.proyectofinaldam1.models;

import java.util.ArrayList;

public class Usuario {
    private String uid;
    private int points;
    private ArrayList<Set> games;
    private ArrayList<String> idTournament;

    public Usuario(String uid, int points) {
        this.uid = uid;
        this.points = points;
        games = new ArrayList<>();
        idTournament = new ArrayList<>();
    }

    public Usuario(String uid, int points, ArrayList<Set> games, ArrayList<String> idTournament) {
        this.uid = uid;
        this.points = points;
        this.games = games;
        this.idTournament = idTournament;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Set> getGames() {
        return games;
    }

    public void setGames(ArrayList<Set> games) {
        this.games = games;
    }

    public ArrayList<String> getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(ArrayList<String> idTournament) {
        this.idTournament = idTournament;
    }
}
