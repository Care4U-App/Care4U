package com.geekym.care4u.HomeScreen.selfAssessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.geekym.care4u.HomeScreen.Homescreen;
import com.geekym.care4u.R;

public class Self_Assessment extends AppCompatActivity {

    CheckBox fever,cough,tired,headache,tastesmell,breath,chestpain,sorethroat,diarrhoea, cardiac, diab, kidliv, cancer, asthma;
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
        asthma = findViewById(R.id.asthma);
        kidliv = findViewById(R.id.kidliv);
        cardiac = findViewById(R.id.cardiac);
        diab = findViewById(R.id.diab);
        cancer = findViewById(R.id.cancer);

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
                        i+=40;
                    }
                    else{
                        i-=40;
                    }
                }

            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.cardiac:
                if(cardiac.isPressed()){
                    if(checked){
                        i+=35;
                    }else{
                        i-=35;
                    }
                }
            case R.id.cancer:
                if(cancer.isPressed()) {
                    if (checked) {
                        i += 45;
                    } else {
                        i -= 45;
                    }
                }
            case R.id.asthma:
                if(asthma.isPressed()){
                    if(checked){
                        i+=47;
                    }else{
                        i-=47;
                    }
                }
            case R.id.diab:
                if(diab.isPressed()){
                    if(checked){
                        i+=40;
                    }else{
                        i-=40;
                    }
                }
            case R.id.kidliv:
                if(kidliv.isPressed()){
                    if(checked){
                        i+=45;
                    }else{
                        i-=45;
                    }
                }
            case R.id.cough:
                if (cough.isPressed()){
                    if (checked){
                        i+=8;
                    }
                    else{
                        i-=8;
                    }
                }
            case R.id.fever:
                if (fever.isPressed()) {
                    if (checked) {
                        i += 10;
                    } else {
                        i -= 10;
                    }
                }
            case R.id.tired:
                if (tired.isPressed()) {
                    if (checked) {
                        i += 3;
                    } else {
                        i -= 3;
                    }
                }
            case R.id.tastesmell:
                if (tastesmell.isPressed()){
                    if (checked){
                        i+=40;
                    }
                    else{
                        i-=40;
                    }
                }
            case R.id.breath:
                if (breath.isPressed()){
                    if (checked){
                        i+=40;
                    }
                    else{
                        i-=40;
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
                        i+=3;
                    }
                    else{
                        i-=3;
                    }
                }
            case R.id.diarrhoea:
                if (diarrhoea.isPressed()){
                    if (checked){
                        i+=8;
                    }
                    else{
                        i-=8;
                    }
                }
            case R.id.headache:
                if (headache.isPressed()){
                    if (checked){
                        i+=5;
                    }
                    else{
                        i-=5;
                    }
                }
                break;
        }
    }


    public void showres(View view) {
        Intent intent = new Intent(Self_Assessment.this, Self_Assessment_Result.class);
        intent.putExtra("newval",i);
        startActivity(intent);
        finish();
    }
}