package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class StudentHomePage extends AppCompatActivity {

    private Bundle extraBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        extraBundle = getIntent().getExtras();

        Button bt = (Button)findViewById(R.id.nameButton);
        bt.setText(extraBundle.getBundle("studentInfos").getString("firstName"));
    }



}