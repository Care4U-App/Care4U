package com.geekym.care4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_Details extends AppCompatActivity {

    //Here we declare variables
    EditText name, age, weight, height, bpm;
    Button confirm;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_details);

        //Here we initialize variables with ids
        name = findViewById(R.id.name_input);
        age = findViewById(R.id.age_input);
        confirm = findViewById(R.id.conf);
        weight = findViewById(R.id.weight_input);
        height = findViewById(R.id.height_input);
        bpm = findViewById(R.id.bpm_input);
        sp = getSharedPreferences("user_details", Context.MODE_PRIVATE);

        //When the user clicks on Confirm, It takes them to the Vaccine_Detials Activity
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0 || age.length() == 0 || weight.length() == 0 || height.length() == 0 || bpm.length() == 0){
                    Toast.makeText(User_Details.this, "Enter the empty fields!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Here we extract texts entered by the user in the form of Strings
                    String txt_name = name.getText().toString();
                    String txt_age = age.getText().toString();
                    String txt_height = height.getText().toString();
                    String txt_weight = weight.getText().toString();
                    String txt_bpm = bpm.getText().toString();

                    //Here we start saving User's details which we will use in future
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("saved_name", txt_name);
                    editor.putString("saved_age", txt_age);
                    editor.putString("saved_height", txt_height);
                    editor.putString("saved_weight", txt_weight);
                    editor.putString("saved_bpm", txt_bpm);
                    editor.commit();                                                          //Details saving ends
                    Toast.makeText(User_Details.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(User_Details.this,Vaccine_Details.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}