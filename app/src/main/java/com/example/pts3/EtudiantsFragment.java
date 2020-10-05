package com.example.pts3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EtudiantsFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        final ArrayList<Student> listStudent;
        listStudent = getStudentManager().getAllStudents();

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mAdapter = new ItemPersonAdapter(listStudent);

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Student> tempList = new ArrayList<>();
                for (int i = 0; i < listStudent.size();i++){
                    if (listStudent.get(i).getFirstName().contains(newText)){
                        tempList.add(listStudent.get(i));
                    }
                }
                mAdapter = new ItemPersonAdapter(tempList);

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
}