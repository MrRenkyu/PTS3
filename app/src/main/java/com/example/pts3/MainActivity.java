package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.pts3.Manage_Student.Student;
import com.example.pts3.Manage_Student.StudentManager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    static Context applicationContext;
    static StudentManager studentManager;
    boolean queryEnded = true;

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
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#E9C46A"));
        TabItem tabEtudiant = findViewById(R.id.tabEtudiant);
        setStyletabLayout(tabLayout,"Etudiant",0);
        TabItem tabGroupes = findViewById(R.id.tabGroupes);
        setStyletabLayout(tabLayout,"Groupes",1);
        TabItem tabQuizz = findViewById(R.id.tabQuizz);
        setStyletabLayout(tabLayout,"Quizz",2);
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

    private void setStyletabLayout(TabLayout tabLayout,String text,int index) {
        TextView tamp = new TextView(this);
        tamp.setText(text);
        tamp.setGravity(Gravity.CENTER);
        tamp.setTextColor(Color.parseColor("#E9C46A"));
        Typeface typeface = ResourcesCompat.getFont(this,R.font.maven_pro_bold);
        tamp.setTypeface(typeface);
        tabLayout.getTabAt(index).setCustomView(tamp);
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

    public static StudentManager getStudentManager() {
        return studentManager;
    }
}