package com.geekym.care4u.HomeScreen.selfAssessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.geekym.care4u.HomeScreen.Homescreen;
import com.geekym.care4u.R;
import com.geekym.care4u.HomeScreen.Safety_Tips;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.text.DecimalFormat;

public class Self_Assessment_Result extends AppCompatActivity {

    DonutProgress progress;
    private TextView status, desc,Age, BMI;
    Integer rem=0, flag = 0;
    double  ht, bmi, weight_fin, height_fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_self_assessment_result);

        progress = findViewById(R.id.progress);
        status = findViewById(R.id.status);
        desc = findViewById(R.id.desc);
        BMI = findViewById(R.id.textView5);
        Age = findViewById(R.id.textView4);

        //UserDetails
        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        String height=sp.getString("saved_height", "");     //Maybe Null
        String weight=sp.getString("saved_weight", "");     //Maybe Null
            String age=sp.getString("saved_age", "");       //Not Null
        Integer age_fin = Integer.parseInt(age);

        if(weight.equals("NULL")){
           flag++;
        }else{
            weight_fin = Integer.parseInt(weight);
        }
        if(height.equals("NULL")){
            flag++;
        }else{
            height_fin = Integer.parseInt(height);
        }

        if(flag==0){
            ht = height_fin/100;
            bmi = weight_fin/(ht * ht);
            DecimalFormat formatter = new DecimalFormat("#00.00");
            Age.setText("Age: "+age_fin);
            BMI.setText("BMI: "+formatter.format(bmi));
        }else{
            Age.setText("Age: "+age_fin);
            BMI.setText("BMI: Not Available");
        }

        if(age_fin>=0 && age_fin<=10){
            rem += 2;
        }else if(age_fin>=11 && age_fin <= 25){
            rem += 4;
        }else if(age_fin >= 26 && age_fin <=45){
            rem += 7;
        }else if(age_fin >= 46 && age_fin <= 70){
            rem += 10;
        }else{
            rem += 14;
        }

        Integer prog = getIntent().getExtras().getInt("newval",0);

        if(prog>100){
            prog = 100;
        }
        progress.setProgress(prog.floatValue());


        if(prog<=7) {
            status.setText("Safe-Zone");
            desc.setText("You're Safe Now. Follow proper safety Tips");
        }
        else if(prog>7 && prog<=14)
        {
            status.setText("Low Risk");
            desc.setText("You're are at low risk, take care and proper medications");
        }
        else if(prog>=15 && prog<=29)
        {
            status.setText("Medium Risk");
            desc.setText("Your health is still serious, consult a doctor and self quarantine yourself");
        }
        else if(prog>=30 && prog<=60)
        {
            status.setText("High Risk");
            desc.setText("Please don't go outside, take proper care and consult a Doctor immediately");
        }
        else
        {
            status.setText("Critical Situation");
            desc.setText("Consult a Doctor Immediately, you're at really High Risk!");
        }
    }
    public void back(View view) {
        Intent intent = new Intent(Self_Assessment_Result.this, Self_Assessment.class);
        startActivity(intent);
        finish();
    }

    public void home(View view) {
        Intent intent = new Intent(Self_Assessment_Result.this, Homescreen.class);
        startActivity(intent);
        finishAffinity();
    }
    public void safety(View view) {
        Intent intent = new Intent(Self_Assessment_Result.this, Safety_Tips.class);
        startActivity(intent);
        finishAffinity();
    }
}