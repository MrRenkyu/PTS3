package com.example.pts3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ActivityQuizz extends AppCompatActivity {

    private Random random = new Random();

    //private ArrayList<Student> studentArrayList = new ArrayList<>();
    private StudentManager studentManager;
    private int nbQuizzLeft;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    private TextView textViewPerson;
    private TextView textViewNbQuizzLeft;
    private TextView textViewSuccess;

    private Button buttonValider;

    private int selectedImage = 0;
    private int rightImage = 0;

    private int nbBadAnswer = 0;

    private boolean canNext = false;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        studentManager = MainActivity.studentManager;
        nbQuizzLeft = getIntent().getIntExtra("nbQuizz",1);

        image1 = this.findViewById(R.id.imageView);
        image2 = this.findViewById(R.id.imageView2);
        image3 = this.findViewById(R.id.imageView3);
        image4 = this.findViewById(R.id.imageView4);

        textViewPerson = this.findViewById(R.id.textViewPerson);
        textViewNbQuizzLeft = this.findViewById(R.id.textViewNbQuizzLeft);
        textViewSuccess = this.findViewById(R.id.textViewSuccess);

        textViewNbQuizzLeft.setText("Quizz restant : " + nbQuizzLeft);

        buttonValider = this.findViewById(R.id.buttonValider);

        newQuizz();
    }

    private void setOnClickImage(){
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setBackgroundColor(Color.argb(100,0, 255, 0));
                image2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 1;
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2.setBackgroundColor(Color.argb(100,0, 255, 0));
                image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 2;
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setBackgroundColor(Color.argb(100,0, 255, 0));
                image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 3;
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image4.setBackgroundColor(Color.argb(100,0, 255, 0));
                image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
                image2.setBackgroundColor(Color.argb(0, 0, 0, 0));
                selectedImage = 4;
            }
        });
    }

    private void setRandomImage(){
        int max = studentManager.getAllStudents().size();
        int nb1 = random.nextInt(max);

        int nb2;
        do {
            nb2 = random.nextInt(max);
        }while (nb1 == nb2);

        int nb3;
        do {
            nb3 = random.nextInt(max);
        }while (nb1 == nb3 || nb2 == nb3);

        int nb4;
        do {
            nb4 = random.nextInt(max);
        }while (nb1 == nb4 || nb2 == nb4 || nb3 == nb4);

        int rand = random.nextInt(4);
        int imageToFInd = 0;
        switch (rand){
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

        String txt = "Qui est " + studentManager.getAllStudents().get(imageToFInd).getFirstName()
                + " " + studentManager.getAllStudents().get(imageToFInd).getLastName() + " ?";
        textViewPerson.setText(txt);

        image1.setImageDrawable(studentManager.getAllStudents().get(nb1).getPhoto().getPicture());
        image2.setImageDrawable(studentManager.getAllStudents().get(nb2).getPhoto().getPicture());
        image3.setImageDrawable(studentManager.getAllStudents().get(nb3).getPhoto().getPicture());
        image4.setImageDrawable(studentManager.getAllStudents().get(nb4).getPhoto().getPicture());
    }

    private void clearBckgroundImage(){
        image2.setBackgroundColor(Color.argb(0, 0,  0, 0));
        image1.setBackgroundColor(Color.argb(0, 0, 0, 0));
        image3.setBackgroundColor(Color.argb(0, 0, 0, 0));
        image4.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

    private void newQuizz(){
        setOnClickImage();
        setRandomImage();
        clearBckgroundImage();
        canNext = false;
        buttonValider.setText("VALIDER");
    }

    public void clickValider(View view) {
        if (selectedImage == 0 && !canNext){
            textViewSuccess.setVisibility(View.VISIBLE);
            textViewSuccess.setText("Veuillez sélectionner une photo.");
            return;
        }

        if (isFinish)
            finishQuizz();

        if (canNext){
            if (nbQuizzLeft == 0){
                textViewSuccess.setText("Vous avez fait " + nbBadAnswer + " erreurs");
                textViewPerson.setVisibility(View.INVISIBLE);
                buttonValider.setText("TERMINER");
                isFinish = true;
                return;
            }
            nbQuizzLeft -= 1;
            textViewNbQuizzLeft.setText("Quizz restant : " + nbQuizzLeft);
            textViewSuccess.setVisibility(View.INVISIBLE);
            newQuizz();
        }else{
            if (rightImage == selectedImage){
                textViewSuccess.setText("Félicitation !");
            }else{
                textViewSuccess.setText("Mauvaise réponse !");
                nbBadAnswer += 1;
            }
            textViewSuccess.setVisibility(View.VISIBLE);
            canNext = true;
            selectedImage = 0;
            buttonValider.setText("SUIVANT");
        }

    }

    private void finishQuizz(){
        finish();
    }
}