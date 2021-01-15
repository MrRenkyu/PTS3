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


public class Activity_quizz_pick_photo extends AppCompatActivity {

    private Random random = new Random();


    private ArrayList<Student> studentArrayList = new ArrayList<>();

    private int nbQuizzLeft;
    private int nbQuizzTotal;
    private int nbQuizzDone = 1;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    private int nb1, nb2, nb3, nb4;

    private TextView textViewPerson;
    private TextView textViewNbQuizz;
    private TextView textViewNbDone;
    private TextView textViewInfo;

    private Button buttonValider;

    private int selectedImage = 0;
    private int rightImage = 0;

    private int nbBadAnswer = 0;

    private boolean canNext = false;
    private boolean isFinish = false;

    private boolean wait = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_pick_photo);

        nbQuizzTotal = getIntent().getIntExtra("nbQuizz", 1);
        nbQuizzLeft = nbQuizzTotal - 1;
        String GroupName = getIntent().getStringExtra("groupName");
        studentArrayList = MainActivity.getStudentManager().getListStudentsByGroupName(GroupName);


        image1 = this.findViewById(R.id.imagePerson1);
        image2 = this.findViewById(R.id.imagePerson2);
        image3 = this.findViewById(R.id.imagePerson3);
        image4 = this.findViewById(R.id.imagePerson4);

        textViewPerson = this.findViewById(R.id.TV_studentName);
        textViewNbQuizz = this.findViewById(R.id.TV_maxQuestionQuizz);
        textViewNbDone = this.findViewById(R.id.quizzCurrentRoundTextView);
        textViewInfo = this.findViewById(R.id.textViewInfo);

        textViewNbQuizz.setText("" + nbQuizzTotal);
        textViewNbDone.setText("" + nbQuizzDone);

        buttonValider = this.findViewById(R.id.BT_nextQuizz_photo);

        newQuizz();
    }

    private void setOnClickImage() {
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setBackgroundColor(Color.argb(100, 40, 156, 193));
                image2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 1;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2.setBackgroundColor(Color.argb(100, 40, 156, 193));
                image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 2;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setBackgroundColor(Color.argb(100, 40, 156, 193));
                image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 3;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image4.setBackgroundColor(Color.argb(100, 40, 156, 193));
                image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 4;
                buttonValider.setVisibility(View.VISIBLE);
            }
        });
    }

    private void colorImage(){
        if(selectedImage == rightImage){
            switch (selectedImage){
                case 1:
                    image1.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 2:
                    image2.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 3:
                    image3.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 4:
                    image4.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
            }
        }else {
            switch (selectedImage) {
                case 1:
                    image1.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
                case 2:
                    image2.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
                case 3:
                    image3.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
                case 4:
                    image4.setBackgroundColor(Color.argb(100, 255, 0, 0));
                    break;
            }
            switch (rightImage) {
                case 1:
                    image1.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 2:
                    image2.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 3:
                    image3.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
                case 4:
                    image4.setBackgroundColor(Color.argb(100, 0, 255, 0));
                    break;
            }
        }
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
            int imageToFInd = 0;
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

            String txt = "Qui est " + studentArrayList.get(imageToFInd).getFirstName()
                    + " " + studentArrayList.get(imageToFInd).getLastName() + " ?";
            textViewPerson.setText(txt);

            new InitializePhoto().execute();
        }
    }

    private void clearBckgroundImage() {
        image2.setBackgroundColor(Color.argb(0, 0, 0, 0));
        image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
        image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
        image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
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
        if (selectedImage == 0 && !canNext) {
            textViewInfo.setVisibility(View.VISIBLE);
            textViewInfo.setText("Sélectionner une photo.");
            return;
        }

        if (isFinish)
            finishQuizz();

        if (canNext) {
            if (nbQuizzLeft == 0) {
                textViewInfo.setText("Vous avez fait " + nbBadAnswer + " erreurs");
                textViewPerson.setVisibility(View.INVISIBLE);
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
            if (rightImage == selectedImage) {
                textViewInfo.setText("Félicitation !");
            } else {
                textViewInfo.setText("Mauvaise réponse !");
                nbBadAnswer += 1;
            }
            textViewInfo.setVisibility(View.VISIBLE);
            canNext = true;
            selectedImage = 0;
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
                studentArrayList.get(nb1).getPhoto().getPictureFromHttp();
                studentArrayList.get(nb2).getPhoto().getPictureFromHttp();
                studentArrayList.get(nb3).getPhoto().getPictureFromHttp();
                studentArrayList.get(nb4).getPhoto().getPictureFromHttp();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (studentArrayList.get(nb1).getPhoto().getPicture() != null)
                image1.setImageDrawable(studentArrayList.get(nb1).getPhoto().getPicture());
            else
                nb1 = 0;

            if (studentArrayList.get(nb2).getPhoto().getPicture() != null)
                image2.setImageDrawable(studentArrayList.get(nb2).getPhoto().getPicture());
            else
                nb2 = 0;

            if (studentArrayList.get(nb3).getPhoto().getPicture() != null)
                image3.setImageDrawable(studentArrayList.get(nb3).getPhoto().getPicture());
            else
                nb3 = 0;

            if (studentArrayList.get(nb4).getPhoto().getPicture() != null)
                image4.setImageDrawable(studentArrayList.get(nb4).getPhoto().getPicture());
            else
                nb4 = 0;

            wait = false;
        }
    }
}