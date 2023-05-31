package com.example.proyectofinaldam1.models;

import android.graphics.Bitmap;

public class Charpter {
    private Bitmap[] idleImages;
    private Bitmap[] attackImages;
    private Bitmap[] hurtImages;
    private int currentState;
    private int currentFrame;
    private long startTime;

    public Charpter(Bitmap[] idleImages, Bitmap[] attackImages, Bitmap[] hurtImages) {
        this.idleImages = idleImages;
        this.attackImages = attackImages;
        this.hurtImages = hurtImages;
        this.currentState = 0;  // Estado inicial (idle)
        this.currentFrame = 0;
        this.startTime = System.currentTimeMillis();
    }

    public void updateState(int newState) {
        currentState = newState;
        currentFrame = 0;
        startTime = System.currentTimeMillis();
    }

    public Bitmap getCurrentImage() {
        Bitmap[] currentImages;

        if (currentState == 1) {  // ataque
            currentImages = attackImages;
        } else if (currentState == 2) {  // daño
            currentImages = hurtImages;
        } else {  // idle
            currentImages = idleImages;
        }

        int frameIndex = (int) ((System.currentTimeMillis() - startTime) / 100) % currentImages.length;
        currentFrame = frameIndex;

        return currentImages[frameIndex];
    }
}
