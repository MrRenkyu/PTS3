package com.example.pts3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantsFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Student> listStudent;
    private Intent studentHomePageIntent;



    private static StudentManager studentManagerFromMainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_etudiants, container, false);
        searchView = rootView.findViewById(R.id.searchView);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.onActionViewExpanded();
            }
        });

        listStudent = getStudentManager().getAllStudents();

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mAdapter = new ItemPersonAdapter(listStudent);


        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        studentHomePageIntent = new Intent(inflater.getContext(), StudentHomePage.class);
        ItemPersonAdapter.setStudentFragment(this);

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
                Log.e("scroll event RV",firstElementPosition+" position of first visible element");
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
                listStudent = studentManagerFromMainActivity.MatchingStudent(newText);
                mAdapter = new ItemPersonAdapter(listStudent);


                mLayoutManager = new LinearLayoutManager(getContext());

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
                return false;
            }
        });

        return rootView;
    }







    public static StudentManager getStudentManager(){
        return studentManagerFromMainActivity;
    }

    public static void setStudentManager(StudentManager studentManager){
        studentManagerFromMainActivity = studentManager;
    }







    class InitializePhoto extends AsyncTask<Integer, Void, Boolean>{
        int actualposMin;

        @Override
        protected Boolean doInBackground(Integer... posMin) {
            actualposMin = posMin[0];
            for(int i = posMin[0]; i< posMin[0]+8; i++){

                try {
                    if(i<listStudent.size()) {
                        listStudent.get(i).getPhoto().getPictureFromHttp();
                    }else{
                        Log.e("InitializePhoto"," out of bound");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



             Log.e("initialize Photo",posMin[0]+"element min ");

            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.e("initializePhoto","On POst Execute");

            for(int i = actualposMin; i< actualposMin+8; i++){
                if(i < listStudent.size()) {
                    ((ItemPersonAdapter) mAdapter).updateImage(i);
                }
            }

    }
    }


    public void startStudentHomePageActivity(Bundle studentInfo, Photo studentPhoto){

        studentHomePageIntent.putExtra("studentInfos",studentInfo);

        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),studentPhoto );
        Bitmap bitmapStudent = ((BitmapDrawable)studentPhoto.getPicture()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapStudent.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        Bundle photoBundle = new Bundle();
        photoBundle.putByteArray(StudentParam.Photo.toString(),b);
        studentHomePageIntent.putExtra(StudentParam.Photo.toString(),photoBundle);
        startActivity(studentHomePageIntent);
    }




}