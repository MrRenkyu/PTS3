package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button bt;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button) findViewById(R.id.onlyBt);
        imageview = (ImageView) findViewById(R.id.imageView);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.i("BT INFO", "button click");
                bt.setBackgroundColor(Color.parseColor("#8F8FF8"));

                new displayImage().execute();

            }
        });

    }



    private class displayImage extends AsyncTask<Void, Void, Void> {
        Drawable d;
        @Override
        protected Void doInBackground(Void... voids) {
            Photo picture = null;
            try {
                picture = new Photo("i190950");
            } catch (IOException e) {
                e.printStackTrace();
            }
            d = picture.getPicture();
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            imageview.setImageDrawable(d);
        }
    }





}