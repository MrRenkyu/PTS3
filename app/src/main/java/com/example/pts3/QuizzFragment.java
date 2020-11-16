package com.example.pts3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class QuizzFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quizz, container, false);

        for (Student student : MainActivity.studentManager.getAllStudents()){
            if (student.getPhoto().getPicture() != null){
                MainActivity.studentWithPhoto.add(student);
            }
        }

        Button b = (Button)v.findViewById(R.id.buttonStarQuizz);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View)v.getParent();
                EditText textNbQuizz = parent.findViewById(R.id.plain_text_input);
                TextView textViewError = parent.findViewById(R.id.textViewError);

                Intent intent = new Intent(MainActivity.getContext(), ActivityQuizz.class);

                String text = textNbQuizz.getText().toString().trim();
                if (text.length() > 0){
                    int nbQuizz = Integer.parseInt(text);
                    if( nbQuizz > 0){
                        intent.putExtra("nbQuizz", nbQuizz);
                        textViewError.setVisibility(View.INVISIBLE);
                        startActivity(intent);
                    }
                }else{
                    textViewError.setText("Veuillez renseigner le nombre de Quizz.");
                    textViewError.setVisibility(View.VISIBLE);
                }

            }
        });

        return v;
    }
}