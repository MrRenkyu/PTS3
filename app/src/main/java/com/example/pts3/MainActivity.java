package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;

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


                test();


            }
        });

    }



    public  void  test(){
        ConnectorImage coImage = null;
        try {
            coImage = new ConnectorImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitTest = BitmapFactory.decodeFile("C:\\Users\\Megaport\\Desktop\\image_steam.PNG");
        Drawable d = new BitmapDrawable(getResources(),bitTest);
        imageview.setImageDrawable(d);
        //  imageview.postInvalidate();

    }
}