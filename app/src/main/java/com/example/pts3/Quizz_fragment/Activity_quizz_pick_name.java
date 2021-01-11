package com.example.pts3.Quizz_fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pts3.MainActivity;
import com.example.pts3.Manage_Student.Student;
import com.example.pts3.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Activity_quizz_pick_name extends AppCompatActivity {
    private Random random = new Random();


    private ArrayList<Student> studentArrayList = new ArrayList<>();

    private int nbQuizzLeft;
    private int nbQuizzTotal;
    private int nbQuizzDone = 1;

    private TextView person1;
    private TextView person2;
    private TextView person3;
    private TextView person4;

    private int nb1, nb2, nb3, nb4;

    private ImageView ImageViewPerson;

    private TextView textViewNbQuizz;
    private TextView textViewNbDone;
    private TextView textViewInfo;

    private Button buttonValider;

    private int selectedTextView = 0;
    private int rightImage = 0;

    private int nbBadAnswer = 0;

    private boolean canNext = false;
    private boolean isFinish = false;

    private boolean wait = true;

    private int imageToFInd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_pick_name);

        nbQuizzTotal = getIntent().getIntExtra("nbQuizz", 1);
        nbQuizzLeft = nbQuizzTotal - 1;
        String GroupName = getIntent().getStringExtra("groupName");
        studentArrayList = MainActivity.getStudentManager().getListStudentsByGroupName(GroupName);


        person1 = this.findViewById(R.id.nameButton1);
        person2 = this.findViewById(R.id.nameButton2);
        person3 = this.findViewById(R.id.nameButton3);
        person4 = this.findViewById(R.id.nameButton4);

        ImageViewPerson = this.findViewById(R.id.IMV_QuizzName);
        textViewNbQuizz = this.findViewById(R.id.TV_maxQuestionQuizz);
        textViewNbDone = this.findViewById(R.id.quizzCurrentRoundTextView);
        textViewInfo = this.findViewById(R.id.textViewInfo);

        textViewNbQuizz.setText("" + nbQuizzTotal);
        textViewNbDone.setText("" + nbQuizzDone);

        buttonValider = this.findViewById(R.id.BT_nextQuizz_photo);

        newQuizz();
    }

    private void setOnClickImage() {
        person1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person1.setBackgroundColor(Color.argb(100, 40, 156, 193));
                person2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedTextView = 1;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });

        person2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person2.setBackgroundColor(Color.argb(100, 40, 156, 193));
                person1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedTextView = 2;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });

        person3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person3.setBackgroundColor(Color.argb(100, 40, 156, 193));
                person1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedTextView = 3;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });

        person4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person4.setBackgroundColor(Color.argb(100, 40, 156, 193));
                person1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                person2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedTextView = 4;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setRandomImage() {
        int max = studentArrayList.size();
        while (nb1 == 0 || nb2 == 0 || nb3 == 0 || nb4 == 0) {

            if (nb1 == 0)
                nb1 = random.nextInt(max);

            if (nb2 == 0) {
                do {
                    nb2 = random.nextInt(max);
                } while (nb1 == nb2);
            }

            if (nb3 == 0) {
                do {
                    nb3 = random.nextInt(max);
                } while (nb1 == nb3 || nb2 == nb3);
            }

            if (nb4 == 0) {
                do {
                    nb4 = random.nextInt(max);
                } while (nb1 == nb4 || nb2 == nb4 || nb3 == nb4);
            }

            int rand = random.nextInt(4);
            switch (rand) {
                case 0:
                    imageToFInd = nb1;
                    rightImage = 1;
                    break;
                case 1:
                    imageToFInd = nb2;
                    rightImage = 2;
                    break;
                case 2:
                    imageToFInd = nb3;
                    rightImage = 3;
                    break;
                case 3:
                    imageToFInd = nb4;
                    rightImage = 4;
                    break;
            }
            new Activity_quizz_pick_name.InitializePhoto().execute();

        }
    }

    private void clearBckgroundImage() {
        person2.setBackgroundColor(Color.argb(0, 0, 0, 0));
        person1.setBackgroundColor(Color.argb(0, 0, 0, 0));
        person3.setBackgroundColor(Color.argb(0, 0, 0, 0));
        person4.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

    private void colorImage(){
        if(selectedTextView == rightImage){
            switch (selectedTextView){
                case 1:
                    person1.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 2:
                    person2.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 3:
                    person3.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 4:
                    person4.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
            }
        }else {
            switch (selectedTextView) {
                case 1:
                    person1.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
                case 2:
                    person2.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
                case 3:
                    person3.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
                case 4:
                    person4.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
            }
            switch (rightImage) {
                case 1:
                    person1.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 2:
                    person2.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 3:
                    person3.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 4:
                    person4.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
            }
        }
    }

    private void newQuizz() {
        setOnClickImage();
        nb1 = 0;
        nb2 = 0;
        nb3 = 0;
        nb4 = 0;
        setRandomImage();
        clearBckgroundImage();
        canNext = false;
        buttonValider.setText("VALIDER");
        buttonValider.setVisibility(View.INVISIBLE);
    }

    public void clickValider(View view) {
        if (selectedTextView == 0 && !canNext) {
            textViewInfo.setVisibility(View.VISIBLE);
            textViewInfo.setText("Sélectionner une photo.");
            return;
        }

        if (isFinish)
            finishQuizz();

        if (canNext) {
            if (nbQuizzLeft == 0) {
                textViewInfo.setText("Vous avez fait " + nbBadAnswer + " erreurs");
                buttonValider.setText("TERMINER");
                isFinish = true;
                return;
            }
            nbQuizzDone += 1;
            nbQuizzLeft -= 1;
            textViewNbDone.setText("" + nbQuizzDone);
            textViewInfo.setVisibility(View.INVISIBLE);
            newQuizz();
        } else {
            colorImage();
            if (rightImage == selectedTextView) {
                textViewInfo.setText("Félicitation !");
            } else {
                textViewInfo.setText("Mauvaise réponse !");
                nbBadAnswer += 1;
            }
            textViewInfo.setVisibility(View.VISIBLE);
            canNext = true;
            selectedTextView = 0;
            buttonValider.setText("SUIVANT");
        }

    }

    private void finishQuizz() {
        finish();
    }


    class InitializePhoto extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... posMin) {
            try {
                studentArrayList.get(imageToFInd).getPhoto().getPictureFromHttp();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
                person1.setText(studentArrayList.get(nb1).getFirstName() + " " + studentArrayList.get(nb1).getLastName());
                person2.setText(studentArrayList.get(nb2).getFirstName() + " " + studentArrayList.get(nb2).getLastName());
                person3.setText(studentArrayList.get(nb3).getFirstName() + " " + studentArrayList.get(nb3).getLastName());
                person4.setText(studentArrayList.get(nb4).getFirstName() + " " + studentArrayList.get(nb4).getLastName());
                if (studentArrayList.get(imageToFInd).getPhoto().getPicture() != null)
                    ImageViewPerson.setImageDrawable(studentArrayList.get(imageToFInd).getPhoto().getPicture());
            wait = false;
        }
    }
}
