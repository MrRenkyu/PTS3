package com.example.pts3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizzFragment} factory method to
 * create an instance of this fragment.
 */
public class QuizzFragment extends Fragment {

    private Button startButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quizz, container, false);
        startButton = v.findViewById(R.id.buttonCommencer);

        final Intent gameIntent = new Intent(inflater.getContext(),ActivityQuizz.class);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gameIntent);
            }
        });
        return v;
    }


}
/*
    // TODO: Rename modaliteButton name, find better description
    private Button groupButton;
    private Button modeButton;
    private Button modaliteButton;
    private Switch aSwitch;

    private View actualView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("QuizzFragment","new Interface");
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





        // Find view by Id
        groupButton = actualView.findViewById(R.id.groupButton);
        modeButton = actualView.findViewById(R.id.modeButton);
        modaliteButton = actualView.findViewById(R.id.modaliteButton);

        // Set on click listeners
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGrp = new Intent(inflater.getContext(), SettingGroup.class);
                startActivity(intentGrp);
            }
        });

        modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMod = new Intent(inflater.getContext(), SettingMode.class);
                startActivity(intentMod);
            }
        });

        modaliteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentModal = new Intent(inflater.getContext(), SettingModalite.class);
                startActivity(intentModal);
            }
        });

        return actualView;

    }


}

 */