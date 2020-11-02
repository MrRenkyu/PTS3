package com.example.pts3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentHomePage extends AppCompatActivity {

    private Bundle extraBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        extraBundle = getIntent().getExtras();

        TextView student_firstName_TV = (TextView)findViewById(R.id.etuFirstName);
        TextView student_lastName_TV = (TextView)findViewById(R.id.etuLastName);
        TextView student_Promo_TV = (TextView)findViewById(R.id.etuDutYear);
        TextView student_Grtd_TV = (TextView)findViewById(R.id.etuGroupTD);
        TextView student_Grtp_TV = (TextView)findViewById(R.id.etuGroupeTP);
        TextView student_Number_TV = (TextView)findViewById(R.id.etuNumber);
        TextView student_Birthdate_TV = (TextView)findViewById(R.id.etuBirthDate);
        TextView student_OldSchool_TV = (TextView)findViewById(R.id.etuOriginSchool);
        TextView student_Bac_TV = (TextView)findViewById(R.id.etuBac);

        ImageView photoStudent = (ImageView)findViewById(R.id.imagePerson);


        student_firstName_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.firstName.toString()));
        student_lastName_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.lastName.toString()));
        student_Promo_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.groupPromo.toString()));
        student_Grtd_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.groupTD.toString()));
        student_Grtp_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.groupTP.toString()));
        student_Number_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.number.toString()));
        student_Birthdate_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.birthDate.toString()));
        student_OldSchool_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.originSchool.toString()));
        student_Bac_TV.setText(extraBundle.getBundle("studentInfos").getString(StudentParam.oldBac.toString()));

        byte[] photoByteArray = extraBundle.getBundle(StudentParam.Photo.toString()).getByteArray(StudentParam.Photo.toString());
        Bitmap bmp = BitmapFactory.decodeByteArray(photoByteArray, 0, photoByteArray.length);

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) photoStudent.getLayoutParams();
        params.width = 325;
        params.height = 325;
        photoStudent.setLayoutParams(params);

        photoStudent.setImageBitmap(bmp);




    }


    private byte[] bundleToBytes(@NonNull Bundle bundle) {
        Parcel parcel = Parcel.obtain();
        parcel.writeBundle(bundle);
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return bytes;
    }



}