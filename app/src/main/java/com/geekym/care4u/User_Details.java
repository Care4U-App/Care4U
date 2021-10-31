package com.geekym.care4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class User_Details extends AppCompatActivity {

    //Here we declare variables
    EditText age, weight, height, bpm;
    Button confirm;
    SharedPreferences sp, up, ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_details);

        //Here we initialize variables with ids
        age = findViewById(R.id.age_input);
        confirm = findViewById(R.id.conf);
        weight = findViewById(R.id.weight_input);
        height = findViewById(R.id.height_input);
        bpm = findViewById(R.id.bpm_input);
        sp = getSharedPreferences("user_details", Context.MODE_PRIVATE);

        ud = getApplicationContext().getSharedPreferences("ud", Context.MODE_PRIVATE);
        String Check=ud.getString("update_ud", "");


        //When the user clicks on Confirm, It takes them to the Vaccine_Detials Activity
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (age.length() == 0){     //Age is important for calculation of Self Assessment Result
                    Toast.makeText(User_Details.this, "Entering Age is Compulsory!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Here we extract texts entered by the user in the form of Strings
                    String txt_age = age.getText().toString();
                    String txt_height = height.getText().toString();
                    String txt_weight = weight.getText().toString();
                    String txt_bpm = bpm.getText().toString();
                    if(txt_bpm.equals("")){
                        txt_bpm = "NULL";
                    }
                    if(txt_weight.equals("")){
                        txt_weight = "NULL";
                    }
                    if(txt_height.equals("")){
                        txt_height = "NULL";
                    }
                    //Here we start saving User's details which we will use in future
                    SharedPreferences.Editor editor = sp.edit(); //Details
                    editor.putString("saved_age", txt_age);
                    editor.putString("saved_height", txt_height);
                    editor.putString("saved_weight", txt_weight);
                    editor.putString("saved_bpm", txt_bpm);
                    editor.commit();

                    if (Check.equals("true")) {     //Called from Homescreen (Update)
                        SharedPreferences preferences = getSharedPreferences("ud",MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = preferences.edit();
                        editor1.putString("update_ud","false");
                        editor1.apply();
                        Toast.makeText(User_Details.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(User_Details.this,Homescreen.class);
                        startActivity(intent);
                        finish();
                        }else{
                        Toast.makeText(User_Details.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(User_Details.this,Vaccine_Details.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}