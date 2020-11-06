package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GroupDetailActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Student> listStudent;
    private Intent studentHomePageIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        searchView = findViewById(R.id.searchView_GroupDetail);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.onActionViewExpanded();
            }
        });

        listStudent = MainActivity.studentManager.

        mRecyclerView = findViewById(R.id.recyclerView_GroupDetail);
        mAdapter = new GroupDetailAdaptater(listStudent);


        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        studentHomePageIntent = new Intent(this, StudentHomePage.class);
        GroupDetailAdaptater.setGroupDetailActivity(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //some code when initially scrollState changes
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //Some code while the list is scrolling
                LinearLayoutManager lManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int firstElementPosition = lManager.findFirstVisibleItemPosition();
                Log.e("scroll event RV", firstElementPosition + " position of first visible element");
                new InitializePhoto().execute(firstElementPosition);

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listStudent = MainActivity.studentManager.MatchingStudent(newText);
                mAdapter = new GroupDetailAdaptater(listStudent);


                mLayoutManager = new LinearLayoutManager(getApplicationContext());

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                return false;
            }
        });


    }


    class InitializePhoto extends AsyncTask<Integer, Void, Boolean> {
        int actualposMin;

        @Override
        protected Boolean doInBackground(Integer... posMin) {
            actualposMin = posMin[0];
            for (int i = posMin[0]; i < posMin[0] + 8; i++) {

                try {
                    if (i < listStudent.size()) {
                        listStudent.get(i).getPhoto().getPictureFromHttp();
                    } else {
                        Log.e("InitializePhoto", " out of bound");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            Log.e("initialize Photo", posMin[0] + "element min ");

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Log.e("initializePhoto", "On POst Execute");

            for (int i = actualposMin; i < actualposMin + 8; i++) {
                if (i < listStudent.size()) {
                    ((GroupDetailAdaptater) mAdapter).updateImage(i);
                }
            }

        }
    }


    public void startStudentHomePageActivity(Bundle studentInfo, Photo studentPhoto) {

        studentHomePageIntent.putExtra("studentInfos", studentInfo);

        Bitmap bitmapStudent;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b;
        /*
        if(studentPhoto.getPicture() != null) {
            bitmapStudent = ((BitmapDrawable) studentPhoto.getPicture()).getBitmap();
        }else{
            bitmapStudent = ((BitmapDrawable) ( ContextCompat.getDrawable(actualView.getContext(), R.drawable.ic_launcher_foreground))  ).getBitmap();
        }
        */
        bitmapStudent = ((BitmapDrawable) studentPhoto.getPicture()).getBitmap();
        baos = new ByteArrayOutputStream();
        bitmapStudent.compress(Bitmap.CompressFormat.PNG, 100, baos);
        b = baos.toByteArray();


        Bundle photoBundle = new Bundle();
        photoBundle.putByteArray(StudentParam.Photo.toString(), b);
        studentHomePageIntent.putExtra(StudentParam.Photo.toString(), photoBundle);
        startActivity(studentHomePageIntent);
    }
}