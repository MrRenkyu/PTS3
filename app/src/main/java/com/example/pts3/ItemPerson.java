package com.example.pts3;

import android.widget.ImageButton;

public class ItemPerson {
    private int mImageResource;
    private String firstName;
    private String lastName;
    private String promo;
    private String grTd;
    private String grTp;

    private int mImageResource2;

    public ItemPerson(int imageResource, String nom, int imageResource2){
        mImageResource = imageResource;
        firstName = nom;
        mImageResource2 = imageResource2;

    }

    public void changerPlus(int imageRessourceChange){
        mImageResource2 = imageRessourceChange;
    }

    public void changeName(String name){
        firstName = name;
    }

    public  int getImageResource(){
        return mImageResource;
    }

    public String getNom(){
        return firstName;
    }

    public  int getImageResource2(){
        return mImageResource2;
    }

}