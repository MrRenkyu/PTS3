package com.example.pts3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * class that get all images from http request
 */
public class ConnectorImage {


    URL url;
    InputStream is;
    Bitmap bit;


    public ConnectorImage() throws IOException {
        Log.i("connector info", "inside connector");
        new MyTask().execute();
    }

    public void initialize(){

    }

    public Bitmap  getBitmap(){
        return bit;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bit = bitmap;
    }



    private class MyTask extends AsyncTask<Void, Void, Void> {
        Bitmap bitTamp;
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            try {
                url = new URL("http://perso.univ-lemans.fr/~plafor/gestionabs/images/p-i132896.jpg");
                InputStream is = url.openStream();
                bitTamp = BitmapFactory.decodeStream(is);
                Log.i("connector image","inside task");

            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            setBitmap(bitTamp);
            Log.i("connector image","end of  task : nb Byte in bitmap "+ bit.getByteCount());
        }
    }
}







