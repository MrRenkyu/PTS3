package com.example.pts3;

import android.graphics.drawable.Drawable;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class Photo contain the picture of student
 */
public class Photo {

    private Drawable picture;
    private String numStudent;

    public Photo(String numStudent) throws IOException {
        this.numStudent = numStudent;
        //getPictureFromHttp();
        getPictureFromHttp();
    }


    /**
     * this method should be execute in task
     * allow to get the picture of student link with http request
     * @throws IOException
     */
    private void getPictureFromHttp() throws IOException {
        String urlAddress = "http://perso.univ-lemans.fr/~plafor/gestionabs/images/p-i"+getNumStudent()+".jpg";
        URL url = new URL(urlAddress);
        URLConnection connector = url.openConnection();
        connector.connect();
        InputStream input = connector.getInputStream();

        picture =  Drawable.createFromStream(input,"src");
    }






    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public String getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(String numStudent) {
        this.numStudent = numStudent;
    }

    public Drawable getPicture() {
        return picture;
    }
}
