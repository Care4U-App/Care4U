package com.geekym.care4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Self_Assessment extends AppCompatActivity {

    CheckBox fever,cough,tired,headache,tastesmell,breath,chestpain,sorethroat,diarrhoea;
    Switch qstncheck;
    Integer i=0;
    Button button, Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_self_assessment);

        fever = findViewById(R.id.fever);
        cough = findViewById(R.id.cough);
        tired = findViewById(R.id.tired);
        tastesmell = findViewById(R.id.tastesmell);
        breath = findViewById(R.id.breath);
        chestpain = findViewById(R.id.chestpain);
        sorethroat = findViewById(R.id.sorethroat);
        diarrhoea = findViewById(R.id.diarrhoea);
        qstncheck = findViewById(R.id.qstncheck);
        headache = findViewById(R.id.headache);
        button = findViewById(R.id.button);
        Home = findViewById(R.id.backhome);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Self_Assessment.this, Homescreen.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        qstncheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (qstncheck.isPressed()){
                    if (qstncheck.isChecked()){
                        i+=30;
                    }
                    else{
                        i-=30;
                    }
                }

            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.cough:
                if (cough.isPressed()){
                    if (checked){
                        i+=7;
                    }
                    else{
                        i-=7;
                    }
                }
            case R.id.fever:
                if (fever.isPressed()) {
                    if (checked) {
                        i += 7;
                    } else {
                        i -= 7;
                    }
                }
            case R.id.tired:
                if (tired.isPressed()) {
                    if (checked) {
                        i += 6;
                    } else {
                        i -= 6;
                    }
                }
            case R.id.tastesmell:
                if (tastesmell.isPressed()){
                    if (checked){
                        i+=15;
                    }
                    else{
                        i-=15;
                    }
                }
            case R.id.breath:
                if (breath.isPressed()){
                    if (checked){
                        i+=15;
                    }
                    else{
                        i-=15;
                    }
                }
            case R.id.chestpain:
                if (chestpain.isPressed()){
                    if (checked){
                        i+=4;
                    }
                    else{
                        i-=4;
                    }
                }
            case R.id.sorethroat:
                if (sorethroat.isPressed()){
                    if (checked){
                        i+=2;
                    }
                    else{
                        i-=2;
                    }
                }
            case R.id.diarrhoea:
                if (diarrhoea.isPressed()){
                    if (checked){
                        i+=7;
                    }
                    else{
                        i-=7;
                    }
                }
            case R.id.headache:
                if (headache.isPressed()){
                    if (checked){
                        i+=7;
                    }
                    else{
                        i-=7;
                    }
                }
                break;
        }
    }


    public void showres(View view) {
        Intent intent = new Intent(Self_Assessment.this,Self_Assessment_Result.class);
        intent.putExtra("newval",i);
        startActivity(intent);
        finish();
    }
}