package com.example.premierepage.ui.notifications;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.premierepage.R;

public class PageReglageGroupe extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_reglage_groupe);

        button = findViewById(R.id.buttonRetourConfig);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourQuizz();
            }
        });

    }

    @Override
    /*public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        button = findViewById(R.id.buttonRetourConfig);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourQuizz();
            }
        });

    }*/

    public void retourQuizz() {

        Intent intent = new Intent(this, NotificationsFragment.class);
        startActivity(intent);

    }
}