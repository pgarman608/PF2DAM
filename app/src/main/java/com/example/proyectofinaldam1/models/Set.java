package com.example.proyectofinaldam1.models;

public class Set {
    private String uid_j1;
    private String uid_j2;
    private int gameWins_j1;
    private int gameWins_j2;
    private String char_j1;
    private String char_j2;
    private String round;

    public Set(String uid_j1, String uid_j2, String round) {
        this.uid_j1 = uid_j1;
        this.uid_j2 = uid_j2;
        this.round = round;
        this.gameWins_j1 = -1;
        this.gameWins_j2 = -1;
        this.char_j1 = "";
        this.char_j2 = "";
    }

    public int getGameWins_j1() {
        return gameWins_j1;
    }

    public void setGameWins_j1(int gameWins_j1) {
        this.gameWins_j1 = gameWins_j1;
    }

    public int getGameWins_j2() {
        return gameWins_j2;
    }

    public void setGameWins_j2(int gameWins_j2) {
        this.gameWins_j2 = gameWins_j2;
    }

    public String getChar_j1() {
        return char_j1;
    }

    public void setChar_j1(String char_j1) {
        this.char_j1 = char_j1;
    }

    public String getChar_j2() {
        return char_j2;
    }

    public void setChar_j2(String char_j2) {
        this.char_j2 = char_j2;
    }
}
