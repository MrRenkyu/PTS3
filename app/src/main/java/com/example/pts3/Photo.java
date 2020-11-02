package com.example.pts3;

import android.graphics.drawable.Drawable;
import android.util.Log;


import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class Photo contain the picture of student
 */
public class Photo implements Serializable {

    private Drawable picture;
    private String numStudent;

    public Photo(String numStudent) throws IOException {
        this.numStudent = numStudent;
       // getPictureFromHttp();
    }


    /**
     * this method should be execute in task
     * allow to get the picture of student link with http request
     * @throws IOException
     */
    public void getPictureFromHttp() throws IOException {
        if(picture == null) {
            String urlAddress = "http://perso.univ-lemans.fr/~plafor/gestionabs/images/p-i" + getNumStudent() + ".jpg";
            URL url = new URL(urlAddress);
            /*
            URLConnection connector = url.openConnection();
            connector.connect();
             */

            HttpURLConnection connector = (HttpURLConnection)url.openConnection();
            connector.setRequestMethod("GET");
            int code = connector.getResponseCode();
            Log.e("Photo","HTTP CODE :"+code);
            if(code != 404) {
                connector.connect();
                InputStream input = connector.getInputStream();
                picture = Drawable.createFromStream(input, "src");
            }else{
                picture = ContextCompat.getDrawable(MainActivity.applicationContext,R.drawable.user);
            }

        }
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








