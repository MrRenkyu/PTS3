package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    StudentManager studentManager;
    boolean queryEnded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
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


=======
>>>>>>> 403ffeed388dfbff1d48081d855fd45a76c8f5b3
    }


    class InitializeJson extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... urls) {
            Log.e("intialize task", "task is call");
            studentManager = new StudentManager();
            Log.e("initializeJson", "student manager is end to initialize");

            Log.e("initializeJson", "all file are get from HTTP");
            GroupesFragment.setStudentManager(studentManager);
            EtudiantsFragment.setStudentManager(studentManager);

            queryEnded = false;
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

        }
    }

}