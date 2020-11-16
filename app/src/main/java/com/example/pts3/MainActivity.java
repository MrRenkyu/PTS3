package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    static Context applicationContext;
    static StudentManager studentManager;
    boolean queryEnded = true;
    static public ArrayList<Student> studentWithPhoto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = this;

        setContentView(R.layout.activity_main);
        new InitializeJson().execute();
        while (queryEnded) {

        }
        Log.e("MainActivity", studentManager.getAllPromos().size() + "promo");


        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem tabEtudiant = findViewById(R.id.tabEtudiant);
        TabItem tabGroupes = findViewById(R.id.tabGroupes);
        TabItem tabQuizz = findViewById(R.id.tabQuizz);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new
                PagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    class InitializeJson extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... urls) {
            Log.e("intialize task", "task is call");
            studentManager = new StudentManager();
            Log.e("initializeJson", "student manager is end to initialize");

            Log.e("initializeJson", "all file are get from HTTP");


            queryEnded = false;
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

        }
    }

    public  static Context getContext(){
        return applicationContext;
    }

}