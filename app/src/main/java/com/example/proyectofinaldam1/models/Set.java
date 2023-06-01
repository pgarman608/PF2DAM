package com.example.proyectofinaldam1.models;

public class Set {
    private Usuario uid_j1;
    private Usuario uid_j2;
    private int[] games;
    private int[] char_j1;
    private int[] char_j2;
    private String round;

    private int startSS;

    private int typeSets = 5;

    private int end;
    public Set(Usuario uid_j1, Usuario uid_j2, String round) {
        this.uid_j1 = uid_j1;
        this.uid_j2 = uid_j2;
        this.round = round;
        this.games = new int[3];
        this.char_j1 = new int[3];
        this.char_j2 = new int[3];
        this.end = 0;
    }

}
