package com.geekym.care4u.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.geekym.care4u.R;

public class Safety_Tips extends AppCompatActivity {

    ImageButton Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_safety_tips);

        Home = findViewById(R.id.home);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Safety_Tips.this, Homescreen.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(Safety_Tips.this,Homescreen.class);
        startActivity(intent);
        finishAffinity();
    }
}