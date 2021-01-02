package com.example.pts3.Quizz_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.pts3.MainActivity;
import com.example.pts3.Manage_Student.GroupTP;
import com.example.pts3.Manage_Student.ListStudent;
import com.example.pts3.Manage_Student.Student;
import com.example.pts3.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class QuizzFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Button startButton;
    private Spinner SPI_Groups;
    private Spinner SPI_NbQUestion;
    private Spinner SPI_GameMode;
    private Switch SW_InfiniteQuestion;
    private Switch SW_AllTypeOfQuestion;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quizz, container, false);
        startButton = v.findViewById(R.id.buttonCommencer);
        SPI_Groups = v.findViewById(R.id.SPI_groups);
        SPI_GameMode = v.findViewById(R.id.SPI_GameMode);
        SPI_NbQUestion = v.findViewById(R.id.SPI_NbQuestions);
        SW_InfiniteQuestion = v.findViewById(R.id.SW_unlimitedQuestion);
        SW_AllTypeOfQuestion = v.findViewById(R.id.SW_allTypeOfQuestion);

        ArrayList<String> listGroup = new ArrayList<>();
        listGroup.add("groupe1");
        listGroup.add("groupe2");
        listGroup.add("groupe2");
        listGroup.add("groupe3");



        ArrayList<String> groups = MainActivity.getStudentManager().getEachGroupName();
        SPI_Groups.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapterGroups = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, groups);
        dataAdapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPI_Groups.setAdapter(dataAdapterGroups);

        ArrayList<Integer> nbQUestionList = new ArrayList<>();
        for(int i=5; i<55; i=i+5){ nbQUestionList.add(i); }
        SPI_NbQUestion.setOnItemSelectedListener(this);
        ArrayAdapter<Integer> dataAdapterNbQuestions = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, nbQUestionList);
        dataAdapterNbQuestions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPI_NbQUestion.setAdapter(dataAdapterNbQuestions);

        ArrayList<GameMode> gameModeList = new ArrayList<>();
        gameModeList.addAll(Arrays.asList(GameMode.values()));
        SPI_GameMode.setOnItemSelectedListener(this);
        ArrayAdapter<GameMode> dataAdapterGameMode = new ArrayAdapter<GameMode>(getContext(), android.R.layout.simple_spinner_item, gameModeList);
        dataAdapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPI_GameMode.setAdapter(dataAdapterGameMode);





        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = null;
                if(SPI_GameMode.getSelectedItem().toString() == GameMode.FACE1_NAME4.getValue()){
                    gameIntent = new Intent(getContext(), Activity_quizz_pick_name.class);
                    Log.e("startButton","gameMode: 1visage 4 noms");

                }else if(SPI_GameMode.getSelectedItem().toString() == GameMode.FACES4_NAME1.getValue()){
                    gameIntent = new Intent(getContext(), Activity_quizz_pick_photo.class);
                    Log.e("startButton","gameMode: 4visages 4noms");
                }
                Bundle extras = new Bundle();
                extras.putString("groupName", SPI_Groups.getSelectedItem().toString());
                extras.putInt("nbQuizz", Integer.parseInt(SPI_NbQUestion.getSelectedItem().toString()));
                gameIntent.putExtras(extras);
                startActivity(gameIntent);

            }
        });
        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}