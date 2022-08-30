package com.geekym.care4u.HomeScreen.FoodForYou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.geekym.care4u.HomeScreen.Homescreen;
import com.geekym.care4u.R;

public class Fruits extends AppCompatActivity {

    ImageButton backhome, backfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_fruits);

            backfood = findViewById(R.id.backfood);
            backhome = findViewById(R.id.backhome);

            backfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Fruits.this, Food_For_You.class);
                    startActivity(intent);
                    finish();
                }
            });

            backhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(Fruits.this, Homescreen.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });
        }
        @Override
        public void onBackPressed() {
            Intent intent = new Intent(Fruits.this, Food_For_You.class);
            startActivity(intent);
            super.onBackPressed();
        }
    }