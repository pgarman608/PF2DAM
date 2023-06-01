package com.example.proyectofinaldam1.models;

import java.util.List;

public class AnimatedCharapter {
    private int[] imgidle;
    private int[] imgrun;
    private int[] imgattack;
    private int[] imgdead;
    private int imgshield;
    private int status;
    private int postStatus;

    private int indice;
    public static interface SetListImg{
        void setListChr(int[] listImg,int index);
    }

    public AnimatedCharapter(int[] imgidle, int[] imgrun, int[] imgattack, int[] imgdead, int imgshield) {
        this.imgidle = imgidle;
        this.imgrun = imgrun;
        this.imgattack = imgattack;
        this.imgdead = imgdead;
        this.imgshield = imgshield;
        this.indice = 0;
        this.postStatus = 0;
        this.status = 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(int postStatus) {
        this.postStatus = postStatus;

    }
    public void incrPostStatus(){
        postStatus = status;
        postStatus++;
    }
    public void changeImageSet(SetListImg callback){
        if (postStatus != status){
            indice = 0;
            status = postStatus;
        }
        int[] imgsAux = null;
        switch (postStatus){
            case 0:
                imgsAux = imgidle;
                break;
            case 1:
                imgsAux = imgrun;
                break;
            case 2:
                imgsAux = imgattack;
                break;
            case 3:
                imgsAux = imgdead;
                break;
            case 4:
                imgsAux = new int[]{imgshield};
                break;
        }
        if (status >= 5){
            status = 0;
            postStatus = 0;
        }
        callback.setListChr(imgsAux,indice);
        indice = (indice + 1) % imgidle.length;
    }
}
